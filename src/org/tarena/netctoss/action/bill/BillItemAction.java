package org.tarena.netctoss.action.bill;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.BillDAO;
import org.tarena.netctoss.dao.BillItemDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Bill;
import org.tarena.netctoss.pojo.BillItem;

@Controller
public class BillItemAction {
	private List<BillItem> billItems;
	private Integer billId;
	private Integer pageSize;//每页记录数，属性注入
	private	Integer page = 1;//当前页
	private Integer totalPages;//总页数
	private Bill bill;
	private String month;
	private List<String> durations;
	@Resource
	private BillItemDAO billItemDAO;
	@Resource
	private BillDAO billDAO;//dao注入
	
	@Transactional(readOnly=true)
	public String execute(){
		try {
			billItems = billItemDAO.findPage(billId, page, pageSize);
			totalPages = billItemDAO.totalPage(billId, pageSize);
			bill = billDAO.findId(billId);			
			durations = new ArrayList<String>();
			for (BillItem bi:billItems) {
				int n = billItemDAO.duraction(bi.getService().getId(), bill.getBillMonth());
				String str = (n / 3600) + "小时" + (n / 60 % 60) + "分钟" + (n % 60) + "秒";
				durations.add(str);
			}
			month = bill.getBillMonth().substring(0,4) + "年" + bill.getBillMonth().substring(4) + "月";
		} catch (DAOException e) {			
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public List<BillItem> getBillItems() {
		return billItems;
	}
	public void setBillItems(List<BillItem> billItems) {
		this.billItems = billItems;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public void setBillItemDAO(BillItemDAO billItemDAO) {
		this.billItemDAO = billItemDAO;
	}
	public void setBillDAO(BillDAO billDAO) {
		this.billDAO = billDAO;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public List<String> getDurations() {
		return durations;
	}
	public void setDurations(List<String> durations) {
		this.durations = durations;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
}
