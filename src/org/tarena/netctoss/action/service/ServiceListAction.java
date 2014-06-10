package org.tarena.netctoss.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDAO;
import org.tarena.netctoss.pojo.Service;
/**处理业务账号列表显示请求*/
@Controller
@Scope("prototype")
public class ServiceListAction {
	private String osUsername;//osUsername查询信息
	private String unixHost;//服务器Ip查询信息
	private String idcard;//身份证查询信息
	private String status = "-1";//状态查询信息，-1为全部
	private int page = 1;//当前页
	private int pageSize;//每页显示记录数，配置注入
	private int totalPages;//页数
	private List<Service> services;//业务账号列表集合
	@Resource
	private ServiceDAO serviceDAO;//dao注入
	
	@Transactional(readOnly=true)
	public String execute(){
		try {
			//获取业务账号信息集合
			services = serviceDAO.findPage(osUsername, unixHost, idcard, status, page, pageSize);
			//获取页数
			totalPages = serviceDAO.totalPages(osUsername, unixHost, idcard, status, pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public String getOsUsername() {
		return osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
	
}
