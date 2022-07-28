<%@page import="com.test.BoardDTO"%>
<%@page import="com.util.DBConn"%>
<%@page import="com.test.BoardDAO"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	Connection conn = DBConn.getConnection();
	BoardDAO dao = new BoardDAO(conn);
	
	//이전 페이지로(List.jsp -> 목록페이지)부터 데이터(num, pageNum) 수신
	String pageNum = request.getParameter("pageNum");   //페이지 번호
	String strNum = request.getParameter("num");		//게시물 번호
	int num = Integer.parseInt(strNum);
	
	//해당 게시물의 조회수 증가
	dao.updateHitCount(num);
	
	// 이전, 다음 게시물 번호 확인
	int beforeNum = dao.getBeforeNum(num);   //	?? 22
	int nextNum= dao.getNextNum(num);		 //    22 ??
			
	BoardDTO dtoBefore = null;
	BoardDTO dtoNext = null;
	
	if(beforeNum != -1)
		dtoBefore = dao.getReadData(beforeNum);

	if(nextNum != -1)
		dtoNext = dao.getReadData(nextNum);
	
	//해당 게시물의 상세 내용 가져오기
	BoardDTO dto = dao.getReadData(num);
	
	//게시물 본문 라인 수 확인
	int lineSu = dto.getContent().split("\n").length;
	
	//게시물 내용
	dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
	//-- 안녕하세요\n반갑습니다.\n즐거운오후입니다.\n안녕히가세요.
	//-> 안녕하세요<br>반갑습니다.<br>즐거운오후입니다.<br>안녕히가세요.


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article.jsp</title>
<link rel="stylesheet"  type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet"  type="text/css" href="<%=cp %>/css/article.css">


<style type="text/css">

input[type=submit], input[type=button],input[type=reset] {
	padding: 4px 4px;
	margin: 4px 4px;
	background: #6495ed; 
	color: white;
	border: none;
	cursor: pointer;
	width:35%;
}
input[type=submit]:hover{
	background:#ff7f50;
}
input[type=button]:hover{
	background:#8b008b;
}
input[type=reset]:hover{
	background:#dc143c;
}

ul{
    list-style-type: none;
}
h3{
    text-align: center;
    color:#F59B7A;
    font-size: 29PX;
}

#bbs_title 
{
	width:600px;   
	padding-left:20px;
	height:40px;
	border: 4px dotted #288cff;
	text-align:left;
	font-weight: bold;
	line-height:40px;
	font-size:10pt;
	margin-bottom:20px;
}

</style>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>


<div id="bbs">

 <div id="bbs_title">
 	    우리들의 힐링 반창고 🩹🧪🍳🍰
  </div><!-- close title -->
	
	<div id="bbsArticle">
		
		<div id="bbsArticle_header">
			<!-- 게시물의 제목입니다. -->
			<%=dto.getSubject() %>
		</div><!-- close header -->
		
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>작성자</dt>
				<dd><%=dto.getName() %></dd>
				
				<dt>라인수</dt>
				<dd><%=lineSu %></dd>
			</dl>
		</div><!-- close bottomLine -->
		
		
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>등록일</dt>
				<!-- <dd>2022-04-27</dd> -->
				<dd><%=dto.getCreated() %></dd>
				
				<dt>조회수</dt>
				<!-- <dd>13</dd> -->
				<dd><%=dto.getHitCount() %></dd>
			</dl>
		
		</div><!--  -->
		
		
		<div id="bbsArticle_content">
			<table style="width:600;">
				<tr>
					<td style="padding: 10px 40px 10px 10px; vertical-align: top; height: 150;">
						<!-- 내용입니다. -->
						<%=dto.getContent() %>
					</td>
				</tr>
			</table>
		
		</div><!--  -->
		
		
		<div class="bbsArticle_bottomLine">
			<!-- 이전글 : (104) 취미 관련 게시물 -->
			
			
			<%
			if (beforeNum != -1)
			{
			%>
				<a href="<%=cp %>/Article.jsp?pageNum=<%=pageNum %>&num=<%=beforeNum %>"  >
				이전글 : (<%=beforeNum %>) <%=dtoBefore.getSubject() %>
				</a>
			<%
			}
			else
			{
			%>
				이전글 : 없음
			<%
			}
			%>
			
		</div><!--  -->
		
		<div class="bbsArticle_noLine">
			<!-- 다음글 : (102) 날씨 관련 게시물 -->
			
			<%
			if (nextNum != -1)
			{
			%>
				<a href="<%=cp %>/Article.jsp?pageNum=<%=pageNum %>&num=<%=nextNum %>"  >
				다음글 : (<%=nextNum %>) <%=dtoNext.getSubject() %>   <!-- DAO check~~★★★ -->
				</a>
			<%
			}
			else
			{
			%>
				다음글 : 없음
			<%
			}
			%>
			
		</div><!--  -->

		
		
	</div><!-- close #bbsArticle -->         <!-- check~!!! -->


	<div class="bbsArticle_noLine" style="text-align: right;">
		<!-- From : 211.238.142.151 -->
		From : <%=dto.getIpAddr() %>
	</div><!--  -->

	<div id="bbsArticle_footer">
		<div id="leftFooter">
			<input type="button" value="수정" class="btn2"
			onclick="javascript:location.href='<%=cp%>/Updated.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>&status=1'">
			<input type="button" value="삭제" class="btn2"
			onclick="javascript:location.href='<%=cp%>/Updated.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>&status=2'">
		</div>
		
		<div id="rightFooter">
			<input type="button" value="리스트" class="btn2"
			onclick="javascript:location.href='<%=cp%>/List.jsp?pageNum=<%=pageNum%>'">
		</div>
		
		
	</div><!-- close #bbsArticle_footer -->
	

	
	


</div><!-- close #bbs -->



</body>
</html>