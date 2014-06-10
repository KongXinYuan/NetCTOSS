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
        <script type="text/javascript" src="../js/feeCheck.js" ></script>
        <script language="javascript" type="text/javascript">
            //保存结果的提示
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
            	$('.fee_off').removeClass().addClass("fee_on");
            	var addFlag = '${addFlag}';
            	if (addFlag == "1"){
            		$('#save_result_info').removeClass("save_fail").addClass("save_success").html("保存成功！");
            		showResult();
            	}
            	if (addFlag == "2"){
            		showResult();
            	}
            }
            
        </script>
    </head>
    <body  onload="test();">
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_fail">保存失败！</div>
            <form action="fee_add" method="post" class="main_form" id="costForm">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="cost.name" id="name"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost.costType" id="costType0" value="0"/>
                    <label for="costType0">包月</label>
                    <input type="radio" name="cost.costType" checked="checked" id="costType1" value="1"/>
                    <label for="costType1">套餐</label>
                    <input type="radio" name="cost.costType" id="costType2" value="2"/>
                    <label for="costType2">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="cost.baseDuration" id="baseDuration"/>
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">1-600之间的整数</div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="cost.baseCost" id="baseCost"/>
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="cost.unitCost" id="unitCost"/>
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">0-999.9999之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="cost.descr" id="descr"></textarea>
                    <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
                </div>        
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  id="save_btn" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='fee_list'"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
