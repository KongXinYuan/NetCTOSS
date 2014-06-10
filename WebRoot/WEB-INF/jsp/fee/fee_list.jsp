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
            //排序按钮的点击事件
            function sort(btnObj,no) {
                if (btnObj.className == "sort_desc") {
                    $('#sort').val(no + 1);
                }else{                    
                    $('#sort').val(no + 2);                    
                }
                $('#form').submit();
            }

            //启用
            function startFee(i,id) {            	
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if(r){
                	$.post(
                		"fee_start",
                		{"id":id},
                		function(data){
                			if(data.ok){
                				$(i).parent().prev().html("开通");
                				$(i).parent().prev().prev().html(data.date);
                				$(i).parent().empty();                				
                				$('#operate_result_info').attr("style","display:block;");
                				$('#operate_msg').text("账号启用成功！");
                				$('#operate_result_info').addClass("operate_success").removeClass("operate_fail");
                			}else{
                				$('#operate_result_info').attr("style","display:block;");
                				$('#operate_msg').text("账号启用失败！");
                				$('#operate_result_info').addClass("operate_fail").removeClass("operate_success"); 
                			}
                		}
                	);
                	
                }                
            }
            //删除
            function deleteFee(i,id) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){
                	$.post(
                		"fee_delete",
                		{"id":id},
                		function(date){
                			if(date){
                				$(i).parent().parent().remove();
                				$('#operate_result_info').attr("style","display:block;");
                				$('#operate_msg').text("删除成功！");
                				$('#operate_result_info').addClass("operate_success").removeClass("operate_fail");               				
                			}else{
                				$('#operate_result_info').attr("style","display:block;");
                				$('#operate_msg').text("删除失败！");
                				$('#operate_result_info').addClass("operate_fail").removeClass("operate_success"); 
                			}
                		}
                	); 
                }               
            }
            $(function(){
            	$('.fee_off').removeClass().addClass("fee_on");;
            	var sortNo = '${sortNo}';
	            	if (sortNo == 12){
	            		$('#sort1').removeClass().addClass("sort_desc");
	            	}
	            	if (sortNo == 22){
	            		$('#sort2').removeClass().addClass("sort_desc");
	            	}
	            	if (sortNo == 32){
	            		$('#sort3').removeClass().addClass("sort_desc");
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
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input type="button" value="月租" class="sort_asc" onclick="sort(this,10);" id="sort1" />                        
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this,20);" id="sort2"/>
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this,30);" id="sort3"/>
                        <s:form action="fee_list" method="post" theme="simple" id="form">
                        <s:hidden name="sortNo" id="sort"></s:hidden>
                        </s:form>
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='fee_add';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="<%=application.getContextPath()%>/images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="operate_msg">删除成功！</span>
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                        <s:iterator value="costs" var="cost">            
                        <tr>
                            <td>${cost.id}</td>
                            <td><a href="fee_detail?id=${cost.id}">${cost.name}</a></td>
                            <td>${cost.baseDuration} 小时</td>
                            <td>${cost.baseCost} 元</td>
                            <td>${cost.unitCost} 元/小时</td>
                            <td class="width150"><s:date name="#cost.createTime" format="yyyy-MM-dd hh:mm:ss" /></td>
                            <td class="width150"><s:date name="#cost.startTime" format="yyyy-MM-dd hh:mm:ss" /></td>
                            <td>
                            	<s:if test="#cost.status == 0">开通</s:if>
                            	<s:elseif test="#cost.status == 1">暂停</s:elseif>
                            </td>
                            <td>
                            	<s:if test="#cost.status == 1">                             
                                <input type="button" value="启用" class="btn_start" onclick="startFee(this,${cost.id});"/>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='fee_modi?id=${cost.id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee(this,${cost.id});" />
                            	</s:if> 
                            </td>
                        </tr>
                        </s:iterator> 
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                <s:if test="totalPages > 1">
                	<s:if test="page > 1">
                		<a href="fee_list?page=${page-1}&sortNo=${sortNo}">上一页</a>
                	</s:if>
        	    	<s:else>
        	    		<a>上一页</a>
        	    	</s:else>
        	         <s:iterator value="new int[totalPages]" status="i">
        	         <s:if test="#i.count == page">
        	         	<a href="fee_list?page=${i.count}&sortNo=${sortNo}" class="current_page">${i.count}</a>
        	         </s:if>
        	         <s:else>
        	         	<a href="fee_list?page=${i.count}&sortNo=${sortNo}">${i.count}</a>
        	         </s:else>
                     </s:iterator>
                   <s:if test="page < totalPages">
                   <a href="fee_list?page=${page+1}&sortNo=${sortNo}">下一页</a>
                   </s:if>
                   <s:else> <a>下一页</a>
                   </s:else>
                </s:if>
                </div>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
