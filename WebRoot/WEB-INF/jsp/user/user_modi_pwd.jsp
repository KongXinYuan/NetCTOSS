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
             	$('.password_off').removeClass().addClass("password_on");
            	var updateFlag = '${updateFlag}';
            	if (updateFlag == "1"){            		
            		showResult();
            	}
            	if (updateFlag == "2"){
            		$('#save_result_info').removeClass("save_success").addClass("save_fail").html("保存失败，旧密码错误！");
            		showResult();
            	}
            }
            var odlFlag = false;
            function oldCheck(i){
            	var $msg = $(i).next().next();
            	if($(i).rangeLength(1,30)){
            		$msg.removeClass("error_msg");
            		oldFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		oldFlag = false;
            	}
            }
            
            var newFlag = false;
            function newCheck(i){
            	var $msg = $(i).next().next();
            	if($(i).rangeLength(1,30)){
            		$msg.removeClass("error_msg");
            		newFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		newFlag = false;
            	}
            }
            
            var repeatFlag = false;
            function repeatCheck(i){
            	var $msg = $(i).next().next();
            	if($(i).rangeLength(1,30)){
            		$msg.removeClass("error_msg");
            		repeatFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		repeatFlag = false;
            	}
            }
            
            function submitResult(){
            	var flag = oldFlag && newFlag && repeatFlag;            	
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
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="user_modi_pwd" method="post" class="main_form" id="userForm">
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200" onblur="oldCheck(this);" name="oldPwd"/><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password"  class="width200" onblur="newCheck(this);" name="newPwd"/><span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password" class="width200"  onblur="repeatCheck(this);"/><span class="required">*</span>
                    <div class="validate_msg_medium">两次新密码必须相同</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitResult();" />
                    <input type="reset" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
