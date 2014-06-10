package org.tarena.netctoss.action.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.AccountDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Account;
/**账务账号信息查询*/
@Controller
@Scope("prototype")
public class AccountSearchListAction {
	
	private List<Account> accounts;//查到的账务账号信息集合
	private int totalPages;//查到记录的总页数
	private String status = "-1";//状态查询条件，“-1”为全部
	private String idcardNo;//身份证查询条件
	private String realName;//真实姓名查询条件
	private String loginName;//登录名查询条件
	private int page = 1;//当前页，首次登录默认为1
	private int pageSize;//每页显示记录数，由配置文件注入
	@Resource
	private AccountDAO AcouuntDAO;//dao注入
	
	@Transactional(readOnly=true)
	public String execute(){
		try {
			accounts = AcouuntDAO.findPage(idcardNo, realName, loginName, status, page, pageSize);//获取账务账号记录集合
			totalPages = AcouuntDAO.totalPages(idcardNo, realName, loginName, status, pageSize);//获取总页数
		} catch (DAOException e) {			
			e.printStackTrace();
			
		}
		return "success";
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public void setAcouuntDAO(AccountDAO acouuntDAO) {
		AcouuntDAO = acouuntDAO;
	}
	
}
