<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>电信计费系统－NetCTOSS</title>
         <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script type="text/javascript" src="js/jquery-1.4.3.js" ></script>
    </head>
    <body class="index">
    	<div class="right" >	
		<span style="color:yellow">用户：${session.admin.name}</span>
		<a href="out_login" style="margin-right: 50px;line-height: 61px;font-weight: bold;color:white">[退出]</a>            
		</div>
        <!--导航区域开始-->
        <div id="index_navi">        
            <ul id="menu">
                <li><a href="index" class="index_on"></a></li>
                <s:if test="'role_list' in #session.urls">
                <li><a href="role/role_list" class="role_off"></a></li>
                </s:if>
                <s:if test="'admin_list' in #session.urls">
                <li><a href="admin/admin_list" class="admin_off"></a></li>
                </s:if>
                <s:if test="'fee_list' in #session.urls">
                <li><a href="fee/fee_list" class="fee_off"></a></li>
                </s:if>
                <s:if test="'account_list' in #session.urls">
                <li><a href="account/account_list" class="account_off"></a></li>
                </s:if>
                <s:if test="'service_list' in #session.urls">
                <li><a href="service/service_list" class="service_off"></a></li>
                </s:if>
                <s:if test="'bill_list' in #session.urls">
                <li><a href="bill/bill_list" class="bill_off"></a></li>
                </s:if>
                <s:if test="'report_list' in #session.urls">
                <li><a href="report/report_list" class="report_off"></a></li>
                </s:if>
                <li><a href="user/user_info" class="information_off"></a></li>
                <li><a href="user/user_modi_pwd" class="password_off"></a></li>
            </ul>
        </div>
    </body>
</html>
