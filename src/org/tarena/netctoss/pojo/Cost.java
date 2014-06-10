package org.tarena.netctoss.pojo;


import java.util.Date;


public class Cost implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer costType;
	private Integer baseDuration;
	private Double baseCost;
	private Double unitCost;
	private String status;
	private String descr;
	private Date createTime;
	private Date startTime;

	public Cost() {
	}

	public Cost(String name, String status) {
		this.name = name;
		this.status = status;
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

	public Integer getCostType() {
		return this.costType;
	}

	public void setCostType(Integer costType) {
		this.costType = costType;
	}

	public Integer getBaseDuration() {
		return this.baseDuration;
	}

	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}

	public Double getBaseCost() {
		return this.baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Cost [id=" + id + ", name=" + name + ", costType=" + costType
				+ ", baseDuration=" + baseDuration + ", baseCost=" + baseCost
				+ ", unitCost=" + unitCost + ", status=" + status + ", descr="
				+ descr + ", createTime=" + createTime + ", startTime="
				+ startTime + "]";
	}
	
	
}