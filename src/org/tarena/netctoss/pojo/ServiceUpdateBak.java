package org.tarena.netctoss.pojo;

import java.util.Date;

/**
 * ServiceUpdateBak entity. @author MyEclipse Persistence Tools
 */

public class ServiceUpdateBak implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer serviceId;
	private String unixHost;
	private String osUsername;
	private Integer costId;
	private Date creatTime;

	// Constructors

	/** default constructor */
	public ServiceUpdateBak() {
	}
	
	public ServiceUpdateBak(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ServiceUpdateBak(Integer serviceId, String unixHost,
			String osUsername, Integer costId, Date creatTime) {
		this.serviceId = serviceId;
		this.unixHost = unixHost;
		this.osUsername = osUsername;
		this.costId = costId;
		this.creatTime = creatTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getUnixHost() {
		return this.unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getOsUsername() {
		return this.osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}

	public Integer getCostId() {
		return this.costId;
	}

	public void setCostId(Integer costId) {
		this.costId = costId;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

}