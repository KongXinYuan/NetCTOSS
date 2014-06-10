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
            //写入下拉框中的年份和月份
            function initialYearAndMonth() {
                //写入最近3年
                var yearObj = document.getElementById("selYears");
                var year = (new Date()).getFullYear();
                for (var i = 0; i <= 2; i++) {
                    var opObj = new Option(year - i, year - i);
                    yearObj.options[i] = opObj;
                    var year1 = '${year}';
                    if (year - i == year1) {
                    	opObj.selected = true;
                    }
                }
                //写入 12 月
                var monthObj = document.getElementById("selMonths");
                var opObj = new Option("全部", "0");
                monthObj.options[0] = opObj;
                for (var i = 1; i <= 12; i++) {
                    var opObj = new Option(i, i);
                    monthObj.options[i] = opObj;
                    var month1 = '${month}';
                    if (month1 == i) {
                    	opObj.selected = true;
                    }
                }
            }
            function changePage(page){
            	var form = document.getElementById(
        				"searchForm");
        		form.action = "bill_list?page="+page;
        		form.submit();//提交搜索表单
        		return false;//阻止href动作
            }
            $(function(){
            	$('.bill_off').removeClass().addClass("bill_on");
            });
        </script>
    </head>
    <body onload="initialYearAndMonth();">
        <!--Logo、导航区域开始-->
    	<%@include file="/WEB-INF/jsp/header.jsp" %>
   		<!--Logo、导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <s:form action="bill_list" method="post" id="searchForm" theme="simple">
                <!--查询-->
                <div class="search_add">                        
                    <div>身份证：<s:textfield cssClass="text_search" name="idcardNo"></s:textfield></div>
                    <div>账务账号：<s:textfield cssClass="width100 text_search" name="loginName"></s:textfield></div>                            
                    <div>姓名：<s:textfield cssClass="width70 text_search" name="realName"></s:textfield></div>
                    <div>
                        <select class="select_search" id="selYears" name="year" >
                        </select>
                        年
                        <select class="select_search" id="selMonths" name="month">
                        </select>
                        月
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                </div>
                </s:form>  
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">账单ID</th>
                        <th class="width70">姓名</th>
                        <th class="width150">身份证</th>
                        <th class="width150">账务账号</th>
                        <th>费用</th>
                        <th class="width100">月份</th>
                        <th class="width100">支付方式</th>
                        <th class="width100">支付状态</th>                                                        
                        <th class="width50"></th>
                    </tr>
                    <s:iterator value="bills" var="bill" >
                    <tr>
                        <td>${bill.id}</td>
                        <td>${bill.account.realName}</td>
                        <td>${bill.account.idcardNo}</td>
                        <td>${bill.account.loginName}</td>
                        <td>${bill.cost}</td>
                        <td>${bill.billMonth}</td>
                        <td>
                        	<s:if test="#bill.paymentMode == '0'">现金</s:if>
                        	<s:elseif test="#bill.paymentMode == '1'">银行转账</s:elseif>
                        	<s:elseif test="#bill.paymentMode == '2'">邮局汇款</s:elseif>
                        	<s:elseif test="#bill.paymentMode == '3'">其他</s:elseif>
                        </td>
                        <td>
                        	<s:if test="#bill.payState == '0'">未支付</s:if>
                        	<s:elseif test="#bill.payState == '1'">已支付</s:elseif>
                        </td>                            
                        <td><a href="bill_item?billId=${bill.id}" title="账单明细">明细</a></td>
                    </tr>
                    </s:iterator>                    
                </table>
                
                <p>业务说明：<br />
                1、设计支付方式和支付状态，为用户自服务中的支付功能预留；<br />
                2、只查询近 3 年的账单，即当前年和前两年，如2013/2012/2011；<br />
                3、年和月的数据由 js 代码自动生成；<br />
                4、由数据库中的ｊｏｂ每月的月底定时计算账单数据。</p>
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
