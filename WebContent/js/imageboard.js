$(document).ready(function(){
	$('#img_write_btn').on('click', function(){
		
		$('#imageId_div').empty();
		$('#imageName_div').empty();
		$('#imagePrice_div').empty();
		$('#imageQty_div').empty();
		$('#imageContent_div').empty();
		
		
		if($('#imageId').val() == ''){
			$('#imageId_div').html("상품코드를 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageName').val() == ''){
			$('#imageName_div').html("상품이름을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imagePrice').val() == ''){
			$('#imagePrice_div').html("상품가격을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageQty').val() ==''){
			$('#imageQty_div').html("상품개수를 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('#imageContent').val() == ''){
			$('#imageContent_div').html("상품내용을 입력하세요").css('color', 'red').css('font-size', '9pt');
		}else if($('image1').val() == ''){
			alert("");
		}else{
			$('#img_board_form').submit();
		}
	});
});