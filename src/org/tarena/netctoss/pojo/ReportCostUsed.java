package org.tarena.netctoss.pojo;
/**报表中各个服务器各资费使用情况*/
public class ReportCostUsed implements java.io.Serializable{
	private String host;
	private int monthlyCount;
	private int packageCount;
	private int timeBasedCount;
	
	public ReportCostUsed(){
	}
	public ReportCostUsed(String host){
		this.host = host;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getMonthlyCount() {
		return monthlyCount;
	}
	public void setMonthlyCount(int monthlyCount) {
		this.monthlyCount = monthlyCount;
	}
	public int getPackageCount() {
		return packageCount;
	}
	public void setPackageCount(int packageCount) {
		this.packageCount = packageCount;
	}
	public int getTimeBasedCount() {
		return timeBasedCount;
	}
	public void setTimeBasedCount(int timeBasedCount) {
		this.timeBasedCount = timeBasedCount;
	}
	@Override
	public String toString() {
		return "ReportCostUsed [host=" + host + ", monthlyCount="
				+ monthlyCount + ", packageCount=" + packageCount
				+ ", timeBasedCount=" + timeBasedCount + "]";
	}	
}
