<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.bbsDAO" %>
<%-- <%@ page import="bbs.bbsDTO"%> --%>
<%@ page import="java.io.PrintWriter" %>
<% 
	request.setCharacterEncoding("UTF-8"); 
%>
<jsp:useBean id="bbs" class="bbs.bbsDTO" scope="page" />
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsContent" />
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<title>pick your star</title>
</head>
<body>
	 <%
	 	String userID = null;
	 	if(session.getAttribute("userID") != null){
	 		userID = (String) session.getAttribute("userID");
	 	}
	 	if(userID == null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
	 		script.println("location.href = 'login.jsp'");
	 		script.println("</script>");
	 	} else {
	 		//if (bbs.getBbsTitle() == null || bbs.getBbsContent() == null){
	 		if (bbs.getBbsContent() == null){
	 			PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('입력이 안된 사항이 있습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	} else {  
		 		bbsDAO BbsDAO = new bbsDAO();
		 		int result = BbsDAO.write(bbs);
		 		if (result == -1){
			 		PrintWriter script = response.getWriter();
			 		script.println("<script>");
			 		script.println("alert('글쓰기에 실패했습니다.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	}
			 	else{
			 		PrintWriter script = response.getWriter();
			 		script.println("<script>");
					script.println("location.href='bbs.jsp'");
			 		script.println("</script>");
			 	}
		 	}
	 	}
	 %>
</body>
</html>