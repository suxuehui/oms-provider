<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统错误</title>
  </head>
  
  <body align="center" valign="center">
  	<center>
  	<img style="margin-top: 10%" alt="加载中···" src="${pageContext.request.contextPath}/static/images/500.png"><br>
  	<a id="show" style="cursor: pointer;color: blue;">显示异常信息</a><br>
  	<div id="msg" style="display: none;"><br>
  	<table>
  	<tr>
  	<td align="right">错误码：</td> 
  	<td><%=request.getAttribute("javax.servlet.error.status_code")%> </td>
  	</tr>
  	<tr>
  	<td align="right">异常： </td>
  	<td><c:out value="${exception }"></c:out> </td>
  	</tr>
  	</table>
  	</div>
  	</center>
  </body>
  <script type="text/javascript">
  	$("#show").on("click",function(){
  		if($("#show").html() == '显示异常信息'){
  			$("#msg").show();
  			$("#show").html("隐藏异常信息");
  		}else{
  			$("#msg").hide();
  			$("#show").html("显示异常信息");
  		}
  	});
  </script>
</html>
