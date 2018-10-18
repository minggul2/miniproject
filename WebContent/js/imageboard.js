$(document).ready(function(){
	//이미지 글쓰기 버튼
	var img_list_num = 3;
	$('#img_write_btn').on('click', function(){
		
		
		
		$('#imageId_div').empty();
		$('#imageName_div').empty();
		$('#imagePrice_div').empty();
		$('#imageQty_div').empty();
		$('#imageContent_div').empty();
		$('#image1_div').empty();
		
		
		if($('#imageId').val() == '' || $('#imageId').val() == 'img_'){
			$('#imageId_div').html("상품코드를 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageName').val() == ''){
			$('#imageName_div').html("상품이름을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imagePrice').val() == ''){
			$('#imagePrice_div').html("상품가격을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if(isNaN($('#imagePrice').val())){
			$('#imagePrice_div').html("숫자로 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageQty').val() ==''){
			$('#imageQty_div').html("상품개수를 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if(isNaN($('#imageQty').val())){
			$('#imageQty_div').html("숫자로 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageContent').val() == ''){
			$('#imageContent_div').html("상품내용을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#image1').val() == ''){
			$('#image1_div').html("파일을 선택하세요").css('color', 'red').css('font-size', '9pt');
		}else{
			alert("서브밋합니다");
			$('#img_board_form').submit();
		}
	});
	
	//이미지 목록 이벤트
	$(document).on('click', '.imageboard_list_a', function(){
		var pg = 1;
		//var pg = $(this).html();
		var pg_move = parseInt($(this).attr('class'));
		
		if(pg_move){	//pg_move가 숫자가아니라면
			pg = parseInt($(this).attr('class'));
		}
		
		$.ajax({
			type :  "POST",
			url : "/miniproject/imageboard/imageboardList.do",	//boardList.do를 여기서 호출함	이부분에서 url요청으로 인해 index.jsp 까지 감
			data : {"img_list_num" : img_list_num, "display" : '/imageboard/imageboardList.jsp', "pg" : pg},		//boardList.do 에서 필요한 list_num 정보 담아감 display는 필요없을거같음
			dataType : "html",	//html, text, json
			success : function(data){	//이 영역은 비동기 아래 코드가 끝나고 실행됨
				$('#display').html(data);	//여기가 중요한데 <jsp:include page = "${display}"> 영역을 아예 갈아치워버림
			}
			///miniproject/board/boardList.do
		});
	});

	//이미지 a태그 이벤트
	$('#display').on('click', '#img_board_div a', function(){
		var pg = $(this).html();
		var pg_move = parseInt($(this).attr('class'));
		
		if(pg_move){	//pg_move가 숫자가아니라면
			pg = parseInt($(this).attr('class'));
		}
		
		$.ajax({
			type :  "POST",
			url : "/miniproject/imageboard/imageboardList.do",	//boardList.do를 여기서 호출함	이부분에서 url요청으로 인해 index.jsp 까지 감
			data : {"img_list_num" : img_list_num, "display" : '/imageboard/imageboardList.jsp', "pg" : pg},		//boardList.do 에서 필요한 list_num 정보 담아감 display는 필요없을거같음
			dataType : "html",	//html, text, json
			success : function(data){	//이 영역은 비동기 아래 코드가 끝나고 실행됨
				$('#display').html(data);	//여기가 중요한데 <jsp:include page = "${display}"> 영역을 아예 갈아치워버림
			}
			///miniproject/board/boardList.do
		});
	});
	
	
	
	
});