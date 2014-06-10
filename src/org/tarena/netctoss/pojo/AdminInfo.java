package org.tarena.netctoss.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AdminInfo implements java.io.Serializable {

	private Integer id;
	private String adminCode;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Date enrolldate;
	private Set<Role> roles = new HashSet<Role>(0);

	public AdminInfo() {
	}

	public AdminInfo(String adminCode, String password, String name,
			Date enrolldate) {
		this.adminCode = adminCode;
		this.password = password;
		this.name = name;
		this.enrolldate = enrolldate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminCode() {
		return this.adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrolldate() {
		return this.enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", adminCode=" + adminCode
				+ ", password=" + password + ", name=" + name + ", telephone="
				+ telephone + ", email=" + email + ", enrolldate=" + enrolldate
				+ "]";
	}	
	
	
}