package org.tarena.netctoss.action;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.AdminInfo;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.util.PrivilegeReader;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction{
	private String code;
	private String adminCode;
	private String password;
	private AdminInfoDAO adminInfoDAO;
	private RoleDAO roleDAO;
	@Resource
	public void setAdminInfoDAO(AdminInfoDAO adminInfoDAO) {
		this.adminInfoDAO = adminInfoDAO;
	}
	@Resource
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Transactional(readOnly=true)
	public String execute(){
		System.out.println(adminCode+"-------------------->"+password);
		if (adminCode == null){
			return "login";
		}
		AdminInfo admin = null;
		String realCode = (String)session.get("code");
		if(!realCode.equalsIgnoreCase(code)){
			request.put("loginFlag", 2);
			return "login";
		}
		try {
			System.out.println("adminInfoDAO------------------->"+adminInfoDAO);
			admin = adminInfoDAO.findByAdminCode(adminCode);
			if (admin == null){
				request.put("loginFlag", 1);
				return "login";
			}else{
				if(!admin.getPassword().equals(password)){
					request.put("loginFlag", 1);
					return "login";
				}
			}
		Set<Integer> privilegeIds = new HashSet<Integer>();		
		for (Role role:admin.getRoles()){
			role = roleDAO.findById(role.getId());
			privilegeIds.addAll(role.getPrivilegeIds());
		}		
		Set<String> urls = new HashSet<String>();
		for (Integer id:privilegeIds) {
			urls.addAll(PrivilegeReader.getModulUrlsById(id));
		}
		session.put("urls", urls);
		session.put("admin", admin);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
