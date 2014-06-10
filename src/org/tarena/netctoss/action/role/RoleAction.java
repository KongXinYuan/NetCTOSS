package org.tarena.netctoss.action.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.Privilege;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.util.PrivilegeReader;
/**
 * 封装对角色操作的请求
 * modi 处理修改请求
 * add 处理添加请求
 * nameCheck 处理角色名检查请求
 * delete 处理删除请求
 * 
 * */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {
	private Role role;//角色信息
	private int id;//角色Id
	private String name;//角色名
	private boolean ok;//操作状态
	private List<Privilege> privileges;//权限选项数据
	@Resource
	private RoleDAO roleDAO;	
	//处理修改请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String modi(){
		privileges = PrivilegeReader.getModules();//获取权限选项数据
		//修改信息不为空，进行修改操作
		if (role != null){
			try {
				roleDAO.update(role);
				request.put("updateFlag", 1);
				
			} catch (DAOException e) {
				request.put("updateFlag", 2);
				e.printStackTrace();
			}
		}else {
			//修改信息为空，读取信息
			try {
				role = roleDAO.findById(id);
			} catch (DAOException e) {
				e.printStackTrace();
				return "error";
			}
		}
		return "success";
	}
	//处理添加请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String add(){
		privileges = PrivilegeReader.getModules();//获取权限选项数据
		//信息为空直接进入页面
		if (role == null) {
			return "success";
		}
		//信息不为空，进行添加操作
		try {			
			roleDAO.insert(role);
			request.put("addFlag", 1);			
		} catch (DAOException e) {
			request.put("addFlag", 2);
			e.printStackTrace();
		}
		return "success";
	}
	//处理角色名检查请求
	@Transactional(readOnly=true)
	public String nameCheck(){
		try {
			if (roleDAO.findByName(name) == null){
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
			roleDAO.delete(id);
			ok = true;			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return "ok";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
}
