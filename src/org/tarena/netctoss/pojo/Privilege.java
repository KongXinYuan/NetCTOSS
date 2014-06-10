package org.tarena.netctoss.pojo;

import java.util.List;

public class Privilege implements java.io.Serializable {

	private int id;
	
	private String moduleName;
	
	private List<String> urls;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
