package org.tarena.netctoss.dao.hibernate;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDAO;
import org.tarena.netctoss.pojo.Account;
import org.tarena.netctoss.pojo.Cost;
import org.tarena.netctoss.pojo.Service;

@Repository("serviceDAO")
public class ServiceDAOImp extends BaseDAO implements ServiceDAO {

	@SuppressWarnings("unchecked")
	public List<Service> findPage(final String osUsername, final String unixHost,
			final String idcard, final String status, final Integer page, final Integer pageSize)
			throws DAOException {
		List<Service> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuilder sql = new StringBuilder("from Service s join fetch s.account a join fetch s.cost where ");
				List<Object> params = new ArrayList<Object>();
				if (status == null || status.equals("-1")) {
					sql.append("1=1");
				} else {
					sql.append("s.status=?");
					params.add(status);
				}
				if (osUsername != null && !"".equals(osUsername)) {
					sql.append(" and s.osUsername like ?");
					params.add("%" + osUsername + "%");
				}
				if (unixHost != null && !"".equals(unixHost)) {
					sql.append(" and s.unixHost like ?");
					params.add("%" + unixHost + "%");
				}
				if (idcard != null && !"".equals(idcard)) {
					sql.append(" and a.idcardNo like ?");
					params.add("%" + idcard + "%");
				}
				Query query = session.createQuery(sql.toString());
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i,params.get(i));			
				}
				int begin = (page - 1) * pageSize;
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				List<Service> list = query.list();
				return list;
			}
		});
		return list;
	}

	public Integer totalPages(String osUsername, String unixHost,
			String idcard, String status, Integer pageSize) throws DAOException {
		StringBuilder sql = new StringBuilder("select count(s.id) from Service s left join s.account a where ");
		List<Object> params = new ArrayList<Object>();
		if (status == null || status.equals("-1")) {
			sql.append("1=1");
		} else {
			sql.append("s.status=?");
			params.add(status);
		}
		if (osUsername != null && !"".equals(osUsername)) {
			sql.append(" and s.osUsername like ?");
			params.add("%" + osUsername + "%");
		}
		if (unixHost != null && !"".equals(unixHost)) {
			sql.append(" and s.unixHost like ?");
			params.add("%" + unixHost + "%");
		}
		if (idcard != null && !"".equals(idcard)) {
			sql.append(" and a.idcardNo like ?");
			params.add("%" + idcard + "%");
		}
		List list = getHibernateTemplate().find(sql.toString(), params.toArray());
		int totalPages = 1;
		int count = Integer.parseInt(list.get(0).toString());
		if (count % pageSize == 0){
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}

	public Service findByid(Integer id) throws DAOException {
		Service s = (Service) getHibernateTemplate().get(Service.class, id);
		return s;
	}
	
	public void setStatusById(Integer id, String status) throws DAOException {
		Service s = (Service) getHibernateTemplate().get(Service.class, id);
		s.setStatus(status);
		if ("0".equals(status)) {
			s.setCloseDate(null);
			s.setPauseDate(null);
		} else if ("1".equals(status)) {
			s.setPauseDate(new Date(System.currentTimeMillis()));
		} else if ("2".equals(status)) {
			s.setCloseDate(new Date(System.currentTimeMillis()));
		}
		getHibernateTemplate().save(s);
	}

	public void insert(Service service) throws DAOException {
		Account a = (Account) getHibernateTemplate().get(Account.class, service.getAccount().getId());
		Cost c = (Cost) getHibernateTemplate().get(Cost.class, service.getCost().getId());
		service.setCost(c);
		service.setAccount(a);
		service.setCreateDate(new Date(System.currentTimeMillis()));
		service.setStatus("0");
		getHibernateTemplate().save(service);
	}

	public void updateCostId(Integer id, Integer costId) throws DAOException {
		Service s = (Service) getHibernateTemplate().get(Service.class, id);
		Cost c = (Cost) getHibernateTemplate().get(Cost.class, costId);
		s.setCost(c);
		getHibernateTemplate().save(s);	
	}

	public boolean existHostOs(String unixHost, String osUsername)
			throws DAOException {
		String hql = "from Service where unixHost=? and osUsername=?";
		Object[] params = {unixHost, osUsername};
		List list = getHibernateTemplate().find(hql, params);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean existHost(String unixHost) throws DAOException {
		String hql = "from Host where id=?";
		List list = getHibernateTemplate().find(hql,unixHost);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}




}
