package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.Bill;

public interface BillDAO {
	//多条件分页查询
	public List<Bill> findPage(String idcardNo, String loginName, 
			String realName, Integer year, Integer month, Integer page, 
			Integer pageSize) throws DAOException;
	//多条件统计页数
	public Integer totalPages(String idcardNo, String loginName, 
			String realName, Integer year, Integer month, 
			Integer pageSize) throws DAOException;
	//根据ID查记录
	public Bill findId(Integer id) throws DAOException;
}
