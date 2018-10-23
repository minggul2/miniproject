<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "../css/board.css">
</head>
<body>
<input type = "hidden" value = "${pg}" id = "pg" />
<input type = "hidden" value = "${boardDTO.seq}" id = "seq"/>
	<table border = "0" cellpadding = "5" cellspacing = "0" frame = "hsides" rules ="rows" color = "red" align  ="center" id = "view_table">
		<tr>
			<td width = "450" colspan = "3"><h3>${boardDTO.subject}</h3></td>
		</tr>
		
		<tr>
			<td width = "150" align = "center">글번호 : ${boardDTO.seq}</td>
			<td width = "150" align = "center">작성자 : ${boardDTO.id}${boardDTO.lev}</td>
			<td width = "150" align = "center">조회수 : ${boardDTO.hit}</td>
		</tr>
		
		<tr>
			
			<td height = "250" colspan = "3" valign = "top"><pre>${boardDTO.content}</pre></td>
		</tr>
	</table>
	<br>
	
	<div align = "center" id = "view_div">	
	<input type = "button" value = "목록" onclick = "location.href = 'boardList.do?pg=${pg}'">
	<input type = "button" value = "답글" onclick = "location.href = 'boardReplyForm.do?pseq=${boardDTO.seq}&pg=${pg}'">
	<c:if test="${memId == boardDTO.id}">
		<input type = "button" value = "글수정" id = "board_modify_button">
		<input type = "button" value = "글삭제" onclick = "location.href = 'boardDelete.do?pseq=${boardDTO.pseq}&seq=${boardDTO.seq}&pg=${pg}'">
	</c:if>
	</div>
</body>
</html>