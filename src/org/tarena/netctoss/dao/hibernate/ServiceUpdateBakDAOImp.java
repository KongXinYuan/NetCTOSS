package org.tarena.netctoss.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceUpdateBakDAO;
import org.tarena.netctoss.pojo.ServiceUpdateBak;
@Repository("serviceUpdateBakDAO")
public class ServiceUpdateBakDAOImp extends BaseDAO implements
		ServiceUpdateBakDAO {

	public void insert(ServiceUpdateBak sub) throws DAOException {		
		getHibernateTemplate().save(sub);
	}

	public void delete(Integer id) throws DAOException {
		ServiceUpdateBak sub = (ServiceUpdateBak) getHibernateTemplate().get(ServiceUpdateBak.class, id);
		getHibernateTemplate().delete(sub);
	}

	public void update(ServiceUpdateBak sub) throws DAOException {
		getHibernateTemplate().update(sub);
	}

	public ServiceUpdateBak findByServiceId(Integer serviceId)
			throws DAOException {
		String hql = "from ServiceUpdateBak where serviceId=?";
		List list = getHibernateTemplate().find(hql, serviceId);
		ServiceUpdateBak sub = null;
		if (!list.isEmpty()){
			sub = (ServiceUpdateBak) list.get(0);
		}
		return sub;
	}

}
