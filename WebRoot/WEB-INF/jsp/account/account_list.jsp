<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>电信计费系统－NetCTOSS</title>
<link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all"	href="../styles/global_color.css" />
<script type="text/javascript"	src="../js/jquery-1.4.3.js"></script>
<script language="javascript" type="text/javascript">
            //删除
            function deleteAccount(i,id) {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                if (r){
                	$.post(
                		"account_delete",
                		{"id":id},
                		function(date){
                			if(date){
	                			$(i).parent().prev().prev().prev().html("删除");
	                			$(i).parent().html("");
	                			$("#operate_result_info").attr("style","display=block").text("删除成功，且已删除其下属的业务账号！");
                			}else {
                				$("#operate_result_info").attr("style","display=block").text("删除失败！");;
                			}
                		});
                }                
            }
            //开通或暂停
            function setState(i,id) {
             	var status = "";
             	var r = false;
                if($(i).val() == "暂停"){
                	status = "1";//为暂停时改状态为0（开通）
                	r = window.confirm("确定要暂停此账务账号吗？");
                }
                 if($(i).val() == "开通"){
                	status = "0";//为开通时改状态为1（暂停）
                	r = window.confirm("确定要开通此账务账号吗？");
                }
                
                
                if (r) {               
                	$.post(
                		"account_setStatus",
                		{"id":id,"status":status},
                		function(date){
                			if(date){
                				if(status == "0"){
                					$(i).parent().prev().prev().prev().html("开通");
									$(i).val("暂停").addClass("btn_pause").removeClass("btn_start");
								}
								if(status == "1"){
									$(i).parent().prev().prev().prev().html("暂停");									
									$(i).val("开通").addClass("btn_start").removeClass("btn_pause");
								}
                			}
                		}
                	);                	
                }                
            }
            function changePage(page){
            	var form = document.getElementById(
        				"searchForm");
        		form.action = "account_list?page="+page;
        		form.submit();//提交搜索表单
        		return false;//阻止href动作
            }
            $(function(){
            	$('.account_off').removeClass().addClass("account_on");
            });
        </script>
</head>
<body>
	<!--Logo、导航区域开始-->
    <%@include file="/WEB-INF/jsp/header.jsp" %>
    <!--Logo、导航区域结束-->
	<!--主要区域开始-->
	<div id="main">
		<s:form action="account_list" method="post" theme="simple"
			id="searchForm">
			<!--查询-->
			<div class="search_add">
				<div>
					身份证：
					<s:textfield name="idcardNo" cssClass="text_search"></s:textfield>
				</div>
				<div>
					姓名：
					<s:textfield name="realName" cssClass="width70 text_search"></s:textfield>
				</div>
				<div>
					登录名：
					<s:textfield name="loginName" cssClass="text_search" />
				</div>
				<div>
					状态：
					<s:select cssClass="select_search"
						list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}" name="status">
					</s:select>
				</div>
				<div>
					<input type="submit" value="搜索" class="btn_search" />
				</div>
				<input type="button" value="增加" class="btn_add"
					onclick="location.href='account_add';" />
			</div>
		</s:form>
		<!--删除等的操作提示-->
		<div id="operate_result_info" class="operate_success">
			<img src="../images/close.png"
				onclick="this.parentNode.style.display='none';" />
			删除成功，且已删除其下属的业务账号！
		</div>
		<!--数据区域：用表格展示数据-->
		<div id="data">
			<table id="datalist">
				<tr>
					<th>账号ID</th>
					<th>姓名</th>
					<th class="width150">身份证</th>
					<th>登录名</th>
					<th>状态</th>
					<th class="width100">创建日期</th>
					<th class="width150">上次登录时间</th>
					<th class="width200"></th>
				</tr>

				<s:iterator value="accounts" status="i" var="account">
					<tr>
						<td>${account.id}</td>
						<td><a href="account_detail?id=${account.id}">${account.realName}</a>
						</td>
						<td>${account.idcardNo}</td>
						<td>${account.loginName}</td>
						<td><s:if test="#account.status == 0">
                        	开通
                        </s:if> <s:elseif test="#account.status == 1">
                        	暂停
                        </s:elseif> <s:elseif test="#account.status == 2">
                        	删除
                        </s:elseif>
						</td>
						<td>${account.createDate}</td>
						<td>${account.lastLoginTime}</td>
						<td class="td_modi"><s:if test="#account.status != 2">
								<s:if test="#account.status == 1">
									<input type="button" value="开通" class="btn_start"
										onclick="setState(this,${account.id});" />
								</s:if>
								<s:elseif test="#account.status == 0">
									<input type="button" value="暂停" class="btn_pause"
										onclick="setState(this,${account.id});" />
								</s:elseif>
								<input type="button" value="修改" class="btn_modify" onclick="location.href='account_modi?id=${account.id}'"/>
								<input type="button" value="删除" class="btn_delete"
									onclick="deleteAccount(this,${account.id});" />
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
			<p>
				业务说明：<br /> 1、创建则开通，记载创建时间；<br /> 2、暂停后，记载暂停时间；<br />
				3、重新开通后，删除暂停时间；<br /> 4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
				5、暂停账务账号，同时暂停下属的所有业务账号；<br />
				6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
				7、删除账务账号，同时删除下属的所有业务账号。
			</p>
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
		<p>版权所有(C)加拿大达内IT培训集团公司</p>
	</div>
</body>
</html>
