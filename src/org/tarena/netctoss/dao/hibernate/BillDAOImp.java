package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.BillDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Bill;

@Repository("billDAO")
public class BillDAOImp extends BaseDAO implements BillDAO{
	//多条件分页查询
	@SuppressWarnings("unchecked")
	public List<Bill> findPage(final String idcardNo, final String loginName,
			final String realName, final Integer year, final Integer month, 
			final Integer page, final Integer pageSize) throws DAOException {
		List<Bill> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer("from Bill b join fetch b.account a where ");
				List<String> params = new ArrayList<String>();
				//年份月份条件
				if (year == null || year == 0) {
					hql.append("1=1 ");
				} else {
					if (month == null || month == 0) {
						hql.append("b.billMonth like ? ");
						params.add(year + "%");
					} else {
						hql.append("b.billMonth=? ");
						if (month < 10) {
							params.add(year + "0" + month);
						} else {
							params.add(year + "" + month);
						}						
					}
				}
				//身份证条件
				if (idcardNo != null && !"".equals(idcardNo)) {
					hql.append("and a.idcardNo like ? ");
					params.add("%" + idcardNo + "%");
				}
				//账务账号条件
				if (loginName != null && !"".equals(loginName)) {
					hql.append("and a.loginName like ? ");
					params.add("%" + loginName + "%");
				}
				//真实姓名条件
				if (realName != null && !"".equals(realName)) {
					hql.append("and a.realName like ? ");
					params.add("%" + realName + "%");
				}
				Query query = session.createQuery(hql.toString());
				for (int i = 0; i < params.size(); i++) {
					query.setString(i, params.get(i));
				}				
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<Bill> list = query.list();
				return list;
			}
		});
		return list;
	}
	//多条件统计页数
	public Integer totalPages(final String idcardNo, final String loginName,
			final String realName, final Integer year, final Integer month, 
			final Integer pageSize)	throws DAOException {
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer("select count(b.id) from Bill b join b.account a where ");
				List<String> params = new ArrayList<String>();
				//年份月份条件
				if (year == null || year == 0) {
					hql.append("1=1 ");
				} else {
					if (month == null || month == 0) {
						hql.append("b.billMonth like ? ");
						params.add(year + "%");
					} else {
						hql.append("b.billMonth=? ");
						if (month < 10) {
							params.add(year + "0" + month);
						} else {
							params.add(year + "" + month);
						}						
					}
				}
				//身份证条件
				if (idcardNo != null && !"".equals(idcardNo)) {
					hql.append("and a.idcardNo like ? ");
					params.add("%" + idcardNo + "%");
				}
				//账务账号条件
				if (loginName != null && !"".equals(loginName)) {
					hql.append("and a.loginName like ? ");
					params.add("%" + loginName + "%");
				}
				//真实姓名条件
				if (realName != null && !"".equals(realName)) {
					hql.append("and a.realName like ? ");
					params.add("%" + realName + "%");
				}
				Query query = session.createQuery(hql.toString());
				for (int i = 0; i < params.size(); i++) {
					query.setString(i, params.get(i));
				}
				List list = query.list();
				return list;
			}
		});
		Integer count = 0;
		if (!list.isEmpty())
			count = Integer.parseInt(list.get(0).toString());
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}
	
	public Bill findId(Integer id) throws DAOException {
		Bill bill = (Bill) getHibernateTemplate().get(Bill.class, id);
		return bill;
	}

}
