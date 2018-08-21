<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'messageadd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/add.js"></script>

  </head>
  
  <body>
  <form id="messageForm" name="messageForm" method="post" 
           	 	action="${pageContext.request.contextPath }/message/addSave.html"
            	enctype="multipart/form-data">
  	<h2>增加标准信息</h2>
	<div>
		<table border="1">
			<tr>
				<td>
				<font color="red">*</font>
				<label for="stdNum">标准号:</label>
				</td>
				<td><input type="text" name="stdNum" id="stdNum" value=""></td>
			</tr>
			<tr>
				<td>
				<font color="red">*</font>
				<label for="zhname">中文名称:</label>
				</td>
				<td><input type="text" name="zhname" id="zhname" value=""></td>
			</tr>
			<tr>
				<td><font color="red">*</font>
				<label for="version">版本:</label>
				</td>
				<td><input type="text" name="version" id="version" value=""></td>
			</tr>
			<tr>
				<td>
				<font color="red" >*</font>
				<label for="keys">关键字/词:</label>
				</td>
				<td><input type="text" name="keys" id="keys" value=""></td>
			</tr>
			<tr>
				<td>
				<label for="releaseDate">发布日期（yyyy-MM-dd）:</label>
				</td>
				<td><input type="text" name="releaseDate" id="releaseDate" ></td>
			</tr>
			<tr>
				<td>
				<label for="implDate">实施日期（yyyy-MM-dd）:</label>
				</td>
				<td><input type="text" name="implDate" id="implDate"></td>
			</tr>
			<tr>
				<td><label for="a_packagePath">附件:</label></td>
				<td><input type="file" name="a_packagePath" id="a_packagePath"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="取消" >
				</td>
			</tr>
		</table>
		<input type="hidden" id="errorinfo" value="${uploadFileError}">
	</div>
</form>
  </body>
</html>
