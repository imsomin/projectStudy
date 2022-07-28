<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="user.userDTO" %> --%>    
<%@ page import="com.test.userDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% 
	request.setCharacterEncoding("UTF-8"); 
%>
<jsp:useBean id="user" class="com.test.userDTO" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<jsp:setProperty name="user" property="userName" />
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<title>회원가입하기</title>
</head>
<body>
	 <%
	 	String userID = null;
	 	if(session.getAttribute("userID") != null){
	 		userID = (String) session.getAttribute("userID");
	 	}
	 	if(userID != null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
	 		script.println("이미 로그인이 되어 있습니다')");
	 		script.println("location.href = 'main.jsp'");
	 		script.println("</script>");
	 	}
	 	if (user.getUserName() == null || user.getUserPassword() == null || user.getUserName() == null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
	 		script.println("alert('입력이 안된 사항이 있습니다.')");
	 		script.println("history.back()");
	 		script.println("</script>");
	 	} else {
	 		userDAO UserDAO = new userDAO();
	 		int result = UserDAO.join(user);
	 		if (result == -1){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('이미 존재하는 아이디 입니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	}
		 	else{
		 		session.setAttribute("userID",user.getUserID());
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("location.href = 'main.jsp'");
		 		script.println("</script>");
		 	}
	 	}
		 
	 	
	 	
	 %>
</body>
</html>