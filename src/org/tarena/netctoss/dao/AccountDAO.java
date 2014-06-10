package org.tarena.netctoss.dao;

import java.util.List;
import org.tarena.netctoss.pojo.Account;

public interface AccountDAO {
	// 查询
	public List<Account> findPage(String idcardNo, String realName,
			String loginName, String status, Integer page, Integer pageSize)
			throws DAOException;

	// 查页数
	public Integer totalPages(String idcardNo, String realName, String loginName,
			String status, Integer pageSize) throws DAOException;

	// 根据ID查数据
	public Account findById(Integer id) throws DAOException;
	
	// 根据idcard查数据
	public Account findByIdcard(String idcard) throws DAOException;

	// 根据ID更新数据
	public void update(Account a) throws DAOException;

	// 根据ID修改状态
	public void setStatus(Integer id, String status) throws DAOException;

	// 添加新数据
	public void insert(Account a) throws DAOException;

	// 根据ID更新最后登录状态
	public void setLastLogin(Integer id, String lastLoginIp) throws DAOException;
	
	//根据用户名查密码
	public String findPwdByName(String loginName) throws DAOException;
	
	//根据idcardNo查ID
	public Integer findIdByidcard(String idcard) throws DAOException;
	//根据id修改service状态
	public void setServiceStatusByAccountId(Integer accountId, String status)
			throws DAOException;

}
