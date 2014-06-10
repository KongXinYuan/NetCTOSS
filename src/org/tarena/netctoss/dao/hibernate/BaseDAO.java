package org.tarena.netctoss.dao.hibernate;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO extends HibernateDaoSupport{
	@Resource
	public void setMySessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
}
