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
<%-- <script>
<% if(!boardDTO.getId().equals(id)){%>
		alert("작성자가아닙니다");
		
		<%response.sendRedirect("boardList.jsp?pg=1");
	}%>
</script> --%>
	<form name = "board_write_form" method = "post" action = "boardModify.do" id = "board_write_form">
	<table border = "1">
	<caption>게시판 수정</caption>
		<tr>
			<td class = "shows">제목</td>
			<td>
				<input type = "text" name = "subject" size = "30" value = "${subject}">
				<input type = "hidden" name = "seq" value = "${seq}">
				<input type = "hidden" name = "pg" value = "${pg}">
				<div id = "subject_div"></div>
			</td>
		</tr>
		<tr>
			<td class = "shows">내용</td>
			<td><textarea rows="10" cols="30" name = "content" id = "content" >${content}</textarea>
			<div id = "content_div"></div>
		</tr>
		<tr>
			<td colspan = "2" align = "center" >
				<input type = "button" value = "글수정" id = "board_write_button">
				<input type = "reset" value = "취소">				
			</td>
		</tr>
	</table>
	</form>
</body>
</html>