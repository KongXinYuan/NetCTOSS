package org.tarena.netctoss.action.account;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.AccountDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Account;
/**
 * 封装对账务账号的控制
 * load 读取指定记录的详细信息
 * detail 处理对获取账务账号详单的请求
 * modi 处理对账务账号修改的请求
 * setStatus 处理对账务账号开启，暂停的请求
 * delete 处理对账务账号设置删除状态的请求
 * add 处理对添加账务账号的请求
 * idcardNoCheck 处理对推荐人验证的请求
 * */
@Controller
@Scope("prototype")
public class AccoutAction extends BaseAction {
	private Account account;//账务账号信息
	private int id;//账务账号id
	private String recommenderIdcardNo;//推荐人身份证
	private String status;//需设置的状态
	private boolean ok = false;//请求是否成功执行
	private int changePwd = 0;//是否修改密码，0为不修改
	private String oldpwd;//旧密码
	private String newpwd;//新密码
	@Resource
	private AccountDAO AcouuntDAO;//注入dao	
	//获取account记录信息
	@Transactional(readOnly=true)
	public String load() {
		try {
			account = AcouuntDAO.findById(id);
			if (account != null && account.getRecommenderId() != null){
				Account ac = AcouuntDAO.findById(account.getRecommenderId());
				recommenderIdcardNo = ac.getIdcardNo();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	//处理显示详单请求
	@Transactional(readOnly=true)
	public String detail(){
		return load();
	}
	//处理修改请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String modi(){
		//无修改信息时，读取信息
		if (account == null) {
			return load();
		}
		 //有修改信息是保存修改信息
		try {
			if(account != null){				
				String pwd = AcouuntDAO.findPwdByName(account.getLoginName());				
				if(changePwd == 1){					
					if (pwd != null){
						if(pwd.equals(oldpwd)){
							account.setLoginPasswd(newpwd);
						}else{
							request.put("updateFlag", 3);
							return "success";
						}
					}
				}else{
					account.setLoginPasswd(pwd);
				}				
				AcouuntDAO.update(account);
				request.put("updateFlag",1);
			}
			throw new RuntimeException();
		} catch (DAOException e) {
			e.printStackTrace();
			request.put("updateFlag", 2);
			return "success";
		}
//		return "success";
	}
	//处理开启，暂停请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String setStatus(){
		try {
			AcouuntDAO.setStatus(id, status);
			ok = true;
			if ("1".equals(status)){			
			AcouuntDAO.setServiceStatusByAccountId(id, status);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "ok";
		}
		return "ok";
	}
	//处理删除请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String delete(){
		try {
			AcouuntDAO.setStatus(id, "2");
			ok = true;
			AcouuntDAO.setServiceStatusByAccountId(id, "2");
			
		} catch (DAOException e) {
			e.printStackTrace();
			return "ok";
		}
		return "ok"; 
	}
	//处理添加请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String add() {
		try {
			if (account != null) {
				account.setStatus("0");
				account.setCreateDate(new Date(System.currentTimeMillis()));
				if (account.getRecommenderId() == 0)
					account.setRecommenderId(null);
				AcouuntDAO.insert(account);
				request.put("addFlag",1);
			}
		} catch (DAOException e) {
			request.put("addFlag", 2);
		}
		return "success";
	}	
	//处理推荐人验证请求
	@Transactional(readOnly=true)
	public String idcardNoCheck(){
		try {
			System.out.println(recommenderIdcardNo);
			Integer recommenderId = AcouuntDAO.findIdByidcard(recommenderIdcardNo);
			if (recommenderId == null) {
				id = 0;
			} else {
				id = recommenderId;
			}
		} catch (DAOException e) {			
			e.printStackTrace();
			return "id";
		}			
		return "id";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecommenderIdcardNo() {
		return recommenderIdcardNo;
	}

	public void setRecommenderIdcardNo(String recommenderIdcardNo) {
		this.recommenderIdcardNo = recommenderIdcardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getChangePwd() {
		return changePwd;
	}

	public void setChangePwd(int changePwd) {
		this.changePwd = changePwd;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public void setAcouuntDAO(AccountDAO acouuntDAO) {
		AcouuntDAO = acouuntDAO;
	}	
	

}
