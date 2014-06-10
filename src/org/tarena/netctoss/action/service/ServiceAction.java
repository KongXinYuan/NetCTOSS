package org.tarena.netctoss.action.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.AccountDAO;
import org.tarena.netctoss.dao.CostDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDAO;
import org.tarena.netctoss.dao.ServiceUpdateBakDAO;
import org.tarena.netctoss.pojo.Account;
import org.tarena.netctoss.pojo.Cost;
import org.tarena.netctoss.pojo.Service;
import org.tarena.netctoss.pojo.ServiceUpdateBak;
/**
 * 封装对业务账号的处理请求
 * add 处理添加请求
 * setStatus 处理暂停，开启，删除请求
 * modi 处理修改请求
 * detail 处理显示详单请求
 * account	获取acouut信息
 * checkAccount 处理检查account信息请求
 * OsCheck	处理检查os账号请求
 * unixHostCheck 处理检查unixHost请求
 * */
@Controller
@Scope("prototype")
public class ServiceAction extends BaseAction{	
	private boolean ok = false;//检查或请求处理状态
	private Integer accountId;
	private Integer id;//service Id
	private String status;//状态
	private List<Cost> costs;//资费列表选项
	private Service service;
	private String idcard;
	private String loginName;
	private String unixHost;
	private String osUsername;
	@Resource
	private ServiceDAO serviceDAO;
	@Resource
	private ServiceUpdateBakDAO serviceUpdateBakDAO;
	@Resource
	private CostDAO costDAO;
	@Resource
	private AccountDAO accountDAO;
		
	//处理修改请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String modi(){
		try {
			costs = costDAO.findAll();//获取下拉选择项（开启状态的资费被列出）
		} catch (DAOException e1) {
			e1.printStackTrace();
			return "error";
		}
		//未提交修改信息
		if (service == null){
			try {
				service = serviceDAO.findByid(id);	
			} catch (DAOException e) {
				e.printStackTrace();
				return "error";
			}
		return "success";
		}
		//获得修改信息
		try {
			Service s = serviceDAO.findByid(id);
			//检查修改信息是否和原信息相同
			if(s.getCost().getId().equals(service.getCost().getId())){
				//相同就查找更新列表中有没需更新的信息
				ServiceUpdateBak sub = serviceUpdateBakDAO.findByServiceId(id);
				//有需更新信息就删除，即取消原修改操作
				if (sub != null){
					serviceUpdateBakDAO.delete(sub.getId());
				}
				//无需更新信息就无操作
				
			}else{
				//修改信息是否和原信息不相同，查看是否已有修改信息
				ServiceUpdateBak sub = serviceUpdateBakDAO.findByServiceId(id);
				if (sub != null){
					//有修改信息就把该修改信息修改
					sub.setCostId(service.getCost().getId());
					serviceUpdateBakDAO.update(sub);
				}else{
					//没有修改信息就添加修改信息
					ServiceUpdateBak sub2 = new ServiceUpdateBak(id, service.getUnixHost(), service.getOsUsername(), 
							service.getCost().getId(), new Date(System.currentTimeMillis()));
					serviceUpdateBakDAO.insert(sub2);
				}
			}
			request.put("updateFlag", 1);
		} catch (DAOException e) {
			e.printStackTrace();
			request.put("updateFlag", 2);
		}		
		return "success";
	}
	//处理显示详单请求
	@Transactional(readOnly=true)
	public String detail(){
		try {
			service = serviceDAO.findByid(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "success";
		}
		return "success";
	}
	//处理添加请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String add(){
		try {
			costs = costDAO.findAll();//获取可选资费信息
		} catch (DAOException e1) {
			e1.printStackTrace();
			return "error";
		}
		//无添加信息直接进入添加页面
		if(service == null){
			return "success";
		}
		//有添加信息进行添加操作
		try {
			serviceDAO.insert(service);
			request.put("addFlag", 1);
		} catch (DAOException e) {
			e.printStackTrace();
			request.put("addFlag", 2);
		}
		return "success";
	}
	//处理获取account部分信息的请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String account(){
		try {
			//通过身份证号获取accountId和loginName
			Account account = accountDAO.findByIdcard(idcard);
			if (account != null){
				accountId = account.getId();
				loginName = account.getLoginName();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "json";
		}
		return "json";
	}
	//处理检查account信息的请求
	@Transactional(readOnly=true)
	public String checkAccount(){
		try {
			Account account = accountDAO.findByIdcard(idcard);
			if (account != null){
				if (account.getLoginName().equals(loginName)){
					ok = true;
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "ok";
		}
		return "ok";
	}
	//处理开启，暂停请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String setStatus(){
		try {
			//如果开启业务账号
			if("0".equals(status)){
				//检查关联的账务账号是否开启
				Account account = accountDAO.findById(accountId);
				if (account != null){
					//如果帐务账号不为开启
					if(!"0".equals(account.getStatus())){
						//返回开启失败信息
						return "ok";
					}
				}			
			}
			//非开启请求或账务账号为开启，就更新业务账号状态信息
			serviceDAO.setStatusById(id, status);
			ok = true;			
		} catch (DAOException e) {
			e.printStackTrace();
			ok = false;
		}
		return "ok";
	}
	//处理检查os账号请求
	@Transactional(readOnly=true)
	public String OsCheck(){
		try {
			if(serviceDAO.existHostOs(unixHost, osUsername)){
				ok = true;
			}
		} catch (DAOException e) {			
			e.printStackTrace();
		}
		return "ok";
	}
	//处理检查unixHost请求
	@Transactional(readOnly=true)
	public String unixHostCheck() {
		try {
			if(serviceDAO.existHost(unixHost)){
				ok = true;
			}
		} catch (DAOException e) {			
			e.printStackTrace();
		}
		return "ok";
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getOsUsername() {
		return osUsername;
	}

	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}
	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
	
	public void setServiceUpdateBakDAO(ServiceUpdateBakDAO serviceUpdateBakDAO) {
		this.serviceUpdateBakDAO = serviceUpdateBakDAO;
	}
	public void setCostDAO(CostDAO costDAO) {
		this.costDAO = costDAO;
	}
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
		

}
