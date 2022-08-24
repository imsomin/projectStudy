<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="like.likeDTO" %>
<%@ page import="like.likeDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="like" class="like.likeDTO" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요 기능구현</title>
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
	 	} 
	 	else{
		 	int bbsID = 0; 
		 	if (request.getParameter("bbsID") != null){
		 		bbsID = Integer.parseInt(request.getParameter("bbsID"));
		 	}
		 	if (bbsID == 0){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('유효하지 않은 글입니다.')");
		 		script.println("location.href = 'login.jsp'");
		 		script.println("</script>");
		 	}
		 	int likeStar = 0;
			int badStar = 0;
			
			if (request.getParameter("likeStar") != null){
				likeStar = Integer.parseInt(request.getParameter("likeStar"));
			}
			if (request.getParameter("badStar") != null){
				badStar = Integer.parseInt(request.getParameter("badStar"));
			}
			
			likeDAO likeDAO = new likeDAO();
			ArrayList<likeDTO> list = likeDAO.whereList(bbsID, userID);
			int result = 0;
			if (list.isEmpty()){
				result = likeDAO.write(bbsID, userID, likeStar, badStar);
			}
			else if (likeStar == list.get(0).getLikeStar() && badStar == list.get(0).getBadStar()){
				result = likeDAO.delete(bbsID, userID);
			}
			else{
				result = likeDAO.update(bbsID, userID, likeStar, badStar);
			}
	 		if (result == -1){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('평가를 실패했습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	}
	 		
	 		else{
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("location.href=document.referrer;");
		 		script.println("</script>");
		 	}
	 	}
	 %>
</body>
</html>