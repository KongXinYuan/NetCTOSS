package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.Role;

public interface RoleDAO {
	//查全部
	public List<Role> findAll() throws DAOException;
	//查询
	public List<Role> findPage(Integer page, Integer pageSize) throws DAOException;
	//根据ID插查询
	public Role findById(Integer id) throws DAOException;
	//根据ID修改
	public void update(Role role) throws DAOException;
	//根据ID删除
	public void delete(Integer id) throws DAOException;
	//添加记录
	public void insert(Role role) throws DAOException;
	//查记录数
	public Integer totalPages(Integer pageSize) throws DAOException;	
	//根据name返回记录
	public Role findByName(String name) throws DAOException;
	//根据privilegeId获得roleId
	public List<Integer> getRoleIdByPrivilegeId(Integer privilegeId) throws DAOException;
	
}
