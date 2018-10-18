$(document).ready(function(){
	
	var list_num = 5;
	$('#board_write_button').on('click', function(){
		
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
	
	$('#view_div').on('click', '#board_modify_button', function(){
		
		var seq = $('#seq').val();
		var pg = $('#pg').val();
		
		location.href = 'boardModifyForm.do?seq='+seq+'&pg='+pg;
	});
	
	$('#delete_board').on('click', function(){
		
		location.href = "boardDelete.do?seq="+seq;
	});
	
	//페이지 이동 a태그 이벤트
	$('#display').on('click', 'a', function(){
		
		var pg = $(this).html();
		
		var pg_move = $(this).attr('class');
		alert(pg_move);
		alert(parseInt(pg_move));
		
		var test = parseInt(pg_move);
		alert(test);
		
		if(!(parseInt(pg_move))){
			alert(pg_move);
		}
		
		$.ajax({
			type :  "POST",
			url : "/miniproject/board/boardList.do",	//boardList.do를 여기서 호출함	이부분에서 url요청으로 인해 index.jsp 까지 감
			data : {"list_num" : list_num, "display" : '/board/boardList.jsp', "pg" : pg},		//boardList.do 에서 필요한 list_num 정보 담아감 display는 필요없을거같음
			dataType : "html",	//html, text, json
			success : function(data){	//이 영역은 비동기임
				
				$('#display').html(data);	//여기가 상당히 중요한데 <jsp:include page = "${display}"> 영역을 아예 갈아치워버림
			}
			///miniproject/board/boardList.do
		});
	});
	
	//boardList ajax
	$(document).on('click', '.board_list_a', function(){
		//list_num = 5;	//기본값 5로설정
		if(!isNaN(parseInt($(this).attr('value')))){	//.board_list_a 공유하는 class는 top.jsp의 a태그와 boardList.jsp의 10목록버튼, 5목록버튼 그중 10목록, 5목록눌렀을경우
			list_num = parseInt($(this).attr('value'));
			alert(list_num);
		}
		
		$.ajax({
			type :  "POST",
			url : "/miniproject/board/boardList.do",	//boardList.do를 여기서 호출함	이부분에서 url요청으로 인해 index.jsp 까지 감
			data : {"list_num" : list_num, "display" : '/board/boardList.jsp'},		//boardList.do 에서 필요한 list_num 정보 담아감 display는 필요없을거같음
			dataType : "html",	//html, text, json
			success : function(data){	//이 영역은 비동기임
				$('#display').html(data);	//여기가 상당히 중요한데 <jsp:include page = "${display}"> 영역을 아예 갈아치워버림
			}
			///miniproject/board/boardList.do
		});
		//success하든 error하든 얘는 흘러감
		
		
	});
	
	
	
});