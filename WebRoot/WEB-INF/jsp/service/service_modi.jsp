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
            //保存成功的提示信息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
            
           	$(function(){
           		$('.service_off').removeClass().addClass("service_on");
           		var updateFlag = '${requestScope.updateFlag}';
           		if (updateFlag == "1") {
           			$('#save_result_info').text("资费修改成功").removeClass().addClass("save_success");
           			showResult();
           		}else if (updateFlag == "2"){
           			showResult();
           		}
           	});
        </script>
    </head>
    <body>
    	<!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">资费修改失败！数据并发错误。</div>
            <s:form action="service_modi" method="post" cssClass="main_form" id="serviceForm" theme="simple">
                <!--必填项-->
                <s:hidden name="service.id"></s:hidden>
                <div class="text_info clearfix"><span>业务账号ID：</span></div>
                <div class="input_info">
                    <s:textfield readonly="true" cssClass="readonly" name="id"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>OS 账号：</span></div>
                <div class="input_info">
                    <s:textfield readonly="true" cssClass="readonly" name="service.osUsername"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <s:textfield readonly="true" cssClass="readonly" name="service.unixHost"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                	<s:select list="costs" listKey="id" listValue="name" name="service.cost.id"></s:select>
                    <div class="validate_msg_long">请修改资费类型，或者取消修改操作。</div>                      
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save"  />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='service_list';"/>
                </div>

                
                <p>业务说明：<br />
                1、修改资费后，点击保存，并未真正修改数据库中的数据；<br />
                2、提交操作到消息队列；<br />
                3、每月月底由程序自动完成业务所关联的资费；</p>
                
            </s:form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
