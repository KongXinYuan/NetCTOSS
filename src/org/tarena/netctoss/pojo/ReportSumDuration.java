package org.tarena.netctoss.pojo;

public class ReportSumDuration {
	private String id;
	private String loginName;
	private String realName;
	private String idcardNo;
	private String telephone;
	private String month;
	private Integer duration;
	private String unixHost;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public void setDuration(String duration) {
		this.duration = Integer.parseInt(duration);
	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	
}
