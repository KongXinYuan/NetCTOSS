<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>电信计费系统－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=application.getContextPath()%>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=application.getContextPath()%>/styles/global_color.css" />
        <script type="text/javascript" src="<%=application.getContextPath()%>/js/jquery-1.4.3.js" ></script>
        <script type="text/javascript">
        	$(function(){
        		$('#code').blur(function(){
        			$.post(
        				"codeCheck",
        				{"code":$(this).val()},
        				function(date){
        					if(date){
        						$('#code_msg').html("验证码正确");
        					}else{
        						$('#code_msg').html("验证码错误");
        					}
        				}
        			);
        		});
        		
        		$('#image1').click(function(){
        			$(this).attr("src","image?i=" + Math.random());
        			return false;
        		});
        		
        		$('#login_submit').click(function(){
        			if ($('#adminCode').val().length < 1){
        				$('#adminCode').focus();
        				return;
        			}
        			if ($('#password').val().length < 1){
        				$('#password').focus();
        				return;
        			}
        			if ($('#code').val().length < 1){
        				$('#code').focus();
        				return;
        			}
        			$('#loginForm').submit();
        			return false;
        		});
        		
        		//用户名验证结果
        		var loginFlag = '${loginFlag}';
        		if (loginFlag == '1') {
        			$('#login_msg').html("用户名或密码错误，请重试");
        		} 
        		if (loginFlag == '2') {
        			$('#code_msg').html("验证码错误");
        		}
        	});
        </script>
    </head>
    <body class="index">
        <div class="login_box">
        	<form action="login" method="post" id="loginForm">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="adminCode" type="text" class="width150" id="adminCode"/></td>
                    <td class="login_error_info">
                    <!--<span class="required">30长度的字母、数字和下划线</span> -->
                    </td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="password" type="password" class="width150" id="password"/></td>
                    <td>
                    <!-- <span class="required">30长度的字母、数字和下划线</span> -->
                    </td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="code" type="text" class="width70" id="code"/></td>
                    <td><a href="javascrip:;" >
                    	<img src="<%=application.getContextPath()%>/image.action" alt="验证码" title="点击更换" width="75" height="30" id="image1"/>
                    	</a>
                    </td>  
                    <td><span class="required" id="code_msg"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <!--<input type="image" src="images/login_btn.png" id="login_submit" style="width:125px;height:41px;order:0px;"/>-->
                        <img src="<%=application.getContextPath()%>/images/login_btn.png" id="login_submit"/>
                    </td>    
                    <td><span class="required" id="login_msg"></span></td>                
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>
