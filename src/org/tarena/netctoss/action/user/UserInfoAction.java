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
import org.tarena.netctoss.pojo.Role;
/**处理修改登录用户信息的请求*/
@Controller
@Scope("prototype")
public class UserInfoAction extends BaseAction{
	private AdminInfo admin;
	private String roleName;
	@Resource
	private AdminInfoDAO adminInfoDAO;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String execute(){
		//有修改信息，进行修改操作
		if (admin != null) {
			try {
				AdminInfo admin1 = adminInfoDAO.findById(admin.getId());
				admin1.setName(admin.getName());
				admin1.setTelephone(admin.getTelephone());
				admin1.setEmail(admin.getEmail());
				adminInfoDAO.update(admin1);
				request.put("updateFlag", 1);
				session.put("admin", admin1);
			} catch (DAOException e) {
				request.put("updateFlag", 2);
				e.printStackTrace();
			}
		}
		//获取登录用户信息
		admin = (AdminInfo) session.get("admin");
		if (admin != null) {
			StringBuilder str = new StringBuilder();
			int  i = 0;
			for (Role role:admin.getRoles()) {
				if (i > 0) {
					str.append("，");
				}
				str.append(role.getName());
				i++;
			}
			roleName = str.toString();			
		}
		return "success";
	}

	public AdminInfo getAdmin() {
		return admin;
	}

	public void setAdmin(AdminInfo admin) {
		this.admin = admin;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}	
	
	public void setAdminInfoDAO(AdminInfoDAO adminInfoDAO) {
		this.adminInfoDAO = adminInfoDAO;
	}

}
