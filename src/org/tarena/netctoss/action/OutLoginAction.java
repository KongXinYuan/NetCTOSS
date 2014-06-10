package org.tarena.netctoss.action;

import org.springframework.stereotype.Controller;
/**出去登录*/
@Controller
public class OutLoginAction extends BaseAction {
	public String execute(){
		session.put("admin", null);//清除用户登录记录
		session.put("urls", null);//清除权限记录
		return "success";
	}
}
