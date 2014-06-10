package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDetailDAO;
import org.tarena.netctoss.pojo.ServiceDetail;
@Repository("serviceDetailDAO")
public class ServiceDetailDAOImp extends BaseDAO implements ServiceDetailDAO {

	public List<ServiceDetail> findPage(final Integer serviceId, final String month,
			final Integer page, final Integer pageSize) throws DAOException {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "from ServiceDetail sd join fetch sd.service s where s.id=? " +
						"and to_char(sd.logoutTime,'yyyymm')=?";
				Query query = session.createQuery(hql);
				query.setInteger(0, serviceId);
				query.setString(1, month);
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<ServiceDetail> list = query.list();
				return list;
			}
		});
		return list;
	}

	public Integer totalPages(final Integer serviceId, final String month, final Integer pageSize)
			throws DAOException {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select count(*) from ServiceDetail sd join sd.service s where s.id=? " +
						"and to_char(sd.logoutTime,'yyyymm')=?";
				Query query = session.createQuery(hql);
				query.setInteger(0, serviceId);
				query.setString(1, month);				
				List<ServiceDetail> list = query.list();
				return list;
			}
		});
		Integer count = 0;
		if (!list.isEmpty()) {
			count = Integer.parseInt(list.get(0).toString());
		}
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}

}
