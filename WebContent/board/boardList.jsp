<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<link rel = "stylesheet" href = "../css/board.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center" >
	<input type = "hidden" value = "${list_num}" id = "list_num" name = "list_num">
	<table border = "0" cellpadding = "5" cellspacing = "0"  frame = "hsides" rules ="rows" id = "board_table">
		<tr>
			<th width = "100">글번호</th>
			<th width = "300">제목</th>
			<th width = "100">작성자</th>
			<th width = "100">작성일</th>
			<th width = "100">조회수</th>
		</tr>
		
		<c:if test = "${list != 'null' || list ne null}">

				<c:forEach var="boardDTO" items="${list}" varStatus = "i">
					<fmt:parseDate value='${boardDTO.logtime}' var='logtime' pattern='yyyy-MM-dd'/>
					<tr>
							<td class = "board">${boardDTO.seq}</td>
				
					<td class = "board">
							<c:forEach var = "i" begin = "1" end = "${boardDTO.lev}" step = "1">
						&emsp;
					</c:forEach>
					
					<c:if test = "${boardDTO.lev != 0}">
						<img src = "../image/reply.gif" />
					</c:if>
					<a href = "#" id = "view_aTag">${boardDTO.subject}</a></td>
					<td class = "board">${boardDTO.id}</td>
					<td class = "board"><fmt:formatDate value="${logtime}" pattern="yyyy.MM.dd"/></td>
					<td class = "board">${boardDTO.hit}</td>
										
				</tr>
				
			</c:forEach>
		</c:if>
	</table>
	<br><br>
	<input type = "button" value = "10개씩" class = "board_list_a"/>
	<input type = "button" value = "5개씩" class = "board_list_a"/><br>
	
	
	
</div>

<div align = "center">
<!-- <img style = "float : center; cursor: pointer;" src = "../image/aa.png" onclick = "location.href = '../main/index.jsp'"> -->
<div id = "paging_div"style = "float : center; border : 1px red solid; width : 600px; text-align : center;" align = "center">${boardPaging.pagingHTML}</div>
</div>
<form action = "/miniproject/board/boardSearch.do" method = "post" >
	<div>
		<input type = "hidden" name = "pg" id = "pg" value = "${pg}">
		<select name = "searchOption" id = "select_option">
			<option selected="selected">제목</option>
			<option>아이디</option>
		</select>
		<input type = "text" name = "keyword" id = "keyword"/>
		<input type = "button" id = "search_btn" value = "검색" />
	</div>
</form>
</html>