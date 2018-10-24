<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/jquery-picZoomer.css">
	<script src = "http://code.jquery.com/jquery-3.3.1.min.js"> </script>
	<script src = "../js/jquery.picZoomer.js"></script>
	<script>
		$(document).ajaxComplete(function(){
			$('.picZoomer').picZoomer();
			$('.picZoomer img').click(function(){
				alert("");
			});
			alert("");
		});
	</script>
</head>


<body>
	<div align = "left"><img src = "../image/plus.png " width = "10" height = "10" id = "plus"/></div>
	<table border = "1">
		<tr>
			<td rowspan = "4">
			<div class = "picZoomer">
				<img alt = "" src = "../upload/${imageboardDTO.image1}" width = "320" height = "320"/>
					<!-- id = "image1" class = "image" -->
			</div>
			</td>
			<td>상품명${imageboardDTO.imageName}</td>
		</tr>
		
		<tr>
			<td>단가${imageboardDTO.imagePrice}</td>
		</tr>
		<tr>
			<td>개수${imageboardDTO.imageQty}</td>
		</tr>
		<tr>
			<td>합계${imageboardDTO.imagePrice * imageboardDTO.imageQty}</td>
		</tr>
		<tr>
			<td colspan = "2"><p>${imageboardDTO.imageContent}</p></td>
		</tr>
	</table>
	${imageboardDTO.seq}
	
</body>
</html>

http://html5around.com/wordpress/tutorials/jquery-magnify-plugin/