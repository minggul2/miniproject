<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src = "http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src = "../js/board.js"></script>
	<script>
		$(document).ready(function(){
			alert("글삭제 성공");
			$('#paging_div > a, .board_list_a').trigger('click');
		});
	</script>
</body>
</html>