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
        <script language="javascript" type="text/javascript">
            function deleteRole(i,id) {
                var r = window.confirm("确定要删除此角色吗？");
                if (r){
                	$.post(
                		"role_delete",
                		{"id":id},
                		function(date){
                			if(date){
	                			$(i).parent().parent().remove();
	                			$("#operate_result_info").attr("style","display=block");
	                			$('#result_msg').text("删除成功!");
                			}else {
                				$("#operate_result_info").attr("style","display=block");
                				$('#result_msg').text("删除失败！");;
                			}
                		}
                	);
                }                
            }
            $(function(){
            	$('.role_off').removeClass().addClass("role_on");
            });
        </script>
    </head>
    <body>
         <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='role_add';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="result_msg"></span>
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>
                        <s:iterator value="roles" var="role" status="i">             
                        <tr> 
                            <td>${role.id}</td>
                            <td>${role.name}</td>
                            <td>${privilege[i.index]}</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_modi?id=${role.id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole(this,${role.id});" />
                            </td>
                        </tr>
                        </s:iterator>
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
                <s:if test="totalPages>1">
        	        <s:if test="page>1">
						<a href="role_list?page=${page-1}" >上一页</a>
					</s:if>
					<s:else>
						<a>上一页</a>
					</s:else>
					<s:iterator value="new int[totalPages]" status="i">
						<s:if test="page == #i.count">
							<a href="role_list?page=${i.count}" class="current_page">${i.count}</a>
						</s:if>
						<s:else>
							<a href="role_list?page=${i.count}">${i.count}</a>
						</s:else>
					</s:iterator>
					<s:if test="page<totalPages">
						<a href="role_list?page=${page+1}">下一页</a>
					</s:if>
					<s:else>
						<a>下一页</a>
					</s:else>
					</s:if>
                </div>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[多数的错失，是因为不坚持，不努力，不挽留，然后催眠自己说一切都是命运。 ]</p>
            <p>版权所有(C)中国朱氏集团公司 </p>
        </div>
    </body>
</html>
