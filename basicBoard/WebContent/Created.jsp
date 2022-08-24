<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Created.jsp</title>
<link rel="stylesheet"  type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet"  type="text/css" href="<%=cp %>/css/created.css">



<style type="text/css">

input[type=submit], input[type=button],input[type=reset] {
	padding: 2px 4px;
	margin: 2px 4px;
	background: #6495ed; 
	color: white;
	border: none;
	cursor: pointer;
	width:20%;
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



<script type="text/javascript" src="<%=cp%>/js/util.js"></script>
<script type="text/javascript">
/* 크롬 4 부터 trim 함수 지원해준다  */

	function sendIt()
	{
		f = document.myForm;
		
		//제목 입력 확인----------------------------------
		//필수 입력 항목 기재 여부 확인 및 공백 처리
		str = f.subject.value;
		str = str.trim(); 
		//※ javascript 에는 trim() 이 존재하지 않음.
		//   외부에서 참조한 util.js 에 만들어두었음.
		
		//테스트
		//alert("|" + str + "|");
		
		if (!str)
		{
			alert("\n제목 입력 부탁");
			f.subject.focus();
			return;
		}
		
		//----------------------------------제목 입력 확인
	
		
		
		//이름 입력 확인----------------------------------
		//필수 입력 항목 기재 여부 확인 및 공백 처리
		str = f.name.value;
		str = str.trim()
		
		if(!str)   //또 str 변수 써도 무방하다~~!!!
		{
			alert("\n이름 입력 부탁");
			f.name.focus();
			return;
			
		}
		
		//------------------------------------이름 입력 확인
		
		
		
		//이메일 검사---------------------------------------
		//필수 입력 항목이 아니므로 선택적인 입력이 가능하지만
		//입력을 한 상황이면, 이메일 형식에 맞게 입력했는지 확인
		//확인하는 처리
		
		if(f.email.value) //이메일을 입력한 상황이라면...
		{
			
			if (!isValidEmail(f.email.value))
			{
				alert("\n정상적인 이메일 형식을 입력 부탁");
				f.email.focus();
				return;
			}
			
			
		}
	
		
		//---------------------------------------이메일 검사
		
		
		//내용 입력 확인-----------------------------------
		
		str = f.content.value;
		str = str.trim();
		
		
		if (!str)
		{
			alert("\n내용 확인 부탁");
			f.content.focus();
			return;
		}
		
		
		//-------------------------------------내용 입력 확인
		

		
		//패스워드 입력 확인----------------------------------
		//필수 입력 항목 기재 여부 확인 및 공백 처리
		
		str = f.pwd.value;
		
		str = str.trim();
		
		if (!str)
		{
			alert("\n패스워드 입력 부탁");
			f.pwd.focus();
			return;
		}
		

		//-----------------------------------패스워드 입력 확인
		
		
		f.action = "<%=cp%>/Created_ok.jsp";
		
		f.submit();
		
		
	}


</script>

</head>
<body>
		

<div id="bbs">

    <div id="bbs_title">
        우리들의 힐링 반창고 🩹🧪🍳🍰
    </div>
 
 <form action="" method="post" name="myForm">
 	
 	<div id="bbsCreated">
 		
 		<div class="bbsCreated_bottomLine">
 			<dl>
 				<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt>
 				<dd>
 					<input type="text" name="subject" size="64" maxlength="100" class="boxTF">
 				</dd>
 			</dl>
 		
 		</div><!-- close bbsCreated_bottonLine -->
 		
 		
 		<div class="bbsCreated_bottomLine">
 			<dl>
 				<dt>작 성 자</dt>
 					<dd>
 						<input type="text" name="name" size="35" maxlength="20" class="boxTF">
 					</dd>
 			
 			</dl>
 	
 		</div><!-- close bbsCreated_bottonLine -->
 		
 		
 		<div class="bbsCreated_bottomLine">
 			<dl>
 				<dt>이 메 일</dt>
 					<dd>
 						<input type="email" name="email" size="35" maxlength="50" class="boxTF">
 					</dd>
 			</dl>
 		</div><!-- close bbsCreated_bottonLine -->
 		
 		
 		
 		<div id="bbsCreated_content">
 			<dl>
 				<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
 				<dd>
 					<textarea name="content" class="boxTA" cols="63" rows="12"></textarea>
 				</dd>
 			
 			</dl>
 		</div><!-- close bbsCreated_content  -->
 		
 		
 		
 		<div class="bbsCreated_noLine">
 			<dl>
 				<dt>패스워드</dt>
 				<dd>
 					<input type="password" name="pwd" size="35" maxlength="10" class="boxTF"/>
 					&nbsp;<span style="font-size: 6pt;">(게시물 수정 및 삭제 시 필요)</span>
 				</dd>
 			</dl>
 		</div><!-- close bbsCreated_noLine -->
 		
 		
 		<div id="bbsCreated_footer">
 			<input type="button" value="등록하기" class="btn2" onclick="sendIt()">
 			<input type="reset" value="다시입력" class="btn2"
 			onclick = "document.myForm.subject.focus();">
 			<input type="button" value="작성취소" class="btn2"
 			onclick="javascript:location.href='<%=cp%>/List.jsp'">	
 		</div><!-- close bbsCreated_footer -->
 		
 
 	
 	</div><!-- close bbsCreated -->
 
 </form>


</div><!-- close bbs -->



</body>
</html>