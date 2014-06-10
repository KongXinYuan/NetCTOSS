package org.tarena.netctoss.dao;

import java.util.List;
import org.tarena.netctoss.pojo.Cost;

public interface CostDAO {
	//查询全部信息
	public List<Cost> findAll() throws DAOException;
	//分页查询
	public List<Cost> findPage(Integer page, Integer pageSize, Integer sortNo) throws DAOException;
	//统计页数
	public Integer totalPages(Integer pageSize) throws DAOException;
	//插入新记录
	public void insert(Cost cost) throws DAOException;
	//更新记录
	public void update(Cost cost) throws DAOException;
	//删除记录
	public void delete(Integer id) throws DAOException;
	//根据ID查找记录
	public Cost findById(Integer id) throws DAOException;
	//根据名字查找记录
	public Cost findByName(String name) throws DAOException;
}
