package org.tarena.netctoss.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.AdminInfo;
import org.tarena.netctoss.pojo.Role;
/**
 * 封装对管理员的操作请求
 * add 处理添加请求
 * adminCheck 处理管理员账号检查请求
 * delete 处理删除请求
 * modi 处理修改请求
 * resetPwd 处理重置密码请求
 * */
@Controller
@Scope("prototype")
public class AdminAction extends BaseAction {
	private AdminInfo admin;//管理员信息
	private int id;//管理员id
	private List<Integer> roleIds = new ArrayList<Integer>();//角色名id
	private List<Role> roles;//角色选项
	private boolean ok = false;//操作状态
	private String adminCode;//登录名
	private String[] adminCodes; //重置密码的管理员登录名
	@Resource
	private AdminInfoDAO adminInfoDAO;
	@Resource
	private RoleDAO roleDAO;	
	//处理添加请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String add(){
		try {
			roles = roleDAO.findAll();//获得角色选项信息
		} catch (DAOException e1) {
			e1.printStackTrace();
			return "error";			
		}
		//添加信息为空直接进如页面
		if (admin == null){
			return "success";
		} else {
			//有添加信息进行添加操作
			try {
				for (Integer roleId:roleIds){
					//设置管理员角色关系
					Role r = new Role();
					r.setId(roleId);
					admin.getRoles().add(r);
				}
				//添加管理员到数据库
				admin.setEnrolldate(new Date(System.currentTimeMillis()));
				adminInfoDAO.insert(admin);
				request.put("addFlag", 1);
			} catch (DAOException e) {
				request.put("addFlag", 2);
				e.printStackTrace();
			}
		}
		return "success";
	}
	//处理管理员登入名检查请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String adminCheck(){
		try {
			if(adminInfoDAO.findByAdminCode(adminCode) == null){
				ok = true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		return "ok";
	}
	//处理删除请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String delete(){
		try {
			adminInfoDAO.delete(id);
			ok = true;
		} catch (DAOException e) {			
			e.printStackTrace();
			ok = false;
		}
		return "ok";
	}
	//处理修改请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String modi(){
		try {
			roles = roleDAO.findAll();//获取角色选项数据
		} catch (DAOException e1) {
			e1.printStackTrace();
			return "error";			
		}
		//修改信息为空是，读取信息
		if (admin == null){
			try {
				admin = adminInfoDAO.findById(id);
				for (Role r : admin.getRoles()) {
					roleIds.add(r.getId());
				}
			} catch (DAOException e) {
				e.printStackTrace();
				return "error";
			}
		} else {
			//有修改信息时，进行修改操作
			try {
				for (Integer roleId:roleIds){
					admin.getRoles().add(roleDAO.findById(roleId));
				}
				adminInfoDAO.update(admin);
				request.put("updateFlag", 1);
			} catch (DAOException e) {
				e.printStackTrace();
				request.put("updateFlag", 2);
			}
		}
		return "success";
	}
	//处理密码重置请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String resetPwd(){
		try {			
			adminInfoDAO.resetPwd(adminCodes);
			ok = true;			
		} catch (DAOException e) {			
			e.printStackTrace();
		}
		return "ok";
	}
	public AdminInfo getAdmin() {
		return admin;
	}
	public void setAdmin(AdminInfo admin) {
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String[] getAdminCodes() {
		return adminCodes;
	}

	public void setAdminCodes(String[] adminCodes) {
		this.adminCodes = adminCodes;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	public void setAdminInfoDAO(AdminInfoDAO adminInfoDAO) {
		this.adminInfoDAO = adminInfoDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
