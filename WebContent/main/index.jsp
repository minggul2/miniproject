<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center">
	<tr>
		<td colspan="4" align="center">
			<jsp:include page="../template/top.jsp"></jsp:include>
		</td>
	</tr>
	
	<tr>
		<td colspan="1" align="center">
			<jsp:include page = "../template/left.jsp"></jsp:include>	
		</td>
		<td colspan="3" align="center" id = "display">
			<jsp:include page="${display }" flush="true"></jsp:include>
		</td>		
	</tr>
	
		<tr>
			<td colspan="4" align="center">
			<jsp:include page="../template/bottom.jsp"></jsp:include>

