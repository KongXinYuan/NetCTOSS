package org.tarena.netctoss.action.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.AdminInfo;
/**处理修改用户密码请求*/
@Controller
@Scope("prototype")
public class UserModiPwdAction extends BaseAction{
	private String oldPwd;
	private String newPwd;
	@Resource
	private AdminInfoDAO adminInfoDAO;	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute(){
		//无修改信息，直接进了页面
		if (oldPwd == null){
			return "success";
		}
		//获得登录用信息
		AdminInfo admin = (AdminInfo) session.get("admin");
		try {
			if (admin.getPassword().equals(oldPwd)){
				adminInfoDAO.updatePwdById(admin.getId(), newPwd);
				request.put("updateFlag", 1);
				admin.setPassword(newPwd);
				session.put("admin", admin);
			} else {
				request.put("updateFlag", 2);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.put("updateFlag", 2);
		}
		
		return "success";
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	public void setAdminInfoDAO(AdminInfoDAO adminInfoDAO) {
		this.adminInfoDAO = adminInfoDAO;
	}
}
