<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>电信计费系统－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js" ></script>
        <script type="text/javascript" src="../js/jquery.validate.js"></script>
        <script type="text/javascript" src="../js/accountCheck.js" charset="utf-8"></script>
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

            //显示修改密码的信息项
            function showPwd(chkObj) {
                if (chkObj.checked)
                    document.getElementById("divPwds").style.display = "block";
                else
                    document.getElementById("divPwds").style.display = "none";
            }
            
            function testflag(){
            	$('.account_off').removeClass().addClass("account_on");
            	var saveflag = '${updateFlag}';
            	if (saveflag == "1") {
            		$('#save_result_info').html("保存成功!").removeClass("save_fail").addClass("save_success");
            		showResult();
            	}
            	if (saveflag == "2") {
            		showResult();
            	}
            	if (saveflag == "3") {
            		$('#save_result_info').html("保存失败，密码错误！");
            		showResult();
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
            <!--保存成功或者失败的提示消息-->          
            <div id="save_result_info" class="save_fail">保存失败！</div>
            <s:form action="account_modi" method="post" cssClass="main_form" theme="simple" id="accountForm">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.id" readonly="true" cssClass="readonly" ></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.realName" id="realName"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.idcardNo" readonly="true" cssClass="readonly" ></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.loginName" readonly="true" cssClass="readonly"  ></s:textfield>                        
                        <div class="change_pwd">
                            <input id="chkModiPwd" type="checkbox" onclick="showPwd(this);" name="changePwd" value="1"/>
                            <label for="chkModiPwd">修改密码</label>
                        </div>
                    </div>
                    <!--修改密码部分-->
                    <div id="divPwds">
                        <div class="text_info clearfix"><span>旧密码：</span></div>
                        <div class="input_info">
                            <input type="password"  name="oldpwd" id="oldPasswd"/>
                            <span class="required">*</span>
                            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>新密码：</span></div>
                        <div class="input_info">
                            <input type="password"  name="newpwd" id="loginPasswd"/>
                            <span class="required">*</span>
                            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <input type="password"  id="repeatPwd"/>
                            <span class="required">*</span>
                            <div class="validate_msg_long">两次密码必须相同</div>
                        </div>  
                    </div>                   
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.telephone" id="telephone" cssClass="width200" ></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">                    
                        <s:textfield name="recommenderIdcardNo" id="reIdcardNo"></s:textfield>
                        <s:hidden name="account.recommenderId" id="recommenderId"></s:hidden>
                        <div class="validate_msg_long error_msgs">正确的身份证号码格式</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.birthdate"  cssClass="readonly" id="birthdate"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.email" cssClass="width200" id="email"></s:textfield>
                        <div class="validate_msg_medium">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <s:select list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}" name="account.occupation" 
                		disabled="true" cssClass="readonly"></s:select>                 
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <s:radio list="#{'1':'女','0':'男'}" disabled="true" name="account.gender" cssClass="readonly"></s:radio>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.mailaddress" cssClass="width350" id="mailaddress"></s:textfield>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.zipcode" id="zipcode"></s:textfield>
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <s:textfield name="account.qq" id="qq"></s:textfield>
                        <div class="validate_msg_long">5到13位数字</div>
                    </div>
                    <s:hidden name="account.createDate"></s:hidden>
                    <s:hidden name="account.pauseDate"></s:hidden>
                    <s:hidden name="account.status"></s:hidden>
                    <s:hidden name="account.lastLoginTime"></s:hidden>
                    <s:hidden name="account.lastLoginIp"></s:hidden>
                    <!--操作按钮-->
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" id="modi_btn"/>
                        <input type="button" value="取消" class="btn_save" onclick="location.href='account_list';"/>
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
