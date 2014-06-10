package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.BillItem;

public interface BillItemDAO {
	//根据账单号分页查询
	public List<BillItem> findPage(Integer billId, Integer page, Integer pageSize) throws DAOException;
	//根据账单号统计页数
	public Integer totalPage(Integer billId, Integer pageSize) throws DAOException;
	//根据serviceId和月份统计时间
	public Integer duraction(Integer serviceId, String month) throws DAOException;
}
