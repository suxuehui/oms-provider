<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
  </head>
  <body>
     <form action="http://localhost:8080/oms-import/oms/addOrderList.do" method="post">
     	<table>
     		<tr>
     			<td>用户名654123</td>
     			<td><input type="text" value="" name="orderNo"/></td>
     		</tr>
     		<tr>
     			<td>密码</td>
     			<td><input type="password" value="" name="empPass"/></td>
     		</tr>
     	</table>
     	<input type="button" value="登陆" onclick="sub()">
     </form>
  </body>
</html>
<script type="text/javascript">

function sub(){

 		var param = {};		
		 param['memberId'] = 5;
		 param['productId'] = 1;
		  param['count'] =2;
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/provider/shopping/addCart.html",
			data : param,
			dataType : "json",
			async : false,
			success : function(data) {
				if (!data) {return;}
				if (data.msg == '200') {

				}
			},
			error : function() {}
		});
	}
</script>