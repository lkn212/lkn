<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/comm/head.jsp"%>
 <h1>标准信息表</h1>
    <div>
    <form method="post" action="${pageContext.request.contextPath }/message/showmessage.html">
     	<input type="text" name="keys" class="mohu" value="${keys}">
    	<input type="submit" value="提交检索" class="firstsubmit">
    	<input type="button" value="新增标准" class="addBtn">
    	<input type="button" value="删除标准" class="delBtn">
    	<input type="hidden" name="pageIndex" id="pageIndex" value=""/>
    </form>
    	<table border="1" >
    		<tr>
    			<th><input type="checkbox" name="checkedmessageth" ></th>
    			<th>标准号</th>
    			<th>中文名称</th>
    			<th>版本</th>
    			<th>发布日期</th>
    			<th>实施日期</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="message" items="${messagelist}" varStatus="status">
    			<tr>
    				<td><input type="checkbox" name="checkedmessagetd" value="${message.id}"></td>
    				<td>${message.stdNum }</td>
    				<td>${message.zhname }</td>
    				<td>${message.version }</td>
    				<td>${message.releaseDate }</td>
    				<td>${message.implDate }</td>
    				<td>
    				<a href="#">下载</a>
    				<a class="modifyMessage" href="javascript:;"id=${message.id }>修改</a>
    				</td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>
    
    <!-- 分页控制 -->
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
    	<c:param name="totalCount" value="${totalCount}"/>
    	<c:param name="currentPageNo" value="${currentPageNo}"/>
	    <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
    
<%@include file="/WEB-INF/jsp/comm/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/showmessage.js"></script>