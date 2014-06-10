package org.tarena.netctoss.pojo;

import java.util.Date;

public class ServiceDetail implements java.io.Serializable {

	private Integer id;
	private Service service;
	private String clientHost;
	private String osUsername;
	private Integer pid;
	private Date loginTime;
	private Date logoutTime;
	private Integer duration;
	private Double cost;

	public ServiceDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getClientHost() {
		return this.clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getOsUsername() {
		return this.osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}