package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.ServiceDetail;

public interface ServiceDetailDAO {
	//根据serviceId和月份分页查询
	public List<ServiceDetail> findPage(Integer serviceId, String month, Integer page,
			Integer pageSize) throws DAOException;
	//根据serviceId和月份统计页数
	public Integer totalPages(Integer serviceId, String month, Integer pageSize) throws DAOException;
}
