package org.tarena.netctoss.pojo;

/**
 * RolePrivilegeId entity. @author MyEclipse Persistence Tools
 */

public class RolePrivilegeId implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private Integer privilegeId;

	// Constructors

	/** default constructor */
	public RolePrivilegeId() {
	}

	/** full constructor */
	public RolePrivilegeId(Integer roleId, Integer privilegeId) {
		this.roleId = roleId;
		this.privilegeId = privilegeId;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolePrivilegeId))
			return false;
		RolePrivilegeId castOther = (RolePrivilegeId) other;

		return (this.getRoleId() == castOther.getRoleId())
				&& (this.getPrivilegeId() == castOther.getPrivilegeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRoleId();
		result = 37 * result + this.getPrivilegeId();
		return result;
	}

}