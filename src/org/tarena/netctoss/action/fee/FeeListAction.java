package org.tarena.netctoss.action.fee;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.CostDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Cost;
/**处理资费查询列表的请求*/
@Controller
@Scope("prototype")
public class FeeListAction extends BaseAction{
	private List<Cost> costs;//资费列表集合
	private int pageSize;//每页记录数，配置注入
	private int page = 1;//当前页
	private int totalPages;//总页数
	private int sortNo = 0;//排序编号1x：月租，2x：基费，3x：时长；x1：升序，x2：降序
	@Resource
	private CostDAO costDAO;//dao注入
	
	@Transactional(readOnly=true)
	public String execute() {	
		try {
			//获得页数
			totalPages = costDAO.totalPages(pageSize);
			//获得资费信息集合
			costs = costDAO.findPage(page, pageSize, sortNo);			
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public void setCostDAO(CostDAO costDAO) {
		this.costDAO = costDAO;
	}
	
}
