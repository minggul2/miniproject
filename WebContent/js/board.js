

$(document).ready(function(){
	//뒤로가기처리
	var back_page;
	
	
	var list_num = 5;
	var pg = 1;
	$(document).on('click', '#board_write_button', function(){
		alert("");
		$('#content_div').empty();
		$('#subject_div').empty();
		
		if($('#content').val() == ''){
			$('#content_div').html("내용을 입력하세요");
		}else if($('#subject').val() == ''){
			$('#subject_div').html("제목을 입력하세요");
		}else{
			$('#board_write_form').submit();
		}
	});
	
	//게시글 a태그 이벤트
	//$('#board_table').on('click', '#view_aTag', function(){	//#board_table도 동적으로 생성되었기떄문에 또다른 부모찾아야함
	$('#display').on('click', '#view_aTag', function(){
		var seq = $(this).parent().prev().text();
		var pg = $('#pg').val();
		location.href = '/miniproject/board/boardView.do?seq='+seq+'&pg='+pg;	
	});
	
	//글수정 이벤트
	$('#view_div').on('click', '#board_modify_button', function(){
		
		var seq = $('#seq').val();
		var pg = $('#pg').val();
		
		location.href = 'boardModifyForm.do?seq='+seq+'&pg='+pg;
	});
	
	//글삭제 이벤트
	$('#delete_board').on('click', function(){
		
		location.href = "boardDelete.do?seq="+seq;
	});
	
	//페이지 이동 a태그 이벤트
	$(document).on('click', '#paging_div > a, .board_list_a', function(){
		/*
		boardList 영역에서는
		목록보기
		[이전][다음]
		[1][2][3]
		10개씩보기 5개씩 보기
		
		4가지 유형이있음
		목록보기 -> pg = 1로 해결
		[이전][다음] -> class 로 해결 (맨앞에 i)
		[1][2][3] -> html()로 해결
		10개씩보기 5개씩 보기 -> attr('value') 로 해결 (버튼 밸류값 첫글자 숫자따옴)
		*/
		
		//뒤로가기
		console.log(location.hash);
		
		var pg_move = parseInt($(this).attr('class'));

		if(!isNaN(parseInt($(this).attr('value')))){	//누른것이 10목록, 5목록 일 경우
			//.board_list_a 공유하는 class는 top.jsp의 a태그와 boardList.jsp의 10목록버튼, 5목록버튼 그중 10목록, 5목록눌렀을경우
			list_num = parseInt($(this).attr('value'));
			//alert("페이지 게시글 개수 : " + list_num);
		}else if(!isNaN($(this).html())){		//누른것이 [1][2][3] 일 경우
			pg = $(this).html();		
		}else if(pg_move){					//누른것이 [이전][다음] 일 경우
			pg = parseInt($(this).attr('class'));
		}
	/*	if(document.location.hash){	
			var hash = document.location.hash.replace('#page', '');
			pg = hash;
		}*/
		
		$.ajax({
			type :  "POST",
			url : "/miniproject/board/boardList.do",	//boardList.do를 여기서 호출함	이부분에서 url요청으로 인해 index.jsp 까지 감
			data : {"list_num" : list_num, "display" : '/board/boardList.jsp', "pg" : pg},		//boardList.do 에서 필요한 list_num 정보 담아감 display는 필요없을거같음
			dataType : "html",	//html, text, json
			success : function(data){	//이 영역은 비동기 아래 코드가 끝나고 실행됨
				$('#display').html(data);	//여기가 중요한데 <jsp:include page = "${display}"> 영역을 아예 갈아치워버림
				document.location.hash = "#page" + pg;
				back_page = pg;
			}
			///miniproject/board/boardList.do
		});
	});
	
	$(window).bind('hashchange', function(){
		alert(location.hash);
		if(location.hash == ""){
			
			pg = back_page;
			$('#paging_div > a, .board_list_a').trigger('click');
		}
		
	});
	
});