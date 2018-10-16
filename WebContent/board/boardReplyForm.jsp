<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<style>
	.shows{
		width : 50px;
    	text-align: center;
    	color: "red";
    	outline-color: "red";
    	text-shadow: aqua;
    	text-transform: capitalize;
    	
	}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name = "boardWriteForm" method = "post" action = "boardReply.do" id = "board_write_form">
	<input type = "hidden" value = "${pg}"  name = "pg"/>
	<input type = "hidden" value = "${pseq}" name = "seq"/>
	<table border = "1">
		<tr>
			<td class = "shows">제목</td>
			<td><input type = "text" name = "subject" size = "30" id = "subject">
				<div id = "subject_div"></div>
			</td>
			
		</tr>
		<tr>
			<td class = "shows">내용</td>
			<td><textarea rows="10" cols="30" name = "content" id = "content"></textarea>
			<div id = "content_div"></div>
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "center" >
				<input type = "button" value = "답글작성" id = "board_write_button">
				<input type = "reset" value = "취소">				
			</td>
		</tr>
		
	</table>
	</form>
	<script src = "http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src = "../js/board.js">
	</script>
</body>
</html>