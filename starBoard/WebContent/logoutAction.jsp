<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="user.userDTO" %>  --%>   
<%@ page import="user.userDAO" %>
<%@ page import="java.io.PrintWriter" %>

<jsp:useBean id="user" class="user.userDTO" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="userID"></jsp:setProperty>
<jsp:setProperty name="user" property="userPassword"></jsp:setProperty>
<jsp:setProperty name="user" property="userName" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<title>pick your star</title>
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