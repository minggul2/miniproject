$(document).ready(function(){
	$('#board_write_button').on('click', function(){
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
	
	$('#board_table').on('click', '#view_aTag', function(){
		var seq = $(this).parent().prev().text();
		var pg = $('#pg').val();
		location.href = 'boardView.do?seq='+seq+'&pg='+pg;
	});
	
	
	$('#view_div').on('click', '#board_modify_button', function(){
		var seq = $('#seq').val();
		var pg = $('#pg').val();
		
		location.href = 'boardModifyForm.do?seq='+seq+'&pg='+pg;
	});
});