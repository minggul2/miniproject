<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.image {
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out;
}
.image:hover {
  transform: scale(1.3);
  -webkit-transform: scale(1.3);
  -moz-transform: scale(1.3);
  -ms-transform: scale(1.3);
  -o-transform: scale(1.3);
}
</style>

<link rel="stylesheet" href="https://thdoan.github.io/magnify/css/magnify.css">

</head>


<body>
	<div align = "left"><img src = "../image/plus.png " width = "10" height = "10" id = "plus"/></div>
	<table border = "1">
		<tr>
			<td rowspan = "4">
			<img src = "../upload/${imageboardDTO.image1}" width = "200" height = "200" id = "image1" class = "image" /></td>
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
	
	<script src = "http://code.jquery.com/jquery-3.3.1.min.js">
	</script>
	<script type="text/javascript" src="../js/cloud-zoom.1.0.2.min.js"></script>
	<script src = "../js/imageboard.js">
	</script>
</body>
</html>

http://html5around.com/wordpress/tutorials/jquery-magnify-plugin/