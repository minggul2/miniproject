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
	<input type = "hidden" value = "${pg}" id = "pg"/>
	<input type = "hidden" value = "${list_num}" id = "list_num" name = "list_num">
	<table border = "0" cellpadding = "5" cellspacing = "0"  frame = "hsides" rules ="rows" id = "board_table">
		<tr>
			<th width = "100">글번호</th>
			<th width = "100">이미지</th>
			<th width = "100">상품명</th>
			<th width = "100">단가</th>
			<th width = "100">개수</th>
			<th width = "100">합계</th>
		</tr>
		
		<c:if test = "${list != 'null' || list ne null}">
			<c:forEach var="imageboardDTO" items="${list}" varStatus = "i">
				<tr>
					<td class = "board">${imageboardDTO.seq}</td>
				
					<td class = "board">
						<img src = "../upload/${imageboardDTO.image1}" width = "100" height = "100"/>
					</td>
					
					<%-- <a href = "#" id = "view_aTag">${imageboardDTO.image1}</a></td> --%>
					<td class = "board">${imageboardDTO.imageName}</td>
					<td class = "board"><fmt:formatNumber type = "number" pattern = "###,###,###" value = "${imageboardDTO.imagePrice}" /></td>
					<td class = "board">${imageboardDTO.imageQty}</td>
					<td class = "board"><fmt:formatNumber type = "number" pattern = "###,###,###" value = "${imageboardDTO.imagePrice * imageboardDTO.imageQty}"/></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<br><br>
	
</div>

<div align = "center">
<!-- <img style = "float : center; cursor: pointer;" src = "../image/aa.png" onclick = "location.href = '../main/index.jsp'"> -->
<div id = "img_board_div"style = "float : center; border : 1px red solid; width : 600px; text-align : center;" align = "center">${boardPaging.pagingHTML}</div>
</div>
<!-- <script src = "http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src ="../js/board.js"> -->
	
<!-- </script> -->
</body>
</html>