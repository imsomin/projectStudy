<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/main.css">

<style type="text/css">
	* {line-height: 150%;}
	#customers td{text-align: center;}
	#submitBtn
	{
		height: 150%;
		width: 250px;
		font-size: 18px;
		font-weight: bold;
		font-family: 맑은 고딕;
		color: #343;
	}
	#err
	{
		color: red;
		font-size: small;
		display: none;
	}

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function()
	{
		$("#submitBtn").click(function()
		{
			//테스트 완료
			//alert("hello");
			
			//두 항목 모두 필수 입력 항목이기 때문에... 생략 가능
			//$("#err").css("display", "none");
			
			
			if ($("#name").val()=="" || $("#telephone").val()=="")
			{
				$("#err").css("display", "inline");
				return
				//return;
			}
			
			$("#memberForm").submit();
			
			
		});
		
	});


</script>


</head>
<body>


<div>
	<h1>회원 관리</h1>
	<hr />
</div>



<div>
	<form action="memberinsert.do" method="post" id="memberForm">
	이름 <input type="text"  name="name" id="name" class="control" required="required"/>
	<br />
	전화 <input type="text"  name="telephone" id="telephone" class="control" required="required"/>
	<br />
	<button type="button" id="submitBtn">회원 추가</button>
	<span id="err">모든 항목 입력 필수</span>
	</form>


<br />

<p>전체 인원 수 : ${count }</p>

<table id="customers" style="width: 500px;">
	<tr>
		<th>번호</th><th>이름</th><th>전화번호</th>
	</tr>

	<c:forEach var="member" items="${memberList }">
	<tr>
	
		<td>${member.mid }</td>
		<td>${member.name }</td>
		<td>${member.telephone }</td>
	</tr>
	</c:forEach>



</table>
</div>

${lists }

</body>
</html>