package org.tarena.netctoss.interceptor;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeCheckInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		Set<String> urls = (Set<String>) session.get("urls");
		String url = invocation.getInvocationContext().getName();
		if (urls != null) {
			if (urls.contains(url)){
				invocation.invoke();
			} else {
				return "nopower";
			}
		}
		return "login";
		
	}

}
