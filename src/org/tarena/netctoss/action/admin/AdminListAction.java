package org.tarena.netctoss.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.AdminInfo;
import org.tarena.netctoss.pojo.Privilege;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.util.PrivilegeReader;
@Controller
@Scope("prototype")
public class AdminListAction extends BaseAction {
	private List<AdminInfo> admins;
	private int page = 1;
	private int pageSize;
	private int totalPages;
	private String roleName;
	private List<String> roleNames;
	private List<String> shortRoleNames;
	private List<Privilege> privileges;
	private int privilegeId = 0;
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
		privileges = new ArrayList<Privilege>();
		Privilege p = new Privilege();
		p.setId(0);
		p.setModuleName("全部");
		privileges.add(p);
		privileges.addAll(PrivilegeReader.getModules());		
		try {
			
			roleNames = new ArrayList<String>();
			shortRoleNames = new ArrayList<String>();
			if (roleName != null && !roleName.equals("")){
				admins = adminInfoDAO.findRole(roleName, page, pageSize);
				totalPages = adminInfoDAO.totalPagesRole(roleName, pageSize);
			} else if (privilegeId != 0) {
				List<Integer> roleIds = roleDAO.getRoleIdByPrivilegeId(privilegeId);
				admins = adminInfoDAO.findRole(roleIds, page, pageSize);
				totalPages = adminInfoDAO.totalPagesRole(roleIds, pageSize);
			} else {
				admins = adminInfoDAO.findPage(page, pageSize);
				totalPages = adminInfoDAO.totalPages(pageSize);
			}
			for(AdminInfo admin:admins){
				StringBuilder str = new StringBuilder();
				StringBuilder str1 = new StringBuilder();
				int i = 0;
				for (Role role:admin.getRoles()){					
					if(i > 0){
						str.append(",");
					} else if (i == 0){
						str1.append(role.getName());
						if (admin.getRoles().size() > 1){
							str1.append("...");
						}
					}					
					str.append(role.getName());	
					i++;
				}
				roleNames.add(str.toString());
				shortRoleNames.add(str1.toString());
			}
		} catch (DAOException e) {			
			e.printStackTrace();
			return "success";
		}
		return "success";
	}

	public List<AdminInfo> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminInfo> admins) {
		this.admins = admins;
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

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	public List<String> getShortRoleNames() {
		return shortRoleNames;
	}

	public void setShortRoleNames(List<String> shortRoleNames) {
		this.shortRoleNames = shortRoleNames;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	
	
	
}
