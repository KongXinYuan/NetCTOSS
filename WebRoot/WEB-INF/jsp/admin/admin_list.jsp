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
            //重置密码
            function resetPwd() {
            	if($('input:checked').length < 1){
            		alert("请至少选择一条数据！");
            		return;
            	}
                var codes = new Array();
                $('input:checked').each(function(i){
                	codes[i] = $(this).val();
                });
                $.ajax({
                	url:"admin_resetPwd",
                	data:{"adminCodes":codes},
                	cache:false,
                	traditional:true,
                	success:function(date){
                		if (date) {
                			$("#operate_result_info").attr("style","display:block").removeClass().addClass("operate_success");
                			$('#result_msg').html("密码重置成功！");
                		} else {
                			$("#operate_result_info").attr("style","display:block").removeClass().addClass("operate_fail");
                			$('#result_msg').html("密码重置失败！数据并发错误。");
                		}
                	}
                });
                
            }
            //删除
            function deleteAdmin(id,i) {
                var r = window.confirm("确定要删除此管理员吗？");
                if (r) {
                	$.post(
                		"admin_delete",
                		{"id":id},
                		function(date){
                			if(date) {
                				$("#operate_result_info").attr("style","display:block").removeClass().addClass("operate_success");
                				$('#result_msg').html("删除成功！");
                				$(i).parent().parent().remove();
                			} else {
	                			$("#operate_result_info").attr("style","display:block").removeClass().addClass("operate_fail");
	                			$('#result_msg').html("删除失败！数据并发错误。");
                		}
                		}
                	);
                }
                
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }
            
            function changePage(page){
            	var form = document.getElementById(
        				"searchForm");
        		form.action = "admin_list?page="+page;
        		form.submit();//提交搜索表单
        		return false;//阻止href动作
            }
            $(function(){
            	$('.admin_off').removeClass().addClass("admin_on");
            });             
        </script>       
    </head>
    <body>
       	<!--Logo、导航区域开始-->
	    <%@include file="/WEB-INF/jsp/header.jsp" %>
	    <!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="admin_list" method="post" id="searchForm">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <s:select list="privileges" listKey="id" listValue="moduleName" id="selModules" cssClass="select_search" name="privilegeId">
                        </s:select>
                    </div>
                    <div>角色：<input type="text" value="" class="text_search width200" name="roleName"/></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='admin_add';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="result_msg"></span>
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" value="no"/>
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <s:iterator value="admins" var="admin" status="i">                    
                        <tr>
                            <td><input type="checkbox" value="${admin.adminCode}"/></td>
                            <td>${admin.id }</td>
                            <td>${admin.name }</td>
                            <td>${admin.adminCode}</td>
                            <td>${admin.telephone}</td>
                            <td>${admin.email}</td>
                            <td>${admin.enrolldate}</td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${shortRoleNames[i.index]}</a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                    ${roleNames[i.index]}
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='admin_modi?id=${admin.id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${admin.id},this);" />
                            </td>
                        </tr>
                        </s:iterator>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                <s:if test="totalPages > 1">
        	        <s:if test="page>1">
				<a href="#" onclick="changePage(${page-1});">上一页</a>
			</s:if>
			<s:else>
				<a>上一页</a>
			</s:else>
			<s:iterator value="new int[totalPages]" status="i">
				<s:if test="page == #i.count">
					<a href="#" class="current_page" onclick="changePage(${i.count});">${i.count}</a>
				</s:if>
				<s:else>
					<a href="#" onclick="changePage(${i.count});">${i.count}</a>
				</s:else>
			</s:iterator>
			<s:if test="page<totalPages">
				<a href="#" onclick="changePage(${page+1});">下一页</a>
			</s:if>
			<s:else>
				<a>下一页</a>
			</s:else>
			</s:if>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
