package org.tarena.netctoss.action.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.dao.hibernate.BaseDAO;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.util.PrivilegeReader;
/**角色列表显示*/
@Controller
@Scope("prototype")
public class RoleListAction extends BaseDAO {
	private List<Role> roles;//角色列表信息
	private int page = 1;//当前页
	private int pageSize;//每页显示记录数，配置注入
	private List<String> privileges;//权限显示
	private int totalPages;//总页数
	@Resource
	private RoleDAO roleDAO;//注入dao
	
	@Transactional(readOnly=true)
	public String execute(){		
		try {
			roles = roleDAO.findPage(page, pageSize);
			privileges = new ArrayList<String>();
			for (int i = 0; i < roles.size(); i++) {
				StringBuilder str = new StringBuilder();
				int n = 0;
				for (Integer privilegeId:roles.get(i).getPrivilegeIds()) {
					if ( n > 0) {
						str.append("、");
					}
					//获取权限id对应的权限名
					str.append(PrivilegeReader.getModuleNameById(privilegeId));
					n++;
				}
				privileges.add(str.toString());
			}
			totalPages = roleDAO.totalPages(pageSize);//获取页数
		} catch (DAOException e) {			
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public List<String> getPrivilege() {
		return privileges;
	}

	public void setPrivilege(List<String> privilege) {
		this.privileges = privilege;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
