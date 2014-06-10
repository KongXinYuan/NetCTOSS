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

            
            $(function(){
            	//account验证
            	var accountFlag = false;
            	$('#search_btn').click(function(){
            		var $this = $(this);
            		$.post(
            			"service_account",
            			{"idcard":$('#idcard').val()},
            			function(data){
            				if(data.loginName != null && data.loginName != ""){
            					$this.next().next().removeClass("error_msg").text("");
            					$('#loginName').val(data.loginName);
            					$('#accountId').val(data.accountId);
            					accountFlag = true;
            				}else{
            					$this.next().next().addClass("error_msg").text("没有此身份证号，请重新录入。");
            					accountFlag = false;
            				}
            			},
            			"json"
            		);
            	});
            	
            	$('#loginName').blur(function(){
            		$.post(
            			"service_checkAccount",
            			{"idcard":$('#idcard').val(),"loginName":$('#loginName').val()},
            			function(date){
            				if(date){
            					$('#loginName').next().next().text("").removeClass("error_msg");
            					accountFlag = true;           					
            				} else {
            					$('#loginName').next().next().text("没有此账务账号，请重新录入。").addClass("error_msg");
            					accountFlag = false;
            				}
            			}
            		);
            	});
            	//ip验证
            	var ipFlag = false;
            	$('#unixHost').blur(function(){
            		var $msg = $(this).next().next(); 
            		$msg.text("15 长度，符合IP的地址规范");           		
            		if($(this).IPCheck()){            			
            			$msg.removeClass("error_msg");
            		}else{
            			$msg.addClass("error_msg");
            			ipFlag = false;
            			return;
            		}
            		$msg.text("检查是否存在...");
            		$.post(
            				"service_unixHostCheck",
            				{"unixHost":$('#unixHost').val()},
            				function(date){
            					if(date){
            						$msg.text("服务器可用！").removeClass("error_msg");
            						ipFlag = true;
            					}else{
            						$msg.text("服务器不存在！ ").addClass("error_msg");
            						ipFlag = false;
            					}
            				}
            			);
            	});
            	//os账号验证
            	var osFlag = false;
            	$('#osUsername').blur(function(){
            		$msg = $(this).next().next();
            		$msg.text("验证中...").removeClass("error_msg");
            		if($(this).rangeLength(1,8)){
            			$.post(
            				"service_OsCheck",
            				{"unixHost":$('#unixHost').val(),"osUsername":$('#osUsername').val()},
            				function(date){
            					if(!date){
            						$msg.text("账号可用").removeClass("error_msg");
            						osFlag = true;
            					}else{
            						$msg.text("该服务器上已经开通过 同名OS账号！ ").addClass("error_msg");
            						osFlag = false;
            					}
            				});
            		}else{
            			$msg.text("8长度以内的字母、数字和下划线的组合").addClass("error_msg");
            			osFlag = false;
            		}
            	});
            	//密码验证
            	var pwdFlag = false;
            	$('#passwd').blur(function(){
            		$msg = $(this).next().next();
            		if($(this).rangeLength(1,30)){
            			pwdFlag = true;
            			$msg.removeClass("error_msg");
            		} else {
            			pwdFlag = false;
            			$msg.addClass("error_msg");
            		}
            	});
            	//重复密码
            	var repeatPwdFlag = false;
            	$('#repeatPwd').blur(function(){
            		$msg = $(this).next().next();
            		if($(this).equalsTo('#passwd')){
            			repeatPwdFlag = true;
            			$msg.removeClass("error_msg");
            		} else {
            			repeatPwdFlag = false;
            			$msg.addClass("error_msg");
            		}
            	});
            	
            	$('#save_btn').click(function(){            	
            		var flag = accountFlag && ipFlag && osFlag && pwdFlag && repeatPwdFlag;
            		if(flag){
            			$('#serviceForm').submit();
            		}else{
            			alert("信息未全部通过验证，请检查！");
            		}
            	});
            	
            	$('.service_off').removeClass().addClass("service_on");
            	var addFlag = '${addFlag}';
            	if (addFlag == '1'){
            		$('#save_result_info').text("保存成功!").removeClass().addClass("save_success");
            		showResult();
            	}else if (addFlag == '2'){
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
            <div id="save_result_info" class="save_fail">保存失败！</div>
            <s:form action="service_add" method="post" class="main_form" theme="simple" id="serviceForm">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" class="width180" id="idcard"/>
                    <input type="button" value="查询账务账号" class="btn_search_large" id="search_btn"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short"></div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input type="text" id="loginName"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long"></div>
                    <input type="hidden" id="accountId" name="service.account.id"/>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                     <s:select list="costs" listKey="id" listValue="name" name="service.cost.id"></s:select>              
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input type="text" name="service.unixHost" id="unixHost"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input type="text"  name="service.osUsername" id="osUsername"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password"  name="service.loginPasswd" id="passwd"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password"  id="repeatPwd"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="save_btn"/>
                    <input type="button" value="取消" class="btn_save" onclick="location.href='service_list';"/>
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
