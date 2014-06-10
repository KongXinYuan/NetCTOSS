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
            	var saveflag = '${updateFlag}';
            	if (saveflag == "1") {
            		showResult();
            	}
            	if (saveflag == "2") {
            		$('#save_result_info').html("保存失败!").removeClass("save_success").addClass("save_fail");
            		showResult();
            	}
            }
            
            var nameFlag = true;
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
            var telFlag = true;
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
            var emailFlag = true;
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
            var checkedFlag = true;
            function checkedCheck(){
            	if ($('input:checked').length < 1){
            		checkedFlag = false;
            		$('#role_msg').addClass("error_msg");
            	} else {
            		checkedFlag = true;
            		$('#role_msg').removeClass("error_msg");
            	}
            }
            
            function submitResult(){            	
            	var flag = nameFlag && checkedFlag && telFlag && emailFlag;            	
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
            <s:form action="admin_modi" method="post" class="main_form" theme="simple" id="adminForm">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.name}" name="admin.name" onblur="nameCheck(this);" />
                        <span class="required">*</span>
                        <div class="validate_msg_long">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <s:hidden name="admin.password"></s:hidden>
                    <s:hidden name="admin.enrolldate"></s:hidden>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${admin.adminCode}" name="admin.adminCode" /></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.telephone}" name="admin.telephone" onblur="telephoneCheck(this);"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" value="${admin.email}" name="admin.email" onblur="emailCheck(this);"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium">50长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                            <ul>
                             <s:iterator value="roles" var="role">
                        	 <s:if test="#role.id in roleIds"> 
                        	 <li><input type="checkbox" value="${role.id}" name="roleIds" checked="checked" onclick="checkedCheck();"/>${role.name}</li>                     
                        	 </s:if>
                        	 <s:else>
                        	 <li><input type="checkbox" value="${role.id}" name="roleIds" onclick="checkedCheck();"/>${role.name}</li>
                        	 </s:else>
                        	 </s:iterator>                        	 
                            </ul>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny" id="role_msg">至少选择一个</div>
                        <s:hidden name="admin.id"></s:hidden>
                    </div>
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" onclick="submitResult();" />
                        <input type="button" value="取消" class="btn_save" onclick="location.href='admin_list'"/>
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
