<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_popup_style.css">
	
	<script>
		$(document).ready(function(){
			// 교환/환불 값 변경
			$(".select").change(function(){
				var value = $(this).val();
				if(value == "swap"){
					$(".content_textarea").attr("id", "swapContent");
					console.log("swap");
				} else if(value == "refund"){
					$(".content_textarea").attr("id", "refundContent");
					console.log("refund");
				}
			});
		 	
			// 별점 선택 시 자동 변경 기능 삭제
	 		$(".star_range").on("click", function(){
	 			$(this).off("mousemove");
	 		});
	 		
			// 별점 자동 변경 기능
	 		$(".star_range").on("mousemove", function(event){
	 			var range = $(this);
	 			var value = range.val();
	 			var position = event.pageX - range.offset().left;
	 			value = Math.round((position / range.width()) * range.attr("max"));
	 			console.log(value);
	 			document.querySelector(".star_span span").style.width = (value*10)+"%";
	 			range.val(value);
	 		})
		});

		// 별점 고정
	 	const drawStar = (target) => {
	 		var star = $("input[type='range']").val();
		    document.querySelector(".star_span span").style.width = (star * 10)+ "%";
		}
	</script>
</head>
<body>
	<div class="popup">
<!-- 교환/환불 요청 -->
		<div class="popup_content">
			<h3>교환/환불 요청</h3>
			<form action="#" class="popupForm" method="post" enctype="multipart/form-data">
				<select class="select select_full">
					<option selected>선택</option>
					<option value="swap">교환</option>
					<option value="refund">환불</option>
				</select><br>
				<div class="content_div">
					<textarea class="content_textarea">
					
					</textarea>
					<input type="file">
				</div><br>
				<input type="button" value="신청하기"><br>
			</form>
		</div>
	</div>

<!-- 리뷰 -->
	<div class="popup">
		<div class="popup_content">
			<h3>리뷰</h3>
			<div class="product">
				<section class="productImage">
					이미지
				</section>
				<section class="productInfo">
					<span>상품 이름</span>
					<span>상품 옵션</span>
				</section>
			</div>
			<form action="#" class="popupForm" method="post" enctype="multipart/form-data">
				<div class="star_wrap">
					<div class="star">
						<span class="star_span">
							★★★★★
							<span>★★★★★</span>
							<input type="range" class="star_range" oninput="drawStar(this)" value="1" step="1" min="0" max="10">
						</span>
					</div>
					<div class="content_div">
						<textarea class="content_textarea" id="review">
						
						</textarea>
						<input type="file">
					</div><br>
					<input type="button" value="등록하기"><br>
				</div>
			</form>
		</div>
	</div>
</body>
</html>