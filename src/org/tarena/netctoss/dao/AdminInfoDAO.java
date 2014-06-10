package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.AdminInfo;

public interface AdminInfoDAO{
	//添加新记录
	public void insert(AdminInfo admin) throws DAOException;
	//根据ID查找
	public List<AdminInfo> findPage(Integer page, Integer pageSize) throws DAOException;
	//根据ID查找
	public AdminInfo findById(Integer id) throws DAOException;	
	//根据admincode找记录
	public AdminInfo findByAdminCode(String adminCode) throws DAOException;
	//根据id更改密码
	public void updatePwdById(Integer id, String pwd) throws DAOException;
	//根据id更新记录
	public void update(AdminInfo admin) throws DAOException;	
	//根据id删除记录
	public void delete(Integer id) throws DAOException;
	//获得总页数
	public Integer totalPages(Integer pageSize) throws DAOException;
	//根据角色查询
	public List<AdminInfo> findRole(String roleName,Integer page, Integer pageSize) throws DAOException;
	public List<AdminInfo> findRole(List<Integer> roleIds,Integer page, Integer pageSize) throws DAOException;
	public Integer totalPagesRole(List<Integer> roleIds, Integer pageSize) throws DAOException;
	public Integer totalPagesRole(String roleName, Integer pageSize) throws DAOException;
	//根据id重置密码
	public void resetPwd(String[] adminCodes) throws DAOException;
	
}
