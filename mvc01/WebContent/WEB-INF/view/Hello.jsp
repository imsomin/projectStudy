<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello.jsp</title>
<link rel="stylesheet"  type="text/css" href="css/main.css">
</head>
<body>

<!-- 표현언어 EL 활용 -->
<div>
	<h1>${message }</h1>
</div>
<!-- Hello, SpringMVC World~  -->
<!-- HelloController.java에서 값 받아와 결과 출력   -->


</body>
</html>