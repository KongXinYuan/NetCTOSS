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
            	$('.report_off').removeClass().addClass("report_on");
            	var tag = '#${tag}';
            	$(tag).removeClass().addClass("tab_on");
            });
            
            function changePage(page){
            	var tag = '${tag}';
            	location.href = "report_list?tag=" + tag + "&page=" + page;
            }
        </script>
    </head>
    <body>
    	<!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->        
        <!--主要区域开始-->        
        <div id="report_main">
        	<div class="tabs">
    	        <ul>
        	        <li><a href="report_list?tag=tag1" id="tag1" class="tab_out" title="每位客户每月的累计时长">客户使用时长</a></li>
                    <li><a href="report_list?tag=tag2" id="tag2" class="tab_out" title="每台服务器上累计时长最高的前三名客户">时长排行榜</a></li>
                    <li><a href="report_list?tag=tag3" id="tag3" class="tab_out" title="每台服务器每种资费标准的使用次数">资费使用率</a></li>
                </ul>
            </div>            
            <div class="report_box">
                <!--数据区域：用表格展示数据-->
                <div id="report_data">
                    <table id="datalist">
                    <s:if test="tag == 'tag1'">
                     	<tr>                            
                            <th>账号 ID</th>
                            <th>账务帐号</th>
                            <th>客户名称</th>
                            <th class="width200">身份证号码</th>
                            <th>电话</th>
                            <th class="width150">月份</th>
                            <th class="width150">累积时长</th>
                        </tr>
                        <s:iterator value="reportSumDurations" var="sd" status="i">                   
                        <tr>
                            <td>${sd.id }</td>
                            <td>${sd.loginName}</td>
                            <td>${sd.realName }</td>
                            <td>${sd.idcardNo }</td>
                            <td>${sd.telephone}</td>
                            <td>${months[i.index]}</td>
                            <td>${durations[i.index]}</td>
                        </tr>
                        </s:iterator>
                    </s:if>
                    <s:elseif test="tag == 'tag2'">
                        <tr>
                            <th>Unix 服务器IP</th>
                            <th>账务账号</th>
                            <th>客户名称</th>
                            <th>身份证号码</th>
                            <th>累计时长</th>
                        </tr>
                        <s:iterator value="reportSumDurations" var="sd" status="i">
                        <tr>
                        	<td>${sd.unixHost}</td>
                        	<td>${sd.loginName}</td>
                        	<td>${sd.realName}</td>
                        	<td>${sd.idcardNo}</td>
                        	<td>${durations[i.index]}</td>
                        </tr>
                        </s:iterator>
                        </s:elseif> 
                    	<s:elseif test="tag == 'tag3'">
                        <tr>
                            <th>Unix 服务器IP</th>
                            <th>包月</th>
                            <th>套餐</th>
                            <th>计时</th>
                        </tr>
                        <s:iterator value="reportCostUseds" var="reportCostUsed">
                        <tr>
                        	<td>${reportCostUsed.host}</td>
                        	<td>${reportCostUsed.monthlyCount}</td>
                        	<td>${reportCostUsed.packageCount}</td>
                        	<td>${reportCostUsed.timeBasedCount}</td>
                        </tr>
                        </s:iterator>
                        </s:elseif>                        
                    </table>
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
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
