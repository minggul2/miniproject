<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<style>
	.td_center {
		text-align : center;
	}
	
	td{
		font-size : 15px;
	}
	
	select{
		height : 25px;
	}
	
	#addressA:link{		
		color : black; text-decoration: none;
	}
	
	#addressA:visited{		
		color : black; text-decoration: none;
	}
	
	#addressA:hover{
		color : green; text-decoration: none;
	}
	
	#addressA:active{
		color : black; text-decoration: none;
	}
	
	<!-- 
		link	 	한번이라도 안누른 상태
		visited 	누르고 난 상태
		hover		마우스 올려 놓은 상태
		active		마우스 꾹 누르는 상태
	
	-->
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name = "checkPostForm" method = "get" action = "checkPost.jsp">                     
	 <table border = "1" cellpadding = "1" cellspacing = "0">                                           
	  <tr>                                                          
	   <td class="td_center">시도</td>                                                
	   <td>
	   		<select name = "sido" >                                       
	   		<option selected>시도선택</option>                      
	   		<option value = "서울">서울</option>                    
	   		<option value = "인천">인천</option>                    
	   		<option value = "대전">대전</option>                    
	   		<option value = "대구">대구</option>                    
	   		<option value = "울산">울산</option>                    
	   		<option value = "세종">세종</option>                    
	   		<option value = "광주">광주</option>                    
	   		<option value = "경기">경기</option>                    
	   		<option value = "강원">강원</option>                    
	   		<option value = "전남">전남</option>                    
	   		<option value = "전북">전북</option>                    
	   		<option value = "경남">경남</option>                    
	   		<option value = "경북">경북</option>
	   		<option value = "충청">충청</option>
	   		<option value = "부산">부산</option>
	   		<option value = "제주">제주</option>
	   		</select>
	   </td>
	   <td class="td_center">시.군.구</td>
	   <td><input type = "text" name = "sigungu" id = "sigungu"></td>
	  </tr>	
	  
	  <tr>
	   <td class="td_center">도로명</td>
	   <td colspan = "4"><input type = "text" name = "roadname" id = "roadname">
	   <input type = "button" value = "검색" id = "child_post_search_button"></td>
	  </tr>
	  
	  <tr>
	   <td class="td_center">우편번호</td>
	   <td colspan = "4" align = "center">주소</td>
	  </tr>
	  
	  <c:forEach var = "zipcodeDTO" items = "${list}" varStatus = "i">
		  <tr>
		  	<td class = "addressA">${zipcodeDTO.zipcode}</td>
		  	<td colspan = "3" >
		  		<a href = "#"  class = "addressA" id = "addressA">${zipcodeDTO.sido} ${zipcodeDTO.yubmyundong} ${zipcodeDTO.ri} ${zipcodeDTO.roadname} ${zipcodeDTO.buildingname}</a>
		  	</td>
		  </tr>
	</c:forEach>
	 
	 </table>
	</form>
	<script src = "http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src = "../js/member.js">
	
	</script>
</body>
</html>