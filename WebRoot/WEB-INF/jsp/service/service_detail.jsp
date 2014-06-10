<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>        
        <script language="javascript" type="text/javascript">
            $(function(){
            	$('.service_off').removeClass().addClass("service_on");
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
                <div class="text_info clearfix"><span>业务账号ID：</span></div>
                <div class="input_info"><input type="text" value="${service.id}" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><input type="text" value="${service.account.id }" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>客户姓名：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${service.account.realName}" /></div>
                <div class="text_info clearfix"><span>身份证号码：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${service.account.idcardNo}" /></div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info"><input type="text" value="${service.unixHost}" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>OS 账号：</span></div>
                <div class="input_info"><input type="text" value="${service.osUsername}" readonly="readonly" class="readonly" /></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                	<s:select list="#{'0':'开通','1':'暂停','2':'删除'}" disabled="true" name="service.status"></s:select>                
                </div>
                <div class="text_info clearfix"><span>开通时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${service.createDate}"/></div>
                <div class="text_info clearfix"><span>资费 ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly="readonly" value="${service.cost.id}"/></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="width200 readonly" value="${service.cost.name }"/></div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70 readonly" readonly="readonly">${service.cost.descr }</textarea>
                </div>  
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
