package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.pojo.ReportCostUsed;
import org.tarena.netctoss.pojo.ReportSumDuration;

public interface ReportDAO {
	//分页查询unix服务器host统计资费使用情况
	public List<ReportCostUsed> findHostPage(Integer page, Integer pageSize) throws DAOException;
	//unix服务器页数
	public Integer totalPagesHost(Integer pageSize) throws DAOException;
	//从host表中取出全部unixId
	public List<String> findAllHost() throws DAOException;
	//分页查询用户使用时长
	public List<ReportSumDuration> findSumDurationPage(Integer page, Integer pageSize) throws DAOException;
	//用户使用时长记录页数
	public Integer totalPagesSumDuration(Integer pageSize) throws DAOException;
	//取出指定服务器时长前3位
	public List<ReportSumDuration> findThreeDuration(String unixHost) throws DAOException;

	
}
