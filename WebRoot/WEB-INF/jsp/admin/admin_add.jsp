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
            //保存成功的提示消息
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
            function testflag(){
            	$('.admin_off').removeClass().addClass("admin_on");
            	var saveflag = '${addFlag}';
            	if (saveflag == "1") {
            		$('#save_result_info').html("保存成功!").removeClass("save_fail").addClass("save_success");
            		showResult();
            	}
            	if (saveflag == "2") {
            		showResult();
            	}
            }
            var nameFlag = false;
            function nameCheck(i){
            	var $msg = $(i).next().next();
            	$msg.removeClass("error_msg");
            	if ($(i).ChineseCheck(1,20)){            		
            		nameFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		nameFlag = false;
            	}
            }
            var adminFlag = false;
            function adminCheck(i){
            	var $msg = $(i).next().next();
            	$msg.html("30长度以内的字母、数字和下划线的组合").removeClass("error_msg");
            	if (!$(i).rangeLength(1,30)){
            		$msg.addClass("error_msg");
            		adminFlag = false;
            		return;
            	}
            	$msg.html("验证中...");
            	$.post(
            		"admin_adminCheck",
            		{"adminCode":$(i).val()},
            		function(date){
            			if (date) {
            				$msg.html("账号可用");
            				adminFlag = true;
            			} else {
            				$msg.html("账号重复").addClass("error_msg");
            				adminFlag = false;
            			}
            		}
            	);
            }
            
            var pwdFlag = false;
            function pwdCheck(i){
            	var $msg = $(i).next().next();
            	$msg.removeClass("error_msg");
            	if ($(i).rangeLength(1,30)){            		
            		pwdFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		pwdFlag = false;
            	}
            }
            
            var repeatFlag = false;
            function repeatCheck(i){
            	var $msg = $(i).next().next();
            	$msg.removeClass("error_msg");
            	if ($(i).equalsTo('#password')){            		
            		repeatFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		repeatFlag = false;
            	}
            }
            
            var telFlag = false;
            function telephoneCheck(i){
            	var $msg = $(i).next().next();
            	$msg.removeClass("error_msg");
            	if ($(i).telephoneCheck()){            		
            		telFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		telFlag = false;
            	}
            }
            var emailFlag = false;
            function emailCheck(i){
            	var $msg = $(i).next().next();
            	$msg.removeClass("error_msg");
            	if ($(i).emailCheck()){            		
            		emailFlag = true;
            	} else {
            		$msg.addClass("error_msg");
            		emailFlag = false;
            	}
            }
            
             var checkedFlag = false;
            function checkedCheck(){
            	if ($('input:checked').length < 1){
            		checkedFlag = false;
            		$('#role_msg').addClass("error_msg");
            	} else {
            		$('#role_msg').removeClass("error_msg");
            		checkedFlag = true;
            	}
            }
            function submitResult(){
 //           	alert(checkedFlag +","+ nameFlag +","+ adminFlag+","+ pwdFlag +","+ repeatFlag +","+ telFlag +","+emailFlag);         	
            	var flag = checkedFlag && nameFlag && adminFlag && pwdFlag && repeatFlag && telFlag && emailFlag;            	
            	if (flag){
            		$('#adminForm').submit();
            	} else {
            		alert("信息有误，请检查！");
            	}
            }
        </script>
    </head>
    <body onload="testflag();">
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
            <s:form action="admin_add" method="post" class="main_form" id="adminForm" theme="simple">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" onblur="nameCheck(this);" name="admin.name"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                        <input type="text" onblur="adminCheck(this);" name="admin.adminCode"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                    <div class="input_info">
                        <input type="password"  onblur="pwdCheck(this);" name="admin.password" id="password"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>重复密码：</span></div>
                    <div class="input_info">
                        <input type="password"  onblur="repeatCheck(this);"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">两次密码必须相同</div>
                    </div>      
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" onblur="telephoneCheck(this);" name="admin.telephone"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" onblur="emailCheck(this);" name="admin.email"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium">50长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                            <ul>
                        	<s:iterator value="roles" var="role">
                        	 <li><input type="checkbox" value="${role.id}" name="roleIds" onclick="checkedCheck();"/>${role.name}</li>
                        	</s:iterator>                            
                        </ul>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny" id="role_msg">至少选择一个</div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" onclick="submitResult();" />
                        <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
                    </div>
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
