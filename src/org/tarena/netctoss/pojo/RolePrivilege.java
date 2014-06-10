package org.tarena.netctoss.pojo;

/**
 * RolePrivilege entity. @author MyEclipse Persistence Tools
 */

public class RolePrivilege implements java.io.Serializable {

	// Fields

	private RolePrivilegeId id;

	// Constructors

	/** default constructor */
	public RolePrivilege() {
	}

	/** full constructor */
	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	// Property accessors

	public RolePrivilegeId getId() {
		return this.id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}

}