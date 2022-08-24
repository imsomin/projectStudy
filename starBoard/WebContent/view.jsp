<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.bbsDTO" %>
<%@ page import="bbs.bbsDAO" %>
<%@ page import="like.likeDTO" %>
<%@ page import="like.likeDAO" %>
<%@ page import="comment.commentDTO" %>
<%@ page import="comment.commentDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<title>pick your star</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		int bbsID = 0;
		if (request.getParameter("bbsID") != null){
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0){
			PrintWriter script = response.getWriter();
	 		script.println("<script>");
	 		script.println("alert('유효하지 않은 글입니다.')");
	 		script.println("location href = 'bbs.jsp'");
	 		script.println("</script>");
		}
		bbsDTO bbs = new bbsDAO().getBbs(bbsID);
		//commentDTO comment = new commentDAO().getComment(bbsID);
	%>
	
	
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">
				<p style="font-weight: bold">pick your star</p>
			</a>
		</div>
		
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">별 게시판</a></li>
			</ul>
			<%
				if(userID == null){		//로그인이 되어있지 않은 경우
			%>
			<ul class="nav navbar-nav navbar-right">
         		<li class="dropdown">
           			<a href="#" class="dropdown-toggle" 
            		data-toggle="dropdown" role="button" aria-haspopup="true" 
            		aria-expanded="false">접속하기<span class="caret"></span></a>
        			<ul class="dropdown-menu">
        				<li><a href="login.jsp">로그인</a></li>
              			<li><a href="join.jsp">회원가입</a></li>
        			</ul>
         		</li>
       		</ul>
			<% 
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
         		<li class="dropdown">
           			<a href="#" class="dropdown-toggle" 
            		data-toggle="dropdown" role="button" aria-haspopup="true" 
            		aria-expanded="false">회원관리<span class="caret"></span></a>
        		<ul class="dropdown-menu">
              		<li><a href="logoutAction.jsp">로그아웃</a></li>
            	</ul>    
         		</li>
       		</ul>
			<%
				}
			%>
		</div>
	</nav>

		<!-- 게시판 화면 -->
		<div class="container">
			<div class="row">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thread>
					<tr>
						<th colspan="3" 
						style="background-color:#eeeeee; text-align:center;">
						게시판 글보기</th>
					</tr>
				</thread>
				
				<tbody>
					<tr>
						<td style="width:20%;">글 제목</td>
						<td colspan="2"><%=bbs.getBbsTitle()%></td>
					</tr>
					
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=bbs.getUserID() %></td>
					</tr>
					
					
					<tr>	
						<td>작성일자</td>		
						<td colspan="2"><%=bbs.getBbsDate().substring(0,11) + bbs.getBbsDate().substring(11, 13)
						+ "시" + bbs.getBbsDate().substring(14, 16) + "분" %></td>
					</tr>
					
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;">
							<%=bbs.getBbsContent().replaceAll("", "&nbps;").replaceAll("<","&lt;")
									.replaceAll(">","&gt;").replaceAll("\n","<b>;") %>
						</td>
					
					</tr>
			
					
					
				
				</tbody>
			
				</table>
				
				
				<a href="bbs.jsp" class="btn btn-primary">목록</a>
				<%
					if(userID != null && userID.equals(bbs.getUserID())) {
				%>
				<a href="update.jsp?bbsID=<%=bbsID%>" class="btn btn-primary">수정</a> 
				
				<a onclick="return comfirm('삭제하시겠습니까?')"
					href="deleteAction.jsp?bbsID=<%=bbsID %>" class="btn btn-primary">삭제</a>
				<%
					}
				%>
				
				</div>
				
				
				</div>
				

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script> 
</body>
</html>