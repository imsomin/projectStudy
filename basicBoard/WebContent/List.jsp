<%@page import="java.net.URLDecoder"%>
<%@page import="com.test.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.util.MyUtil"%>
<%@page import="com.test.BoardDAO"%>
<%@page import="com.util.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!-- 로그인 안 되어있으면 게시판으로 못 들어감 -->
<%-- <%
	String userID = (String)session.getAttribute("userID");
	if (userID == null) {
		response.sendRedirect("./login.jsp");
	} 

%>
 --%>


<%
   
   // 이전 페이지로부터(List.jsp- 스스로 던지고 스스로 받기) 데이터 수신(pageNum, num, searchKey, searchValue...)
   
   // 이전 페이지로부터 넘어온 게시물 번호 수신
   String strNum = request.getParameter("num");
   int num = 0;
   if (strNum != null)
      num = Integer.parseInt(strNum);
   
   // 이전 페이지로부터 넘어온(요청) 페이지 번호 수신
   String pageNum = request.getParameter("pageNum");
   
   
   int currentPage = 1;
   if (pageNum != null)
      currentPage = Integer.parseInt(pageNum);
   
    // 검색 키와 검색 값 수신
    String searchKey = request.getParameter("searchKey");
    String searchValue = request.getParameter("searchValue");
   
    
    if (searchKey != null) //검색을 통해서 이 페이지가 요청되었을 경우
    {
    	// 넘어온 값이 get 방식이라면...
    	// -> get은 한글 문자열을 인코딩해서 보내기 때문에...
    	if(request.getMethod().equalsIgnoreCase("GET"))
    	{
    		//디코딩 처리
    		searchValue = URLDecoder.decode(searchValue, "UTF-8");
    	}
    	
    }
    else				 //-- 검색 기능이 아닌 기본적인 페이지 요청이 이루어졌을 경우
    {
    	searchKey = "subject";
    	searchValue = "";
    	
    }
   
   Connection conn = DBConn.getConnection();
   BoardDAO dao = new BoardDAO(conn);
   MyUtil myUtil = new MyUtil();
   
   
   // 위에 생성했으므로 삭제!!!
   // 현재 표시되어야 하는 페이지(기본) 
   //int currentPage = 1;
   
   
   // 전체 데이터 갯수 구하기
   int dataCount = dao.getDataCount(searchKey, searchValue);
   
   
   // 전체 페이지를 기준으로 총 페이지 수 계산
   int numPerPage = 10;         //-- 한 페이지에 표시할 데이터 갯수
   int totalPage = myUtil.getPageCount(numPerPage, dataCount);
   
   
   // 전체 페이지 수 보다 표시할 페이지가 큰 경우
   // 표시할 페이지를 전체 페이지로 처리
   // -> 한 마디로, 데이터를 삭제해서 페이지가 줄어들었을 경우...
   if (currentPage > totalPage)
      currentPage = totalPage;
   
   
   // 데이터베이스에서 가져올 시작과 끝 위치
   int start = (currentPage-1) * numPerPage + 1;
   int end = currentPage * numPerPage;
   
   
   // 실제 리스트 가져오기
   List<BoardDTO> lists = dao.getLists(start, end, searchKey, searchValue);
   
   
   
   // 페이징 처리
   String param = "";
   
   
   // 검색값이 존재한다면...
   if (!searchValue.equals(""))  //not check~!!!!
   {
	   param += "?searchKey=" + searchKey;   //따옴표 사이에 공백이 있어서는 안된다 check
	   param += "&searchValue=" + searchValue;
   }
   
 
   
   String listUrl = "List.jsp" + param;
   String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
   
   
   // 글 내용 보기 주소
   String articleUrl = cp + "/Article.jsp";
   
   
   //직접 본문 보게 됨
   //상품 보러 들어갔다가 다시 리스트로 돌아가기 버튼을 눌렀을 때
   //1 페이지 리스트를 보는 게 아니라
   //해당 상품을 눌렀을 때의 페이지 리스트를 볼 수 있게 해주어야 한다
   if (param.equals(""))
   {
      articleUrl = articleUrl + "?pageNum=" + currentPage;
   }
   else
   {
      articleUrl = articleUrl + param + "&pageNum=" + currentPage;
   }
   
   DBConn.close();
         
   

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/list.css">  

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

