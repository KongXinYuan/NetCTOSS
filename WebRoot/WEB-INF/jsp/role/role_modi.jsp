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
            $(function(){
            	$('.role_off').removeClass().addClass("role_on");
            	var addFlag = '${updateFlag}';
            	if (addFlag == "1"){
            		showResult();
            	} else if (addFlag == "2") {
            		$('#save_result_info').html("保存失败！").removeClass().addClass("save_fail");
            		showResult();
            	}
            	<%session.setAttribute("updateFlag", null);%>
            	//检查
            	$('#name').blur(function(){
            		var $msg = $(this).next().next();
            		var name = $('#name').val();
            		if (!$('#name').ChineseCheck(1,20)){
            			$msg.addClass("error_msg").html("不能为空，且为20长度的字母、数字和汉字的组合");
            			nameFlag = false;
            			return;
            		}
            		$msg.removeClass("error_msg").html("验证中....");
            		$.post(
            			"role_nameCheck",
            			{"name":name},
            			function(date){
            				if (date) {
            					$msg.html("名字可用！");
            					nameFlag = true;
            				} else {
            					$msg.addClass("error_msg").html("名字重复！");
            					nameFlag = false;
            				}
            			}
            		);
            	});
            });
            var nameFlag = true;
            function submitForm(){
            	var boxFlag = $('input:checked').length > 0;
            	if (boxFlag){
            		$('#privilege_msg').removeClass("error_msg");
            	} else {
            		$('#privilege_msg').addClass("error_msg");
            	}
            	
            	var flag = boxFlag && nameFlag;
            	if (flag){
            		$('#roleForm').submit();
            	} else {
            		alert("信息填写有误，请检查！");
            	}
            	
            }
        </script>
    </head>
    <body>
         <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <s:form action="role_modi" method="post" class="main_form" id="roleForm" theme="simple">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" value="${role.name}" name="role.name" id="name"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>
                <s:hidden name="id"></s:hidden>
                <s:hidden name="role.id"></s:hidden>
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                        	<s:iterator value="privileges" var="p">
                        	<s:if test="#p.id in role.privilegeIds"> 
                           		<li><input name="role.privilegeIds" type="checkbox" value="${p.id }" checked="checked" onclick="checkedCheck();"/>${p.moduleName }</li>                        	
                        	 </s:if>
                        	 <s:else>
                        	 	<li><input name="role.privilegeIds" type="checkbox" value="${p.id }" onclick="checkedCheck();"/>${p.moduleName }</li>
                        	 </s:else>
                        	 </s:iterator>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="privilege_msg">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitForm();" />
                    <input type="reset" value="取消" class="btn_save" onclick="location.href='role_list';"/>
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
