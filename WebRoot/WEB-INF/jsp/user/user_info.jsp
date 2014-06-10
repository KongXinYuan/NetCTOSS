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
        <script type="text/javascript" src="../js/jquery.validate.js"></script>
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
            
             function test(){
             	$('.information_off').removeClass().addClass("information_on");
            	var updateFlag = '${updateFlag}';
            	if (updateFlag == "1"){
            		$('#save_result_info').removeClass("save_fail").addClass("save_success").html("保存成功！");
            		showResult();
            	}
            	if (updateFlag == "2"){
            		showResult();
            	}
            }
            var nameFlag = true;
            function nameCheck(i){
            	var $msg = $(i).next().next();
            	if($(i).ChineseCheck(1,20)){
            		$msg.removeClass("error_msg");
            		nameFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		nameFlag = false;
            	}
            }
            var telFlag = true;
            function telephoneCheck(i){
            	var $msg = $(i).next();
            	if($(i).telephoneCheck()){
            		$msg.removeClass("error_msg");
            		telFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		telFlag = false;
            	}
            }
            var emailFlag = true; 
            function emailCheck(i){
            	var $msg = $(i).next();
            	if($(i).emailCheck()){
            		$msg.removeClass("error_msg");
            		emailFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		emailFlag = false;
            	}
            }
            
            function submitResult(){
            	alert(emailFlag+","+telFlag+","+nameFlag);
            	var flag = emailFlag && telFlag && nameFlag;
            	if (flag) {
            		$('#userForm').submit();
            	}else{
            		alert("信息填写有误，请检查！");
            	}
            }
        </script>
    </head>
    <body onload="test();">
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，数据并发错误！-->
            <s:form action="user_info" method="post" class="main_form" theme="simple" id="userForm">
            	<input type="hidden" value="${admin.id}" name="admin.id"/>
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${admin.adminCode}" /></div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly width400" value="${roleName}" />
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" value="${admin.name}" name="admin.name" onblur="nameCheck(this);"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">20长度以内的汉字、字母的组合</div>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" value="${admin.telephone}" name="admin.telephone" onblur="telephoneCheck(this);"/>
                    <div class="validate_msg_medium">电话号码格式：手机或固话</div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" value="${admin.email}" name="admin.email" onblur="emailCheck(this);"/>
                    <div class="validate_msg_medium">50长度以内，符合 email 格式</div>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${admin.enrolldate}"/></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitResult();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </s:form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>            
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