#bbsList_title 
{
	width:664px;   
	padding-left:20px;
	height:40px;
	border: 4px dotted #288cff;
	text-align:left;
	font-weight: bold;
	line-height:40px;
	font-size:10pt;
	margin-bottom:30px;
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






<script type="text/javascript">

	function sendIt()
	{
		var f = document.searchForm;
		
		//check
		f.action = "<%=cp%>/List.jsp";
		
		f.submit();
		
		
		
	}



</script>







</head>
<body>

<!-- 로그인 정보 보여주기 -->
<%-- <div id="idInfo" style="display: inclin-block; margin:5px; float: right;">
<%=userID %>님 안녕하세요</div> --%>

<br />
<br />


<div id="bbsList">

    <div id="bbsList_title">
        우리들의 힐링 반창고 🩹🧪🍳🍰
    </div>
   
   <div id="bbsList_header">
   
      <div id="leftHeader">
      
      	 <!-- 검색 폼 구성  -->
         <form action="" name="searchForm" method="post">
            <select name="searchKey" class="selectFiled">
            <!-- 	
            	<option value="subject">제목</option>
               <option value="name">작성자</option>
               <option value="content">내용</option>
                -->
            
            
            	<%
            	if(searchKey.equals("name"))		// 수신한 searchKey 가 name 이라면...
            	{
            	%>
            		<option value="subject">제목</option>
               		<option value="name" selected="selected">작성자</option>
               		<option value="content">내용</option>
            	<%
            	}
            	else if(searchKey.equals("content"))	// 수신한 searchKey 가 content 이라면...
            	{
            	%>
					<option value="subject">제목</option>
               		<option value="name">작성자</option>
               		<option value="content" selected="selected">내용</option>
				<%
            	}
            	else        //수신한 searchKey 가 subject 이거나... 없으면... //기본은 subject 이미 설정
            	{
				%>            		
               		<option value="subject">제목</option>
               		<option value="name">작성자</option>
               		<option value="content">내용</option>
               <%
            	}
               %>
               
            </select>
            <!-- 검색한 값이 남아있을 수 있도록  value=추가 -->
            <input type="text" name="searchValue" class="textFiled" value="<%=searchValue%>">
            <input type="button" value="검색" class="btn2" onclick="sendIt()">
         </form>
      </div><!-- #leftHeader -->
      
      <div id="rightHeader">
         <input type="button" value="글올리기" class="btn2"
         onclick="javascript:location.href='<%=cp%>/Created.jsp'">
      </div><!-- #rightHeader -->
      
   </div><!-- #bbsList_header  -->
   
   
   <div id="bbsList_list">
      
      <div id="title">
         <dl>
            <dt class="num">번호</dt>
            <dt class="subject">제목</dt>
            <dt class="name">작성자</dt>
            <dt class="created">작성일</dt>
            <dt class="hitCount">조회수</dt>
         </dl>
      </div><!--#title  -->
      
      <div id="lists">
          
         <%
         for (BoardDTO dto : lists)
         {
         %> 
          
         <dl>
            <dd class="num"><%=dto.getNum() %></dd>
            <dd class="subject">
               <a href="<%=articleUrl %>&num=<%=dto.getNum()%>"><%=dto.getSubject() %></a>
            </dd>
            <dd class="name"><%=dto.getName() %></dd>
            <dd class="created"><%=dto.getCreated() %></dd>
            <dd class="hitCount"><%=dto.getHitCount() %></dd>
         </dl>
          
         <% 
         }
         %>
          
      </div><!-- #lists -->
      
      <div id="footer">
         <!-- <p>1 Prev 21 22 23 24 25 26 27 28 29 30 Next 42</p> -->
         <!-- <p>등록된 게시물이 존재하지 않습니다.</p> -->
         
         <p>
  
         <%
         if (dataCount != 0)
         {   
         %>
            <%=pageIndexList %>
         <%
         }
         else
         {
         %>
            등록된 게시물이 존재하지 않습니다.
         <%
         }
         %>
   
         </p>
         
         
      </div><!-- #footer -->
     <!-- <a href="main.jsp">메인으로</a> -->
      
   </div><!-- #bbsList_list  -->
   
   
   
</div><!-- #bbsList -->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script> 


</body>
</html>