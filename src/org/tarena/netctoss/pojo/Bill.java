package org.tarena.netctoss.pojo;

import java.util.HashSet;
import java.util.Set;

public class Bill implements java.io.Serializable {

	private Integer id;
	private Account account;
	private String billMonth;
	private Double cost;
	private String paymentMode;
	private String payState;
	private Set<BillItem> billItems = new HashSet<BillItem>(0);

	public Bill() {
	}

	public Bill(Account account, String billMonth, Double cost) {
		this.account = account;
		this.billMonth = billMonth;
		this.cost = cost;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBillMonth() {
		return this.billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPayState() {
		return this.payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public Set<BillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<BillItem> billItems) {
		this.billItems = billItems;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", account=" + account + ", billMonth="
				+ billMonth + ", cost=" + cost + ", paymentMode=" + paymentMode
				+ ", payState=" + payState  + "]";
	}

	
}