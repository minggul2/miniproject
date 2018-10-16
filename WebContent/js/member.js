//이름 아이디 비밀번호 재확인 중복체크

$(document).ready(function(){
	var sId;
	var newWindow = null;
	var post_window;
	
	
	
	//회원가입 이벤트
	$('form[name=writeForm]').on('click', '#write_button', function(){
		var name = $('#name').val();
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		var repwd = $('#repwd').val();
		var idCheck = $('#idCheck').val();
		
		
		$('#name_div').empty();
		$('#id_div').empty();
		$('#password_div').empty();
		$('#repassword_div').empty();
		
		
		if(name == ''){
			$('#name_div').html("이름을 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('#name').focus();
		}else if(id == ''){
			$('#id_div').html("아이디를 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('#id').focus();
		}else if(pwd == ''){
			$('#password_div').html("비밀번호를 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('#pwd').focus();
		}else if(repwd == ''){
			$('#repassword_div').html("재확인 비밀번호를 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('#repwd').focus();
		}else if($('#id').val() != sId){
			$('#id_div').html("중복체크 하세요").css('color', 'red').css('font-size', '9pt');
		}else{
			$('form[name=writeForm]').submit();
		}
	});
	
	//우편 이벤트
	$('#addr_search_button').on('click', function(){
		post_window = window.open("checkPost.do", "", "width=400 height=400 scrollbars=yes");
		
	});
	
	//자식창에서 이벤트
	$('#child_post_search_button').on('click', function(){
		var roadname = $('#roadname').val();
		var sigungu = $('#sigungu').val();
		var sido = $('select[name=sido]').val();
		
		alert(sido);
		location.href = 'checkPost.do?sido='+sido+'&sigungu='+sigungu+'&roadname='+roadname;
		
	});
	
	//중복체크
	$('#check').on('click', function(){
		sId = $('#id').val();
		
		$('#id_div').empty();
		
		if(sId == ''){
			$('#id_div').html("먼저아이디를 입력하세요").css('color', 'magenta').css('font-size', '9pt').css('font-weight', 'bold');
		}else{
			$.ajax({
				type : "POST",
				url : "checkId.do",
				data : {"id" : sId},
				dataType : "text",
				success : function(data){
					data = data.trim();
					
					if(data == '사용 불가능')
						$('#id_div').html(data).css('color', 'red').css('font-size', '9pt').css('font-weight', 'bold');
					else
						$('#id_div').html(data).css('color', 'blue').css('font-size', '9pt').css('font-weight', 'bold');
				}
			});
			
		}
		/*else{
			if(newWindow == null){
				newWindow = window.open("checkId.do?id="+sId,"","width=300 height=100 location=yes");
			}else {
				alert(typeof(newWindow));
				if(typeof(newWindow) == 'undefined' || newWidnow.closed){
					//newWindow.close() == false
					alert("false");
					newWindow.focus();
				}else{
					newWindow = window.open("checkId.do?id="+sId,"","width=300 height=100 location=yes");
				}
			}
		}*/
	});
	
	//자식창 중복체크 다시
	$('#check_again').on('click', function(){
		sId = $('#id_again').val();
		if(sId==''){
			$('#check_id_div').html("아이디를 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else{
			var href = location.href = 'checkId.do?id='+sId;
		}
	});
	
	
	//중복체크에서 사용
	$('#login_ok').on('click', '#use_id', function(){
		$('form[name=writeForm] input[name=id]').val(sId);
		sId = $('#id_able').val();
		//opener.writeForm.id.value = sId;
		$('#id', opener.writeForm).val(sId);
		//$('form[name=writeForm] #pwd').focus();
		$('#pwd', opener.writeForm).focus();
		//opener.writeForm.pwd.focus();
		window.close();
	});
	
	
	
	//로그인 유효성검사
	
	$('form[name=loginForm] #login_button').on('click', function(){
		var id = $('input[name=login_id]').val();
		var pwd = $('input[name=login_password]').val();
		$('#login_id_div').empty();
		$('#login_pwd_div').empty();
		
		
		if(id == ''){
			$('#login_id_div').html("아이디를 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('input[name=login_id]').focus();
		}else if(pwd == ''){
			$('#login_pwd_div').html("비밀번호를 입력하세요").css('color', 'red').css('font-size', '9pt');
			$('input[name=login_password]').focus();
		}else{
			$('form[name=loginForm]').submit();
		}
	});
	
	
	$('form[name=checkPostForm]').on('click', '.addressA', function(){
		
		//var zipcode = $('#addressA').parents().parents().children('td:eq(0)').text();
		//var zipcode = $('#addressA').parent().prev().text();
		var zipcode = $('.addressA:eq(0)').text();
		var addr = $('.addressA:eq(1)').html();
		
		$('#zipcode', opener.document).val(zipcode);
		$('#addr1', opener.document).val(addr);
		$('#addr2', opener.document).focus();
		window.close();
		
	});
	
	//회원정보수정
	$('#modify_btn').on('click', function(){
		location.href = "/miniproject/member/modifyForm.do";
		$.ajax({
			type : "POST",
			url : "../json/modify.json",
			data : {},
			dataType : "json",
			success : function(data){
				alert(data.email1);	
				alert(data.name);
				alert(data.tel1);
				
			}
		});
		
	});
});