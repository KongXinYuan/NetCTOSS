<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>电信计费系统－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js" ></script>
        <script type="text/javascript">
        	$(function(){
        		$('.account_off').removeClass().addClass("account_on");
        	});
        </script>
    </head>
    <body>
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="post" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><input type="text" value="${account.id}" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info"><input type="text" value="${account.realName}" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.idcardNo}" readonly="readonly" class="readonly" />
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.loginName}" readonly="readonly" class="readonly" />
                </div>                   
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" class="width200 readonly" readonly="readonly" value="${account.telephone}" />
                </div>
                <div class="text_info clearfix"><span>推荐人账务账号ID：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${account.recommenderId == 0?'':account.recommenderId}" /></div>
                <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${recommenderIdcardNo}" /></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                    <s:select list="#{'0':'开通','1':'暂停','2':'删除'}" disabled="true" name="account.status"></s:select>                                        
                </div>                    
                <div class="text_info clearfix"><span>开通/暂停/删除时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="2013/01/21 12:00:00" /></div>
                <div class="text_info clearfix"><span>上次登录时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${account.lastLoginTime}" /></div>
                <div class="text_info clearfix"><span>上次登录IP：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${account.lastLoginIp}" /></div>
                <!--可选项数据-->
                <div class="text_info clearfix"><span>生日：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly" value="${account.birthdate}" />
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input type="text" class="width350 readonly" readonly="readonly" value="${account.email}" />
                </div> 
                <div class="text_info clearfix"><span>职业：</span></div>
                <div class="input_info">
                	<s:select list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}" name="account.occupation" 
                	disabled="true" cssClass="readonly"></s:select>
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type">
                	<s:radio list="#{'1':'女','0':'男'}" disabled="true" name="account.gender" cssClass="readonly"></s:radio>                    
                </div> 
                <div class="text_info clearfix"><span>通信地址：</span></div>
                <div class="input_info"><input type="text" class="width350 readonly" readonly="readonly" value="${account.mailaddress}" /></div> 
                <div class="text_info clearfix"><span>邮编：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly="readonly" value="${account.zipcode}" /></div> 
                <div class="text_info clearfix"><span>QQ：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly="readonly" value="${acount.qq}" /></div>                
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="history.back();" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
