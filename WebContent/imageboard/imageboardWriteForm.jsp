<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {
		border:1px solid black;
		border-collapse:collapse;
	}

	td, tr, th{
		border:1px solid black;
	}
</style>
</head>
<body>
<form id = "img_board_form"  method = "post" enctype="multipart/form-data" action = "imageboardWrite.do" >
	<table>
		<tr>
			<th>상품코드</th>
			<td>
				<input type = "text" value = "img_" name = "imageId" id = "imageId"/>
				<div id = "imageId_div"></div>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>
				<input type = "text" placeholder = "상풍명 입력" name = "imageName" id = "imageName"/>
				<div id = "imageName_div"></div>
			</td>
		</tr>
		<tr>
			<th>단가</th>
			<td>
				<input type = "text" placeholder = "단가 입력" name = "imagePrice" id = "imagePrice"/>
				<div id = "imagePrice_div"></div>
			</td>
			
		</tr>
		
		<tr>
			<th>갯수</th>
			
			<td>
				<input type = "text" placeholder = "갯수 입력" name = "imageQty" id = "imageQty"/>
				<div id = "imageQty_div"></div>
			</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>
				<textarea cols = "20" rows = "10" name = "imageContent"  placeholder = "내용입력" id = "imageContent"></textarea>
				<div id = "imageContent_div"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan = "2">
				<input type = "file" size = "50" name = "image1" id = "image1">
				<div id = "image1_div"></div> 
			</td>
		</tr>
		
		<tr>
			<td colspan = "2">
				<input type = "button" value = "이미지등록" id = "img_write_btn" />
				<!-- <input type = "submit" value = "이미지등록" /> -->	
				<input type = "reset" value = "다시작성" /> 
			</td>
		</tr>
	</table>
</form>

<!-- <script src = "http://code.jquery.com/jquery-3.3.1.min.js" >
</script>

<script src = "../js/imageboard.js"></script> -->
</body>
</html>
<!-- 
seq NUMBER PRIMARY KEY,               
     imageId VARCHAR2(20) NOT NULL,     -- 상품코드  
     imageName VARCHAR2(40) NOT NULL, -- 상품명
     imagePrice  NUMBER NOT NULL,      -- 단가
     imageQty    NUMBER NOT NULL,      -- 개수
     imageContent VARCHAR2(4000) NOT NULL,      
     image1 varchar2(200),   
     logtime DATE DEFAULT SYSDATE -->