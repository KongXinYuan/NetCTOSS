package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.BillItemDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.BillItem;
@Repository("billItemDAO")
public class BillItemDAOImp extends BaseDAO implements BillItemDAO {
	//根据账单号分页查询
	public List<BillItem> findPage(final Integer billId, final Integer page,
			final Integer pageSize) throws DAOException {
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String hql = "from BillItem bi join fetch bi.service s join fetch bi.bill b where b.id=?";
					Query query = session.createQuery(hql);
					query.setInteger(0, billId);
					query.setFirstResult((page - 1) * pageSize);
					query.setMaxResults(pageSize);
					List<BillItem> list = query.list();
					return list;
				}
			});
		return list;
	}
	//根据账单号统计页数
	public Integer totalPage(Integer billId, Integer pageSize) throws DAOException {
		String hql = "select count(b.id) from BillItem b left join b.bill a where a.id=?";
		List list = getHibernateTemplate().find(hql, billId);
		Integer count = Integer.parseInt(list.get(0).toString());
		System.out.println(count);
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}
	public Integer duraction(Integer serviceId, String month)
			throws DAOException {
		String hql = "select sum(sd.duration) from ServiceDetail sd join sd.service" +
				" s where s.id=? and to_char(sd.logoutTime,'yyyymm')=?";
		Object[] params = {serviceId, month};
		List list = getHibernateTemplate().find(hql,params);
		if (!list.isEmpty()) {
			return Integer.parseInt(list.get(0).toString());
		}
		return null;
	}

}
