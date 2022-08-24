<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.bbsDAO"%>
<%@ page import="bbs.bbsDTO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.File" %>
<%	request.setCharacterEncoding("UTF-8");%>
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
		//로그인 완료된 회원은 로그인 상태 유지 & 회원가입 페이지 못 들어감
		if (session.getAttribute("userID") != null) {//유저아이디이름으로 세션이 존재하는 회원들은 
			userID = (String) session.getAttribute("userID");//유저아이디에 해당 세션값을 넣어준다.
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'"); //이미 로그인 되면 또 로그인 안 됨
			script.println("</script>");
		} 
		
		//글이 유효한지 판별
		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글 입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		bbsDTO bbs = new bbsDAO().getBbs(bbsID);
		if (!userID.equals(bbs.getUserID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");				
		} 
		//권한이 있으면 성공적으로 넘어가고 bbsID를 매개변수로 받아서 삭제
		else {
			bbsDAO BbsDAO = new bbsDAO();
			int result = BbsDAO.delete(bbsID);
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제에 실패했습니다')");
				script.println("history.back()"); //이전 페이지(로그인 페이지)로 돌아가게 만듦
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href='bbs.jsp'");
				script.println("</script>");

			}
		}
		%>
</body>
</html>