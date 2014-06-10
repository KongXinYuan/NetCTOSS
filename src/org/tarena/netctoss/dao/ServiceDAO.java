package org.tarena.netctoss.dao;

import java.util.List;
import org.tarena.netctoss.pojo.Service;

public interface ServiceDAO {
	//查找
	public List<Service> findPage(String osUsername, String unixHost,
			String idcard, String status, Integer page, Integer pageSize)
			throws DAOException;
	//统计页数
	public Integer totalPages(String osUsername, String unixHost,
			String idcard, String status, Integer pageSize) throws DAOException;
	//根据ID查记录
	public Service findByid(Integer id) throws DAOException;
	//根据ID设置状态
	public void setStatusById(Integer id, String status) throws DAOException;
	//添加新记录
	public void insert(Service service) throws DAOException;
	//根据id查找service记录	
	public void updateCostId(Integer id, Integer costId) throws DAOException;
	//检查unixHost与osUername主键是否存在
	public boolean existHostOs(String unixHost, String osUsername) throws DAOException;
	//检查unixHost是否存在
	public boolean existHost(String unixHost) throws DAOException;
}
