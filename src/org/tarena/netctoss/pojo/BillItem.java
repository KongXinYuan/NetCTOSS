package org.tarena.netctoss.pojo;

public class BillItem implements java.io.Serializable {

	private Integer itemId;
	private Service service;
	private Bill bill;
	private Double cost;

	public BillItem() {
	}

	public BillItem(Service service, Bill bill) {
		this.service = service;
		this.bill = bill;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "BillItem [itemId=" + itemId + ", serviceId=" + service.getId()
				+ ", billId=" + bill.getId() + ", cost=" + cost + "]";
	}

	
	
}