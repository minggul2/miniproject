<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<form name = "loginForm" method = "post" action = "/miniproject/member/login.do">
	 <table border = "1" align = "center" cellpadding = "5" cellspacing = "0">
	  <caption><h2>로그인</h2></caption>
	   <tr>
	    <td>아이디</td>
	    <td><input type = "text" name = "login_id">
	    	<div id = "login_id_div"></div>
	    
	    </td>
	   </tr>
	   
	   <tr>
	    <td>비밀번호</td>
	    <td><input type = "password" name = "login_password">
	    	<div id = "login_pwd_div"></div>
	    </td>
	   </tr>
	  
	   <tr align = "center">
	    <td colspan = "2">
	    ${fail}
	    <c:if test = "${fail != null && memId == null}">
	    	<div id = "login_error_div">아이디 또는 비밀번호가 틀립니다</div>
	    </c:if>
	     <input type = "button" value = "로그인" id = "login_button">
	     <input type = "button" value = "회원가입" onclick = "location.href = '/miniproject/member/writeForm.do'">
	    </td>
	   </tr>
	 </table>
	</form>
		<script src = "http://code.jquery.com/jquery-3.3.1.min.js"></script>
		 <script src = "../js/member.js"></script>
