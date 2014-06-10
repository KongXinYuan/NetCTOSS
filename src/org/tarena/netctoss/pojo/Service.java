package org.tarena.netctoss.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Service implements java.io.Serializable {

	private Integer id;
	private Cost cost;
	private Account account;
	private String unixHost;
	private String osUsername;
	private String loginPasswd;
	private String status;
	private Date createDate;
	private Date pauseDate;
	private Date closeDate;
	private Set<ServiceDetail> serviceDetails = new HashSet<ServiceDetail>(0);
	private Set<BillItem> billItems = new HashSet<BillItem>(0);

	public Service() {
	}

	public Service(Cost cost, Account account, String unixHost,
			String osUsername, String loginPasswd, String status) {
		this.cost = cost;
		this.account = account;
		this.unixHost = unixHost;
		this.osUsername = osUsername;
		this.loginPasswd = loginPasswd;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cost getCost() {
		return this.cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String getLoginPasswd() {
		return this.loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPauseDate() {
		return this.pauseDate;
	}

	public void setPauseDate(Date pauseDate) {
		this.pauseDate = pauseDate;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Set<ServiceDetail> getServiceDetails() {
		return this.serviceDetails;
	}

	public void setServiceDetails(Set<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public Set<BillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<BillItem> billItems) {
		this.billItems = billItems;
	}

	@Override
	public String toString() {
		return "Service [id=" + id  + ", unixHost="
				+ unixHost + ", osUsername=" + osUsername + ", loginPasswd="
				+ loginPasswd + ", status=" + status + ", createDate="
				+ createDate + ", pauseDate=" + pauseDate + ", closeDate="
				+ closeDate + "]";
	}

	
}