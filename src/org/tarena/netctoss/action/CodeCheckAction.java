package org.tarena.netctoss.action;

import org.springframework.stereotype.Controller;

@Controller
public class CodeCheckAction extends BaseAction{
	private String code;
	private boolean ok = false;
	public String execute(){
		String realCode = (String)session.get("code");
		if(realCode.equalsIgnoreCase(code)){
			ok = true;
			return "success";
		}
		return "success";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
