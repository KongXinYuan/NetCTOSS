package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ReportDAO;
import org.tarena.netctoss.pojo.ReportCostUsed;
import org.tarena.netctoss.pojo.ReportSumDuration;
@Repository("reportDAO")
public class ReportDAOImp extends BaseDAO implements ReportDAO {
	
	//分页查询unix服务器id
	@SuppressWarnings("unchecked")
	public List<ReportCostUsed> findHostPage(final Integer page, final Integer pageSize)
			throws DAOException {
		List<Object[]> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select h.id ,(select count(*) from Service s join s.cost c " +
						"where c.costType=0 and s.unixHost=h.id),(select count(*) from " +
						"Service s join s.cost c where c.costType=1 and s.unixHost=h.id)," +
						"(select count(*) from Service s join s.cost c where c.costType=2 " +
						"and s.unixHost=h.id) from Host h";
				Query query = session.createQuery(hql);
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<Object[]> list = query.list();
				return list;
			}
		});
		List<ReportCostUsed> list1 = new ArrayList<ReportCostUsed>();
		for (Object[] objs: list) {
			ReportCostUsed rcu = new ReportCostUsed();
			rcu.setHost(objs[0].toString());
			rcu.setMonthlyCount(Integer.parseInt(objs[1].toString()));
			rcu.setPackageCount(Integer.parseInt(objs[2].toString()));
			rcu.setTimeBasedCount(Integer.parseInt(objs[3].toString()));
			list1.add(rcu);
		}		
		return list1;
	}
	//unix服务器页数
	public Integer totalPagesHost(Integer pageSize) throws DAOException {
		String hql = "select count(id) from Host";
		List list = getHibernateTemplate().find(hql);
		Integer count = Integer.parseInt(list.get(0).toString());
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count /pageSize + 1;
		}		
	}
	//分页查询用户使用时长
	@SuppressWarnings("unchecked")
	public List<ReportSumDuration> findSumDurationPage(final Integer page, final Integer pageSize)
			throws DAOException {
		List<Object[]> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select max(a.id), max(a.loginName), max(a.realName), max(a.idcardNo), max(a.telephone), " +
						"max(to_char(sd.logoutTime,'yyyymm')), sum(sd.duration) from ServiceDetail sd left " +
						"join sd.service s left join s.account a group by a.id, to_char(sd.logoutTime,'yyyymm') " +
						"order by a.id, to_char(sd.logoutTime,'yyyymm')";
				Query query = session.createQuery(hql);
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<Object[]> list = query.list();
				return list;
			}
		});
		List<ReportSumDuration> rsd = new ArrayList<ReportSumDuration>();
		for (Object[] objs:list) {
			ReportSumDuration sd = new ReportSumDuration();
			sd.setId(objs[0].toString());
			sd.setLoginName(objs[1].toString());
			sd.setRealName(objs[2].toString());
			sd.setIdcardNo(objs[3].toString());
			sd.setTelephone(objs[4].toString());
			sd.setMonth(objs[5].toString());
			sd.setDuration(objs[6].toString());
			rsd.add(sd);
		}
		return rsd;
	}
	//用户使用时长记录页数
	public Integer totalPagesSumDuration(Integer pageSize) throws DAOException {	
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select count( sum(sd.duration)) " +
				"from service_detail sd left join service s " +
				"on sd.service_id = s.id " +
				"left join account a " +
				"on a.id = s.account_id " +
				"group by a.id, to_char(sd.logout_time,'yyyymm')";
				Query query = session.createSQLQuery(hql);				
				return query.list();
			}
		});
		Integer count = Integer.parseInt(list.get(0).toString());
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count /pageSize + 1;
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportSumDuration> findThreeDuration(final String unixHost)
		throws DAOException {		
		List<Object[]> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select max(s.unixHost),max(a.loginName),max(a.realName),max(a.idcardNo),sum(sd.duration) from" +
						" ServiceDetail sd left join sd.service s left join s.account a "+
						" where s.unixHost=? group by s.id order by sum(sd.duration) desc";
				Query query = session.createQuery(hql);
				query.setString(0, unixHost);
				query.setFirstResult(0);
				query.setMaxResults(3);
				List<Object[]> list = query.list();
				return list;
			}
		});		
		List<ReportSumDuration> rsd = new ArrayList<ReportSumDuration>();
		for (Object[] objs: list) {
			ReportSumDuration sd = new ReportSumDuration();
			sd.setUnixHost(objs[0].toString());
			sd.setLoginName(objs[1].toString());
			sd.setRealName(objs[2].toString());
			sd.setIdcardNo(objs[3].toString());			
			sd.setDuration(objs[4].toString());
			rsd.add(sd);
		}
		return rsd;
	}
	
	public List<String> findAllHost() throws DAOException {
		String hql = "select id from Host";
		List<String> list = getHibernateTemplate().find(hql);
		return list;
	}
	
	

}
