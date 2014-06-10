package org.tarena.netctoss.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Account implements java.io.Serializable {

	private Integer id;
	private Integer recommenderId;
	private String loginName;
	private String loginPasswd;
	private String status;
	private Date createDate;
	private Date pauseDate;
	private Date closeDate;
	private String realName;
	private String idcardNo;
	private Date birthdate;
	private String gender;
	private String occupation;
	private String telephone;
	private String email;
	private String mailaddress;
	private String zipcode;
	private String qq;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Set<Bill> bills = new HashSet<Bill>(0);
	private Set<Service> services = new HashSet<Service>(0);

	public Account() {
	}

	public Account(String loginName, String loginPasswd, String realName,
			String idcardNo, String telephone) {
		this.loginName = loginName;
		this.loginPasswd = loginPasswd;
		this.realName = realName;
		this.idcardNo = idcardNo;
		this.telephone = telephone;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(Integer recommenderId) {
		this.recommenderId = recommenderId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdcardNo() {
		return this.idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public String getMailaddress() {
		return this.mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", recommenderId=" + recommenderId
				+ ", loginName=" + loginName + ", loginPasswd=" + loginPasswd
				+ ", status=" + status + ", createDate=" + createDate
				+ ", pauseDate=" + pauseDate + ", closeDate=" + closeDate
				+ ", realName=" + realName + ", idcardNo=" + idcardNo
				+ ", birthdate=" + birthdate + ", gender=" + gender
				+ ", occupation=" + occupation + ", telephone=" + telephone
				+ ", email=" + email + ", mailaddress=" + mailaddress
				+ ", zipcode=" + zipcode + ", qq=" + qq + ", lastLoginTime="
				+ lastLoginTime + ", lastLoginIp=" + lastLoginIp + "]";
	}
}