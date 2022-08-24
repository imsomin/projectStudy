<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.bbsDAO"%>
<%@ page import="bbs.bbsDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<!-- 
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
 -->

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<title>별 게시판</title>
</head>
<body>

	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
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
		
			
			<%
				if(userID == null){		//로그인이 되어있지 않은 경우
			%>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">별 게시판</a></li>
				</ul>
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
       		</div>
       		
			<% 
				} else {
			%>
			<div class="collaps navbar-collapse" id="bs-example-naver-collapse-1">
				<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">별 게시판</a></li>
				</ul>
			<ul class="nav navbar-nav navbar-right">
			<!-- 로그인 정보 보여주기 -->
			<div id="idInfo" style="display: inclin-block; margin:5px; float: right;">
			<%=userID %>님 안녕하세요😍 </div> 
         		<li class="dropdown">
           			<a href="#" class="dropdown-toggle" 
            		data-toggle="dropdown" role="button" aria-haspopup="true" 
            		aria-expanded="false">회원관리<span class="caret"></span></a>
        		<ul class="dropdown-menu">
              		<li><a href="logoutAction.jsp">로그아웃</a></li>
            	</ul>    
         		</li>
       		</ul>
       		</div>
			<%
				}
			%>
	</nav>
	<div class="container">
			<div class="row">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: #eeeee; text-align: center;">번호</th>
							<th style="background-color: #eeeee; text-align: center;">제목</th>
							<th style="background-color: #eeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeee; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>안녕하세요</td>
							<td>별지기</td>
							<td>2022-07-15</td>
						</tr>
					</tbody>
				</table>
	
				<a href="write.jsp" class="btn-primary pull-right">글쓰기</a>
			</div>
		</div>

	</div>
	

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script> 



</body>
</html>