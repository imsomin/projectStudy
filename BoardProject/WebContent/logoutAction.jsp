<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="user.userDTO" %>  --%>   
<%@ page import="com.test.userDAO" %>
<%@ page import="java.io.PrintWriter" %>

<jsp:useBean id="user" class="com.test.userDTO" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="userID"></jsp:setProperty>
<jsp:setProperty name="user" property="userPassword"></jsp:setProperty>
<jsp:setProperty name="user" property="userName" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<title>로그아웃하기</title>
</head>
<body>
	 <%
		session.invalidate();	 	
	 %>
	 <script>
	 	location.href = 'main.jsp';
	 </script>
</body>
</html>