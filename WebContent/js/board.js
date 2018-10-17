$(document).ready(function(){
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
	
	//boardList ajax
	$(document).on('click', '.board_list_a', function(){
		/*alert(parseInt($(this).attr('value')));*/
		var list_num = 5;
		if(!isNaN(parseInt($(this).attr('value')))){
			list_num = parseInt($(this).attr('value'));
			alert(list_num);
		}
		
		
		
		/*var list_num = 10;*/ 
		$.ajax({
			type :  "POST",
			url : "/miniproject/board/boardList.do",
			data : {"list_num" : list_num, "display" : '/board/boardList.jsp'},
			dataType : "html",	//html, text, json
			success : function(data){
				$('#display').html(data);
			}
			///miniproject/board/boardList.do
		});
		
		
	});
	
	
	
});