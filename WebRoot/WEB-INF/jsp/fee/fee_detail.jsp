<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js" ></script>
        <script type="text/javascript">
        	$(function(){
        		$('.fee_off').removeClass().addClass("fee_on");
        	});
        </script>    
    </head>
    <body>
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
           
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info">
                	<s:textfield name="cost.id" cssClass="readonly" readonly="true" ></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                	<s:textfield name="cost.name" cssClass="readonly" readonly="true" ></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                	<s:select list="#{'0':'开通','1':'暂停'}" disabled="true" name="cost.status"></s:select>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                	<s:radio list="#{'0':'包月','1':'套餐','2':'计时'}" disabled="true" name="cost.costType"></s:radio>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseDuration" cssClass="readonly" readonly="true" ></s:textfield>
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseCost" cssClass="readonly" readonly="true" ></s:textfield>
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.unitCost" cssClass="readonly" readonly="true" ></s:textfield>
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info">
                	<s:textfield name="cost.createTime" cssClass="readonly" readonly="true" ></s:textfield>
                </div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info">
                	<s:textfield name="cost.startTime" cssClass="readonly" readonly="true" ></s:textfield>
                </div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea cssClass="width300 height70 readonly" readonly="true" name="cost.descr"></s:textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="history.back();" />
                </div>
              
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
