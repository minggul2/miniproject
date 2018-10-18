<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<h1><a href = "/miniproject/main/index.do"><img src = "../image/bb.PNG" width = "50" height = "50"></a>MVC를 이용한 미니프로젝트</h1>
	
	<c:if test = "${sessionScope.memId == null}">
	</c:if>
	
	<c:if test = "${sessionScope.memId != null}">
		<a href = "/miniproject/board/boardWriteForm.do">글쓰기</a> |
		<a href = "/miniproject/imageboard/imageboardWriteForm.do" style = "background-color : silver;">이미지등록</a> |
	</c:if>
		<a href = "#" class = "board_list_a" >목록</a> |
		<a href = "#" class = "imageboard_list_a">이미지목록</a> 
		
		<!-- <a href = "/miniproject/board/boardList.do">목록</a> -->
		