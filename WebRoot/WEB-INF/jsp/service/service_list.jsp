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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //删除
            function deleteAccount(i,id) {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                if (r) {
                	$.post(
                		"service_setStatus",
                		{"id":id,"status":"2",},
                		function(date){
                			if(date){
                				$(i).parent().empty();
                				$('#result_msg').html("账号删除成功！");
                				$('#operate_result_info').attr("style","display:block;").romveClass().addClass("operate_success");
                			}else{
                				$('#result_msg').html("账号删除失败！");
                				$('#operate_result_info').attr("style","display:block;").romveClass().addClass("operate_fail");
                			}
                		}
                	);
                }                
            }
            //开通或暂停
            function setState(i,id,accountId) {
            	var msg = "确定要开通此业务账号吗？";
            	var $s = $(i).parent().prev().prev().prev();
            	if ($(i).val() == "暂停"){
            		status = "1";
            		msg = "确定要暂停此业务账号吗？";
            	}else if($(i).val() == "开通"){
            		status = "0";
            	}
                var r = window.confirm(msg);
                if(r){
                	$.post(
                		"service_setStatus",
                		{"id":id,"status":status,"accountId":accountId},
                		function(date){
                			if(date){
	                			if(status == "1"){
	                				$s.text("暂停");
	                				$(i).removeClass().addClass("btn_start").val("开通");
	                			}else if (status == "0") {
	                				$s.text("开通");
	                				$(i).removeClass().addClass("btn_pause").val("暂停");
	                			}                				
                				$('#result_msg').html("账号操作成功！");
                				$('#operate_result_info').attr("style","display:block;").addClass("operate_success").romveClass("operate_fail");
                			}else{
                				$('#result_msg').html("账号操作失败！请确认账务账号状态！");
                				$('#operate_result_info').attr("style","display:block;").addClass("operate_fail").romveClass("operate_success");
                			}
                		}
                	);
                }
                
            }
            
            function changePage(page){
            	var form = document.getElementById(
        				"searchForm");
        		form.action = "service_list?page="+page;
        		form.submit();//提交搜索表单
        		return false;//阻止href动作
            }
            $(function(){
            	$('.service_off').removeClass().addClass("service_on");
            });
        </script>
    </head>
    <body>
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <s:form action="service_list" method="post" theme="simple" id="searchForm">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<s:textfield cssClass="width100 text_search" name="osUsername"></s:textfield></div>                            
                    <div>服务器 IP：<s:textfield cssClass="width100 text_search" name="unixHost"></s:textfield></div>
                    <div>身份证：<s:textfield cssClass="text_search" name="idcard"></s:textfield></div>
                    <div>状态：
                    	<s:select list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}" cssClass="select_search" name="status"></s:select>                        
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='service_add';" />
                </div> 
                </s:form> 
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                   	<span id="result_msg">删除成功！</span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <s:iterator value="services" var="service">
                    <tr>
                        <td><a href="service_detail?id=${service.id}" title="查看明细">${service.id}</a></td>
                        <td>${service.account.id}</td>
                        <td>${service.account.idcardNo}</td>
                        <td>${service.account.realName}</td>
                        <td>${service.osUsername}</td>
                        <td>
                        	<s:if test="#service.status == 0">开通</s:if>
                        	<s:elseif test="#service.status == 1">暂停</s:elseif>
                        	<s:elseif test="#service.status == 2">删除</s:elseif>
                        </td>
                        <td>${service.unixHost}</td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${service.cost.name}</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                ${service.cost.descr}
                            </div>
                        </td>                            
                        <td class="td_modi">
                        	<s:if test="#service.status != 2">
                        	<s:if test="#service.status == 0">
									<input type="button" value="暂停" class="btn_pause" onclick="setState(this,${service.id},${service.account.id});" />
							</s:if>
							<s:elseif test="#service.status == 1">
									<input type="button" value="开通" class="btn_start" onclick="setState(this,${service.id},${service.account.id});" />
							</s:elseif>
                            
                            <input type="button" value="修改" class="btn_modify" onclick="location.href='service_modi?id=${service.id}';" />
                            <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(this,${service.id},${service.account.id});" />
                            </s:if>
                        </td>
                    </tr>
                    </s:iterator>                                                        
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
		            <s:if test="totalPages > 1">
					<a href="#" onclick="changePage(1);">首页</a>
					<s:if test="page>1">
						<a href="#" onclick="changePage(${page-1});">上一页</a>
					</s:if>
					<s:else>
						<a>上一页</a>
					</s:else>
					<s:if test="totalPages < 5">
						<s:iterator value="new int[totalPages]" status="i">
							<s:if test="page == #i.count">
								<a href="#" class="current_page" onclick="changePage(${i.count});">${i.count}</a>
							</s:if>
							<s:else>
								<a href="#" onclick="changePage(${i.count});">${i.count}</a>
							</s:else>
						</s:iterator>
					</s:if>
					<s:else>
						<s:if test="page < 3">
							<s:iterator value="new int[5]" status="i">
								<s:if test="page == #i.count">
									<a href="#" class="current_page" onclick="changePage(${i.count});">${i.count}</a>
								</s:if>
								<s:else>
									<a href="#" onclick="changePage(${i.count});">${i.count}</a>
								</s:else>
							</s:iterator>
						</s:if>
						<s:elseif test="page >= totalPages - 2">
							<s:iterator value="new int[5]" status="i">
								<s:if test="page == #i.count + totalPages - 5">
									<a href="#" class="current_page" onclick="changePage(${i.count+totalPages-5});">${i.count+totalPages-5}</a>
								</s:if>
								<s:else>
									<a href="#" onclick="changePage(${i.count+totalPages-5});">${i.count+totalPages-5}</a>
								</s:else>
							</s:iterator>
						</s:elseif>
						<s:else>
							<a href="#" onclick="changePage(${page-2});">${page-2}</a>
							<a href="#" onclick="changePage(${page-1});">${page-1}</a>
							<a href="#" class="current_page" onclick="changePage(${page});">${page}</a>
							<a href="#" onclick="changePage(${page+1});">${page+1}</a>
							<a href="#" onclick="changePage(${page+2});">${page+2}</a>
						</s:else>
					</s:else>
					<s:if test="page<totalPages">
						<a href="#" onclick="changePage(${page+1});">下一页</a>
					</s:if>
					<s:else>
						<a>下一页</a>
					</s:else>
					<a href="#" onclick="changePage(${totalPages});">末页</a>
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
