package org.tarena.netctoss.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<AdminInfo> admins = new HashSet<AdminInfo>();
	private List<Integer> privilegeIds = new ArrayList<Integer>();
	
	public Role() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AdminInfo> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<AdminInfo> admins) {
		this.admins = admins;
	}

	public List<Integer> getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(List<Integer> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name 
				+ ", PrivilegeIds=" + privilegeIds + "]";
	}

	
}