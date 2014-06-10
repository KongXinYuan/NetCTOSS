package org.tarena.netctoss.action.bill;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDAO;
import org.tarena.netctoss.dao.ServiceDetailDAO;
import org.tarena.netctoss.pojo.Service;
import org.tarena.netctoss.pojo.ServiceDetail;

@Controller
public class BillServiceDetailAction {
	private List<ServiceDetail> serviceDetails;//详单列表
	private Service service;//service显示信息
	private Integer serviceId;//查找条件
	private Integer page = 1;//当前页
	private Integer pageSize;//每页显示记录数，配置注入
	private Integer totalPages;//总页数
	private Integer cost;//总费用
	private Integer billId;//关联的billId
	private String month;//查找月份
	private String monthStr;
	@Resource
	private ServiceDetailDAO serviceDetailDAO;
	@Resource
	private ServiceDAO serviceDAO;
	public String execute(){
		try {
			serviceDetails = serviceDetailDAO.findPage(serviceId, month, page, pageSize);
			totalPages = serviceDetailDAO.totalPages(serviceId, month, pageSize);
			service = serviceDAO.findByid(serviceId);
			monthStr = month.substring(0,4) + "年" + month.substring(4) + "月";
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public List<ServiceDetail> getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setServiceDetailDAO(ServiceDetailDAO serviceDetailDAO) {
		this.serviceDetailDAO = serviceDetailDAO;
	}
	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public String getMonthStr() {
		return monthStr;
	}
	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}
	
	
}
