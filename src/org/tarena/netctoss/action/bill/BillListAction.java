package org.tarena.netctoss.action.bill;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.BillDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Bill;
/**处理账单列表请求*/
@Controller
public class BillListAction {
	private List<Bill> bills;//账单列表
	private String idcardNo;//身份证信息
	private String loginName;//账务账号
	private String realName;//真实姓名
	private Integer year;//年份
	private Integer month;//月份
	private Integer pageSize;//每页记录数，属性注入
	private	Integer page = 1;//当前页
	private Integer totalPages;//总页数
	@Resource
	private BillDAO billDAO;//dao注入
	@Transactional(readOnly=true)
	public String execute(){
		try {
			bills = billDAO.findPage(idcardNo, loginName, realName, year, month, page, pageSize);
			for (Bill bill:bills) {
				String str = bill.getBillMonth();
				String month = str.substring(0,4) + "年" + str.substring(4) + "月";
				bill.setBillMonth(month);
			}
			totalPages = billDAO.totalPages(idcardNo, loginName, realName, year, month, pageSize);
		} catch (DAOException e) {			
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public void setBillDAO(BillDAO billDAO) {
		this.billDAO = billDAO;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
}
