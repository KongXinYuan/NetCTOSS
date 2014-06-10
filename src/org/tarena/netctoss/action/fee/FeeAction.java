package org.tarena.netctoss.action.fee;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.netctoss.action.BaseAction;
import org.tarena.netctoss.dao.CostDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Cost;
/**
 * 封装对资费操作的请求
 * nameCheck 处理资费名检查请求
 * add 处理添加请求
 * detail 处理显示详单请求
 * modi 处理修改请求
 * delete 处理删除请求
 * start 处理开启资费请求
 * */
@Controller
@Scope("prototype")
public class FeeAction extends BaseAction {
	private Cost cost;//资费信息
	private boolean ok = false;//操作状态
	private String name;//资费名
	private int id;//资费id
	@Resource
	private CostDAO costDao;
	private Date date;
	//处理资费名检查请求
	@Transactional(readOnly=true)
	public String nameCheck(){		
		try {
			Cost cost = costDao.findByName(name);
			if (cost == null)
				ok = true;
		} catch (DAOException e) {
			e.printStackTrace();
			return "ok";
		}
		return "ok";
	}
	//处理添加请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String add() {
		//无添加信息直接进了添加页面 
		if(cost==null){
			return "success";
		}
		//有添加信息执行添加操作
		cost.setStatus("1");//默认暂停状态
		cost.setCreateTime(new Date(System.currentTimeMillis()));
		try {
			//检查是否重名
			if(costDao.findByName(cost.getName()) != null){
				request.put("addFlag", 2);//重名返回保存失败
				return "success";
			}		
			costDao.insert(cost);
			request.put("addFlag", 1);
		} catch (DAOException e) {			
			e.printStackTrace();
			request.put("addFlag", 2);
			return "success";
		}
		return "success";		
	}
	//处理显示详单请求
	@Transactional(readOnly=true)
	public String detail() {		
		return load();
	}
	//处理修改请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String modi(){
		//无修改信息就载入资费信息
		if (cost == null) {
			return load();
		}
		//有修改信息进行修改操作
		try {			
			costDao.update(cost);
			request.put("updateFlag", 1);
		} catch (DAOException e) {			
			e.printStackTrace();
			request.put("updateFlag", 2);
		}		
		return "success";
	
	}
	//载入资费信息
	public String load() {		
		try {
			cost = costDao.findById(id);
		} catch (DAOException e) {			
			e.printStackTrace();
			return "error";
		}		
		return "success";
	}
	//处理删除请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String delete() {		
		try {
			costDao.delete(id);
			ok = true;				
		} catch (DAOException e) {			
			e.printStackTrace();
			return "ok";
		}
		return "ok";
	}
	//处理开启资费请求
	@Transactional(propagation=Propagation.REQUIRED)
	public String start(){
		try {
			Cost cost = costDao.findById(id);
			cost.setStatus("0");
			date = new Date(System.currentTimeMillis());
			cost.setStartTime(date);
			costDao.update(cost);
			ok = true;
		} catch (DAOException e) {			
			e.printStackTrace();
			return "start";
		}
		return "start";
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCostDao(CostDAO costDao) {
		this.costDao = costDao;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
