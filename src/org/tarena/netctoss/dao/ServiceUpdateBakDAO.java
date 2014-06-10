package org.tarena.netctoss.dao;

import org.tarena.netctoss.pojo.ServiceUpdateBak;

public interface ServiceUpdateBakDAO {
	// 添加记录
	public void insert(ServiceUpdateBak sub) throws DAOException;
	//根据id删除记录
	public void delete(Integer id) throws DAOException;
	//根据id更改记录
	public void update(ServiceUpdateBak sub) throws DAOException;
	//根据serviceId查找记录
	public ServiceUpdateBak findByServiceId(Integer serviceId) throws DAOException;
}
