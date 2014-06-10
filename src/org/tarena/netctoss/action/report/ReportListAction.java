package org.tarena.netctoss.action.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ReportDAO;
import org.tarena.netctoss.pojo.ReportCostUsed;
import org.tarena.netctoss.pojo.ReportSumDuration;

@Controller
public class ReportListAction {
	private String tag = "tag1";//tag1使用时长，tag2时长排行榜，tag3资费使用率
	private List<ReportCostUsed> reportCostUseds;//存资费使用情况列表信息
	private Integer page = 1;//当前页
	private Integer pageSize;//每页显示记录数
	private Integer totalPages;//总页数
	private List<ReportSumDuration> reportSumDurations;//用户使用时长列表信息
	private List<String> months;//月份的格式显示
	private List<String> durations;//时长的格式显示
	@Resource
	private ReportDAO reportDAO;
	public String execute(){		
			try {
				if ("tag1".equals(tag)) {
					reportSumDurations = reportDAO.findSumDurationPage(page, pageSize);
					totalPages = reportDAO.totalPagesSumDuration(pageSize);		
					months = new ArrayList<String>();
					durations = new ArrayList<String>();
					for (ReportSumDuration rsd: reportSumDurations) {
						months.add(rsd.getMonth().substring(0, 4) + "年" + rsd.getMonth().substring(4) + "月");
						int n = rsd.getDuration();
						durations.add((n / 3600) + "小时" + (n / 60 % 60) + "分钟" + (n % 60) + "秒");
					}
										
				} else if ("tag2".equals(tag)) {
					List<String> hosts = reportDAO.findAllHost();
					List<ReportSumDuration> list = new ArrayList<ReportSumDuration>();
					for (String host:hosts) {
						list.addAll(reportDAO.findThreeDuration(host));
					}
					if (list.size() % pageSize == 0) {
						totalPages = list.size() / pageSize;
					} else {
						totalPages = list.size() / pageSize + 1;
					}
					if ((page * pageSize) < list.size()) {
						reportSumDurations = list.subList((page - 1) * pageSize , page * pageSize);						
					} else {
						reportSumDurations = list.subList((page - 1) * pageSize, list.size());	
					}
					durations = new ArrayList<String>();
					for (ReportSumDuration rsd: reportSumDurations) {
						int n = rsd.getDuration();
						durations.add((n / 3600) + "小时" + (n / 60 % 60) + "分钟" + (n % 60) + "秒");
					}
				} else if("tag3".equals(tag)) {					
					reportCostUseds = reportDAO.findHostPage(page, pageSize);
					totalPages = reportDAO.totalPagesHost(pageSize);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				return "error";
			}		
		return "success";
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<ReportCostUsed> getReportCostUseds() {
		return reportCostUseds;
	}
	public void setReportCostUseds(List<ReportCostUsed> reportCostUseds) {
		this.reportCostUseds = reportCostUseds;
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
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public List<ReportSumDuration> getReportSumDurations() {
		return reportSumDurations;
	}
	public void setReportSumDurations(List<ReportSumDuration> reportSumDurations) {
		this.reportSumDurations = reportSumDurations;
	}
	public List<String> getMonths() {
		return months;
	}
	public void setMonths(List<String> months) {
		this.months = months;
	}
	public List<String> getDurations() {
		return durations;
	}
	public void setDurations(List<String> durations) {
		this.durations = durations;
	}		
	
}
