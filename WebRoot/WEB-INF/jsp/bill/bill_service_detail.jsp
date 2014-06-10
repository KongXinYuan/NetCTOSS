<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
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
         $(function(){
            	$('.bill_off').removeClass().addClass("bill_on");
            });
        </script>
    </head>
    <body>
       <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="post">
                <!--查询-->
                <div class="search_add">                        
                    <div>账务账号：<span class="readonly width70">${service.account.loginName }</span></div>                            
                    <div>OS 账号：<span class="readonly width100">${service.osUsername }</span></div>
                    <div>服务器 IP：<span class="readonly width100">${service.unixHost }</span></div>
                    <div>计费时间：<span class="readonly width70">${monthStr}</span></div>
                    <div>费用：<span class="readonly width70">${cost}</span></div>
                    <input type="button" value="返回" class="btn_add" onclick="location.href='bill_item?billId=${billId}';" />
                </div>  
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="width150">客户登录 IP</th>
                            <th class="width150">登入时刻</th>
                            <th class="width150">登出时刻</th>
                            <th class="width100">时长（秒）</th>
                            <th class="width150">费用</th>
                            <th>资费</th>
                        </tr>
                        <s:iterator value="serviceDetails" var="serviceDetail">
	                        <tr>
	                            <td>${serviceDetail.clientHost}</td>
	                            <td>${serviceDetail.loginTime }</td>
	                            <td>${serviceDetail.logoutTime }</td>
	                            <td>${serviceDetail.duration }</td>
	                            <td>${serviceDetail.cost }</td>
	                            <td>${service.cost.name}</td>
	                        </tr>
                        </s:iterator>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                <s:if test="totalPages>1">
        	        <s:if test="page>1">
						<a href="bill_service_detail?serviceId=${serviceId}&month=${month}&cost=${cost }&billId=${billId}&page=${page-1}" >上一页</a>
					</s:if>
					<s:else>
						<a>上一页</a>
					</s:else>
					<s:iterator value="new int[totalPages]" status="i">
						<s:if test="page == #i.count">
							<a href="bill_service_detail?serviceId=${serviceId}&month=${month}&cost=${cost }&billId=${billId}&page=${i.count}" class="current_page">${i.count}</a>
						</s:if>
						<s:else>
							<a href="bill_service_detail?serviceId=${serviceId}&month=${month}&cost=${cost }&billId=${billId}&page=${i.count}">${i.count}</a>
						</s:else>
					</s:iterator>
					<s:if test="page<totalPages">
						<a href="bill_service_detail?serviceId=${serviceId}&month=${month}&cost=${cost }&billId=${billId}&page=${page+1}">下一页</a>
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
