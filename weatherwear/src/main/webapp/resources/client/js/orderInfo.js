let orderList = [];
let orderStatus;

let id;				// 글 번호
let orderId;		// 주문번호
let optionId;		// 옵션번호
let reason;			// 사유
let image;			// 이미지
let email;			// 이메일
let regDate;		// 요청일자
let deliverWay;		// 수거 방식(직접발송/택배수거)
let cost;			// 택배비용
let costMtd;		// 택배에동봉, 무통장입금
let status;			// 상태(요청,진행,완료)
let bankId;			// 환불 은행
let refundBankNum;	// 환불 계좌
let requestWhat;	// 교환/환불
let keyword;		// 오배송/상품하자/단순변심/사이즈변경


let reviewContent;
let reviewStar;
let reviewDate;
let reviewStatus = '일반';
let productId;

// 배송 방법 설정(배송비 설정)
	function setDeliveryMtd(id){
		if(document.querySelector("input[name='radioCheck']:checked") == null){	return;	}
		switch(id){
		case "직접발송":
			if(document.querySelector("#단순변심:checked") || document.querySelector("#사이즈변경:checked") ){	cost = 3000; 
			} else {	cost = 0;}
			break;
		case "택배수거":
			if(document.querySelector("#단순변심:checked") || document.querySelector("#사이즈변경:checked") ){	cost = 6000; 
			} else {	cost = 0;}
			break;
		}
		setCost(cost);
	}

// 사유 선택(배송비 설정)
	function setReason(id){
		cost = 0;
		switch(id){
		case "오배송": case "상품하자": cost = 0; break;
		case "단순변심": case "사이즈변경": 
			if(document.querySelector("#직접발송:checked")){	cost = 3000;
			}else {		cost = 6000; 	}
			break;
		}	
		keyword = id;
		setCost(cost);
	}

// 배송비 적용
function setCost(cost){
	let costContent = "<div class='modalDiv'><label for='content'><b>배송비 지불 방법</b></label><div class='payDiv'></div>";
	costContent += "<div class='custom-control custom-radio'><div class='costMtd'></div>";
	costContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='택배에동봉' name='payMtd'>&nbsp;&nbsp;&nbsp;";
	costContent += "<label for='택배에동봉' class='custom-control-label'>택배에동봉</label></div>";
	costContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='무통장입금' name='payMtd'>&nbsp;&nbsp;&nbsp;";
	costContent += "<label for='무통장입금' class='custom-control-label'>무통장입금</label></div>";
	if(requestWhat == "refund"){
		costContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='결제금액차감' name='payMtd'>&nbsp;&nbsp;&nbsp;";
		costContent += "<label for='결제금액차감' class='custom-control-label'>결제 금액에서 제외하고 차감</label></div></div></div><hr>";
	}
	if (cost >0){
		document.querySelector(".payMtd").innerHTML = costContent;
	} else {
		document.querySelector(".payMtd").innerHTML = "";
	}
	document.querySelector(".costMtd").innerHTML = "<b>택배비 : " + cost + "원</b><br>";
}

// 주문 취소
	function cancleOrder(target){
		orderId = target.split("_")[0];
		optionId = target.split("_")[1];
		orderStatus = "취소요청";
		
		Swal.fire({
			title: "주문을 취소하시겠습니까?",
			text: "주문 취소를 철회하고 싶은 경우 문의 바랍니다.",
			icon: "question",
		  	showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: "취소하기",
		  	cancelButtonText: "닫기",
			reverseButtons: true, // 버튼 순서 거꾸로
		}).then((result) => {
			if(result.isConfirmed){	// 매개변수 list 안됨
				playAjax();
			}
		});
	}

// 교환 요청
	function swapOrder(target){	
		orderId = target.split("_")[0];
		optionId = target.split("_")[1];
		status = "교환요청";
		orderStatus = "교환요청";
		requestWhat = "swap";
		
		let swapContent = "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>교환 요청</h4>";
		swapContent += "<button type='button' class='close form-control' style='width:10%; background-color:#dee2e6;' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
		swapContent += "<div class='modal-body'>";
		swapContent += "<div class='modalDiv'><label for='content'><b>교환 요청 사유</b></label><div class='codeDiv'></div>";
		swapContent += "<div class='custom-control custom-radio'>";
		swapContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='오배송' name='radioCheck' onclick='setReason(this.id)'>";
		swapContent += "<label for='오배송' class='custom-control-label'>오배송</label>";
		swapContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='상품하자' name='radioCheck' onclick='setReason(this.id)'>";
		swapContent += "<label for='상품하자' class='custom-control-label'>상품하자</label>";
		swapContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='단순변심' name='radioCheck' onclick='setReason(this.id)'>";
		swapContent += "<label for='단순변심' class='custom-control-label'>단순변심</label>";
		swapContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='사이즈변경' name='radioCheck' onclick='setReason(this.id)'>";
		swapContent += "<label for='사이즈변경' class='custom-control-label'>사이즈변경</label></div>";
		swapContent += "<textarea class='form-control' name='content' id='content' placeholder='입력해주세요'></textarea></div><hr>";
		swapContent += "<div class='modalDiv' id='dropzone' style='width:100%'><div id='image' class='dz-message needsclick baseImgDiv'>";
		swapContent += "<span class='text' style='display:flex; align-items:center; flex-direction:column;' onclick='playDropzone()'>";
		// key값 // imageStatus 변경하기(교환/리뷰/환불/문의)
		swapContent += "<input type='hidden' id='key' value='client'><input type='hidden' id='imageStatus' value='교환'>";
		swapContent += "<img src='resources/util/image/dropzone_camera.png' alt='Camera' />";
		swapContent += "<code>이미지를 등록하시려면 클릭하세요</code><br></span>";
		swapContent += "</div></div><hr>";
		swapContent += "<div class='modalDiv'><label for='content'><b>교환 방법</b></label><div class='wayCodeDiv'></div>";
		swapContent += "<div class='custom-control custom-radio'><div class='costMtd'></div>";
		swapContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='직접발송' name='deliveryMtd' onclick='setDeliveryMtd(this.id)'>&nbsp;&nbsp;&nbsp;";
		swapContent += "<label for='직접발송' class='custom-control-label'>직접발송</label></div>";
		swapContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='택배수거' name='deliveryMtd' onclick='setDeliveryMtd(this.id)'>&nbsp;&nbsp;&nbsp;";
		swapContent += "<label for='택배수거' class='custom-control-label'>택배수거</label></div></div><div class='payMtd'></div></div>";
		swapContent += "<div class='modalDiv'><b>이메일</b><div class='emailDiv'></div>";
		swapContent += "<div class='custom-control custom-radio'>";
		swapContent += "<label for='email'></label><input type='email' class='form-control form-control-border border-width-2' name='email' id='email' placeholder='weatherwear@email.com'style='width:80%;'></div><br>";
		swapContent += "<div class='modal-footer justify-content-between'><button type='button' class='form-control' data-dismiss='modal' onclick='closeModal()' style='width:100px;'>취소하기</button>";
		swapContent += "<button type='button' class='btn btn-primary insertBtn' onclick='submit()' style='width:100px;'>등록하기</button></div></div></div>";
		
		openModel(swapContent);
	}

// 환불 요청
	function refundOrder(target){	
		orderId = target.split("_")[0];
		optionId = target.split("_")[1];
		status = "환불요청";
		orderStatus = "환불요청";
		requestWhat = "refund";
		
		let refundContent = "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>환불 요청</h4>";
		refundContent += "<button type='button' class='close form-control' style='width:10%; background-color:#dee2e6;' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
		refundContent += "<div class='modal-body'>";
		refundContent += "<div class='modalDiv'><label for='content'><b>환불 요청 사유</b></label><div class='codeDiv'></div>";
		refundContent += "<div class='custom-control custom-radio'>";
		refundContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='오배송' name='radioCheck' onclick='setReason(this.id)'>";
		refundContent += "<label for='오배송' class='custom-control-label'>오배송</label>";
		refundContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='상품하자' name='radioCheck' onclick='setReason(this.id)'>";
		refundContent += "<label for='상품하자' class='custom-control-label'>상품하자</label>";
		refundContent += "<input class='custom-control-input custom-control-input-danger' type='radio' id='단순변심' name='radioCheck' onclick='setReason(this.id)'>";
		refundContent += "<label for='단순변심' class='custom-control-label'>단순변심</label></div>";
		refundContent += "<textarea class='form-control' name='content' id='content' placeholder='입력해주세요'></textarea></div><hr>";
		refundContent += "<div class='modalDiv' id='dropzone' style='width:100%'><div id='image' class='dz-message needsclick baseImgDiv'>";
		refundContent += "<span class='text' style='display:flex; align-items:center; flex-direction:column;' onclick='playDropzone()'>";
		// key값 // imageStatus 변경하기(환불/리뷰/환불/문의)
		refundContent += "<input type='hidden' id='key' value='client'><input type='hidden' id='imageStatus' value='환불'>";
		refundContent += "<img src='resources/util/image/dropzone_camera.png' alt='Camera' />";
		refundContent += "<code>이미지를 등록하시려면 클릭하세요</code><br></span>";
		refundContent += "</div></div><hr>";
		refundContent += "<div class='modalDiv'><label for='content'><b>환불 방법</b></label><div class='wayCodeDiv'></div>";
		refundContent += "<div class='custom-control custom-radio'><div class='costMtd'></div>";
		refundContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='직접발송' name='deliveryMtd' onclick='setDeliveryMtd(this.id)'>&nbsp;&nbsp;&nbsp;";
		refundContent += "<label for='직접발송' class='custom-control-label'>직접발송</label></div>";
		refundContent += "<div><input class='custom-control-input custom-control-input-danger' type='radio' id='택배수거' name='deliveryMtd' onclick='setDeliveryMtd(this.id)'>&nbsp;&nbsp;&nbsp;";
		refundContent += "<label for='택배수거' class='custom-control-label'>택배수거</label></div></div><div class='payMtd'></div></div>";
		refundContent += "<div class='modalDiv'><b>이메일</b><div class='emailDiv'></div>";
		refundContent += "<div class='custom-control custom-radio'>";
		refundContent += "<label for='email'></label><input type='email' class='form-control form-control-border border-width-2' name='email' id='email' placeholder='weatherwear@email.com'style='width:80%;'></div><br>";	
		
		// 무통장 입금 시 
		if($("#paymentMethodInput").val() == "무통장입금"){
			refundContent += "<div class='modalDiv'><b>환불 계좌 입력</b><div class='bankDiv'></div>";
			refundContent += "<div class='custom-control custom-radio'>";
			refundContent += "<select class='select' id='bankId'><option value='001'>한국은행</option>";
			refundContent += "<option value='003'>기업은행</option><option value='004'>국민은행</option>";
			refundContent += "<option value='007'>수협은행</option><option value='011'>농협은행</option>";
			refundContent += "<option value='020'>우리은행</option><option value='023'>SC제일은행</option>";
			refundContent += "<option value='027'>씨티은행</option><option value='032'>부산은행</option>";
			refundContent += "<option value='035'>제주은행</option><option value='037'>전북은행</option>";
			refundContent += "<option value='039'>경남은행</option><option value='045'>새마을금고</option>";
			refundContent += "<option value='048'>신협은행</option><option value='050'>상호저축은행</option>";
			refundContent += "<option value='071'>우체국은행</option><option value='081'>하나은행</option>";
			refundContent += "<option value='088'>신한은행</option><option value='089'>케이뱅크</option>";
			refundContent += "<option value='090'>카카오뱅크</option><option value='092'>토스뱅크</option></select>";
			refundContent += "<label for='refundBankNum'></label><input type='text' class='form-control form-control-border border-width-2' name='refundBankNum' id='refundBankNum' style='width:80%;' placeholder='계좌번호'></div><br>";
		}
		refundContent += "<div class='modal-footer justify-content-between'><button type='button' class='form-control' data-dismiss='modal' onclick='closeModal()' style='width:100px;'>취소하기</button>";
		refundContent += "<button type='button' class='btn btn-primary insertBtn' onclick='submit()' style='width:100px;'>등록하기</button></div></div></div>";
		
		openModel(refundContent);
	}
	
// 교환/환불 등록
	function submit(){
		// 사유 선택 확인
		let radioCheck = document.querySelector("input[name='radioCheck']:checked");
		if(radioCheck == null){
			document.querySelector(".codeDiv").innerHTML = "<code>사유를 체크해주세요</code><br>";
			return;
		} else {
			document.querySelector(".codeDiv").innerHTML = "";
		}
		
		// 내용 확인
		reason = $("#content").val();
		if(reason == null || reason == '' || reason.length<10){
			document.querySelector(".codeDiv").innerHTML = "<code>사유를 10자 이상 입력해주세요</code><br>";
			$("#content").focus();
			return;
		} else {
			document.querySelector(".codeDiv").innerHTML = "";
		}
		
		// 수거방법 확인
		deliverWay = document.querySelector("input[name='deliveryMtd']:checked");
		if(deliverWay == null){
			document.querySelector(".wayCodeDiv").innerHTML = "<code>수거 방법을 체크해주세요</code><br>";
			return;
		} else {
			document.querySelector(".wayCodeDiv").innerHTML = "";
		}
		
		// 이메일 확인
		email = $("#email").val();
		if(email == null || email == ''){
			document.querySelector(".emailDiv").innerHTML = "<code>이메일 주소를 입력해주세요</code><br>";
			$("#email").focus();
			return;
		} else {
			document.querySelector(".emailDiv").innerHTML = "";
		}
		
		// 배송비 지불 방법 확인
		let costMethod = document.querySelector("input[name='payMtd']:checked");
		if(costMethod == null || costMethod == ''){
			if(cost>0){
				document.querySelector(".payDiv").innerHTML = "<code>배송비 지불 방법을 선택해주세요</code><br>";
				return;
			}
			costMtd = "무통장입금";
		} else {
			costMtd = costMethod.id;
			document.querySelector(".payDiv").innerHTML = "";
		}
		
		// 계좌번호 확인
		if($("#paymentMethodInput").val() == "무통장입금"){
			bankId = $("#bankId").val();
			refundBankNum = $("#refundBankNum").val();
			if(refundBankNum == null || refundBankNum == ""){
				document.querySelector(".bankDiv").innerHTML = "<code>환불받을 계좌번호를 입력해주세요</code><br>";
				$("#refundBankNum").focus();
				return;
			} else {
				document.querySelector(".bankDiv").innerHTML = "";
			}
		}
		
		$.ajax({
			url: "insertSwapRefund.do",
			type: "POST",
			async: true,
			dataType: "json",
			data: {
				requestWhat: requestWhat,
				orderId: orderId,
				optionId: optionId,
				reason: reason,
				email: email,
				deliverWay: deliverWay.id,
				cost: parseInt(cost),
				costMtd: costMtd,
				status: status,
				bankId: bankId,
				refundBankNum: refundBankNum,
				keyword: keyword
			},
			success: function(res){
				if(res.code == 1){
					playAjax();
				} else {
					playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
				}
			},
			error : function(error){
				playToast("오류가 발생했습니다.", 'error');
			}
		});
	}
	
// 리뷰 작성
	function reviewWrite(product_id, order_id, option_id, productName, mainImage, proCnt, optionColor, optionSize){
		productId = product_id;
		orderId = order_id;
		optionId = option_id;
		let statusName = "orderStatus_" + optionId;
		orderStatus = $("input[name='" + statusName + "'").val();

		let reviewContent = "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>리뷰 작성</h4>";
		reviewContent += "<button type='button' class='close form-control' style='width:10%; background-color:#dee2e6;' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
		reviewContent += "<div class='modal-body'>";
		reviewContent += "<div class='modalDiv'><div class='row'><div class='col-md-4'>";
		reviewContent += "<img class='product_image' src='" + mainImage +"' style='height:150px; width:150px;'></div>";
		reviewContent += "<div class='col-md-1'></div><div class='col-md-7'><br><b>" + productName +"</b><br><span>";
		reviewContent += optionColor + " / " + optionSize + " : " + proCnt + "</span></div><br><br></div><hr>";
		reviewContent += "</div><div class='modalDiv'><b>별점</b><div class='starDiv'></div>";
		reviewContent += "<div class='custom-control custom-radio'>";
		reviewContent += "<fieldset class='rate'>";
		reviewContent += "<input type='radio' id='reviewStar10' name='reviewStar' value='10'><label for='reviewStar10' title='5점'></label>";
		reviewContent += "<input type='radio' id='reviewStar9' name='reviewStar' value='9'><label class='half' for='reviewStar9' title='4.5점'></label>";
		reviewContent += "<input type='radio' id='reviewStar8' name='reviewStar' value='8'><label for='reviewStar8' title='4점'></label>";
		reviewContent += "<input type='radio' id='reviewStar7' name='reviewStar' value='7'><label class='half' for='reviewStar7' title='3.5점'></label>";
		reviewContent += "<input type='radio' id='reviewStar6' name='reviewStar' value='6'><label for='reviewStar6' title='3점'></label>";
		reviewContent += "<input type='radio' id='reviewStar5' name='reviewStar' value='5'><label class='half' for='reviewStar5' title='2.5점'></label>";
		reviewContent += "<input type='radio' id='reviewStar4' name='reviewStar' value='4'><label for='reviewStar4' title='2점'></label>";
		reviewContent += "<input type='radio' id='reviewStar3' name='reviewStar' value='3'><label class='half' for='reviewStar3' title='1.5점'></label>";
		reviewContent += "<input type='radio' id='reviewStar2' name='reviewStar' value='2'><label for='reviewStar2' title='1점'></label>";
		reviewContent += "<input type='radio' id='reviewStar1' name='reviewStar' value='1'><label class='half' for='reviewStar1' title='0.5점'></label></fieldset>";
		reviewContent += "</div><hr><b>리뷰 내용</b><div class='contentDiv'></div><br>";
		reviewContent += "<textarea class='form-control' name='content' id='content' placeholder='최소 10자 이상 입력해주세요' style='height:150px;'></textarea></div><hr>";
		reviewContent += "<b>사진 첨부</b><div class='modalDiv' id='dropzone' style='width:100%'>";
		reviewContent += "<div id='image' class='dz-message needsclick baseImgDiv'>";
		reviewContent += "<span class='text' style='display:flex; align-items:center; flex-direction:column;' onclick='playDropzone()'>";
		reviewContent += "<input type='hidden' id='key' value='client'><input type='hidden' id='imageStatus' value='리뷰'>";
		reviewContent += "<img src='resources/util/image/dropzone_camera.png' alt='Camera' />";
		reviewContent += "<code>이미지를 등록하시려면 클릭하세요</code><br></span>";
		reviewContent += "</div></div><hr><br>";
		reviewContent += "<div class='modal-footer justify-content-between'><button type='button' class='form-control' data-dismiss='modal' onclick='closeModal()' style='width:100px;'>취소하기</button>";
		reviewContent += "<button type='button' class='btn btn-primary insertBtn' onclick='registerReview()' style='width:100px;'>등록하기</button></div></div></div>";
		
		openModel(reviewContent);
	}

// 리뷰 등록
function registerReview(){
	
	// 별점 선택 확인
	let reviewStarInput = document.querySelector("input[name='reviewStar']:checked");
	let reviewStar;
	if(reviewStarInput == null){
		document.querySelector(".starDiv").innerHTML = "<code>별점을 선택해주세요</code><br>";
		return;
	} else {
		reviewStar = parseInt(reviewStarInput.value)/2;
		document.querySelector(".starDiv").innerHTML = "";
	}
	
	// 내용 확인
	let content = $("#content").val();
	if(content == null || content == '' || content.length<10){
		document.querySelector(".contentDiv").innerHTML = "<code>사유를 10자 이상 입력해주세요</code><br>";
		$("#content").focus();
		return;
	} else {
		document.querySelector(".contentDiv").innerHTML = "";
	}
	
	$.ajax({
		url: "insertReview.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: {
			orderId : orderId,
			optionId : optionId,
			reviewContent : content,
			reviewStar: reviewStar,
			reviewStatus: reviewStatus,
			productId: productId,
		},
		success: function(res){
			if(res.code == 1){
				if(orderStatus == "배송중"){
					orderStatus = "배송완료";
					playAjax();
				} else {
					playToast("리뷰가 등록되었습니다", "success");
					setTimeout(function(){
						window.location.reload();
					}, 1500);
				}
			} else {
				playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
			}
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 리뷰 보기
function reviewView(reviewId, productName, mainImage, proCnt, optionColor, optionSize){
	$.ajax({
		url: "getReviewInfo.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: { reviewId: reviewId },
		success: function(res){
			let star = parseInt(parseFloat(res.data.review.reviewStar) * 2);
			let reviewResult = "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>리뷰 보기</h4>";
			reviewResult += "<button type='button' class='close form-control' style='width:10%; background-color:#dee2e6;' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
			reviewResult += "<div class='modal-body'>";
			reviewResult += "<div class='modalDiv'><div class='row'><div class='col-md-4'>";
			reviewResult += "<img class='product_image' src='" + mainImage +"' style='height:150px; width:150px;'></div>";
			reviewResult += "<div class='col-md-1'></div><div class='col-md-7'><br><b>" + productName +"</b><br><span>";
			reviewResult += optionColor + " / " + optionSize + " : " + proCnt + "</span></div><br><br></div><hr>";
			reviewResult += "</div><div class='modalDiv'><b>별점</b><div class='starDiv'></div>";
			reviewResult += "<div class='custom-control custom-radio'>";
			reviewResult += "<fieldset class='rated'>";
			reviewResult += "<input type='radio' id='reviewStar10' name='reviewStar' value='10' disabled";
			if(star == 10){
				reviewResult += " checked";
			}
			reviewResult += "><label for='reviewStar10' title='5점'></label>";
			reviewResult += "<input type='radio' id='reviewStar9' name='reviewStar' value='9' disabled";
			if(star == 9){
				reviewResult += " checked";
			}
			reviewResult += "><label class='half' for='reviewStar9' title='4.5점'></label>";
			reviewResult += "<input type='radio' id='reviewStar8' name='reviewStar' value='8' disabled";
			if(star == 8){
				reviewResult += " checked";
			}
			reviewResult += "><label for='reviewStar8' title='4점'></label>";
			reviewResult += "<input type='radio' id='reviewStar7' name='reviewStar' value='7' disabled";
			if(star == 7){
				reviewResult += " checked";
			}
			reviewResult += "><label class='half' for='reviewStar7' title='3.5점'></label>";
			reviewResult += "<input type='radio' id='reviewStar6' name='reviewStar' value='6' disabled";
			if(star == 6){
				reviewResult += " checked";
			}
			reviewResult += "><label for='reviewStar6' title='3점'></label>";
			reviewResult += "<input type='radio' id='reviewStar5' name='reviewStar' value='5' disabled";
			if(star == 5){
				reviewResult += " checked";
			}
			reviewResult += "><label class='half' for='reviewStar5' title='2.5점'></label>";
			reviewResult += "<input type='radio' id='reviewStar4' name='reviewStar' value='4' disabled";
			if(star == 4){
				reviewResult += " checked";
			}
			reviewResult += "><label for='reviewStar4' title='2점'></label>";
			reviewResult += "<input type='radio' id='reviewStar3' name='reviewStar' value='3' disabled";
			if(star == 3){
				reviewResult += " checked";
			}
			reviewResult += "><label class='half' for='reviewStar3' title='1.5점'></label>";
			reviewResult += "<input type='radio' id='reviewStar2' name='reviewStar' value='2' disabled";
			if(star == 2){
				reviewResult += " checked";
			}
			reviewResult += "><label for='reviewStar2' title='1점'></label>";
			reviewResult += "<input type='radio' id='reviewStar1' name='reviewStar' value='1' disabled";
			if(star == 1){
				reviewResult += " checked";
			}
			reviewResult += "><label class='half' for='reviewStar1' title='0.5점'></label></fieldset>";
			reviewResult += "</div><hr><b>리뷰 내용</b><div class='contentDiv'></div><br><span>";
			reviewResult += res.data.review.reviewContent;
			reviewResult += "</span></div><hr>";
			if(res.data.review.reviewStatus == "포토"){
				let imageList = "" + res.data.reviewImage;
				let images = imageList.split(',');
				reviewResult += "<b>첨부 사진</b><div class='modalDiv' style='width:100%'>";
				for(let i=0; i<images.length; i++){
					reviewResult += "<img class='product_image' src='" + images[i] +"' style='height:auto; width:100%;' id='" + images[i] + "' onclick='openImage()'><br><br>";
				}
			}
			reviewResult += "</div><br>";
			reviewResult += "<div class='modal-footer justify-content-between'><button type='button' id='" + reviewId + "' class='form-control' data-dismiss='modal' onclick='deleteReview()' style='width:100px;'>삭제하기</button></div></div></div>";
			
			openModel(reviewResult);
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 이미지 확대
function openImage(){
	let imageDir = event.target.id;
	Swal.fire({
		title: "<br>",
		html: "<div class='row' style='width:100%; height:100%;'><img src='" + imageDir + "' style='100%; height:auto;'></div>",
		text: "확인",
		width: "1000px",
		confirmButtonColor: "dimgrey",
		confirmButtonText: "닫기"
	}).then(function() {
		return;
	});
}

// 리뷰 삭제
function deleteReview(){
	let reviewId = event.target.id;
	playConfirm("리뷰를 삭제하시겠습니까?", null, "question", "삭제하기", "취소하기", "deleteReviewProc('" + reviewId + "')", "playToast('취소하셨습니다.', null)");
}

function deleteReviewProc(reviewId){
	console.log("reviewId : " + reviewId);
	$.ajax({
		url: "deleteReview.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: {
			reviewId: reviewId
		},
		success: function(res){
			if(res.code == 1){
				playToast(res.message, "success");
				setTimeout(function(){
					window.location.reload();
				}, 2000);
			} else {
				playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
			}
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 주문 상태 변경(orders_info)
function playAjax(){
	$.ajax({
		url: "updateOrder.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: {
			orderId: orderId,
			optionId: optionId,
			orderStatus: orderStatus
		},
		success: function(res){
			if(res.code == 1){
				playToast(res.message, "success");
				setTimeout(function(){
					window.location.reload();
				}, 2000);
			} else {
				playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
			}
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 이미지 업로드
function playDropzone(){
 	// 서버에 전송할 파일과 관련된 추가 정보
	var hiddenkey = $('#key').val();
	var imageStatus = $('#imageStatus').val();
	
 	var dropzoneError = '';
 	Dropzone.autoDiscover = false;
 	const dropzone = new Dropzone('div#dropzone', {
 		url: 'fileUpload.mdo',	// 파일 업로드 로직 호출
 		method: 'post',	
		autoProcessQueue: false,		/** 자동 업로드 */
		autoQueue: true,				/** 파일 업로드시 큐에 자동 업로드 */ 
		clickable: true,				/** 클릭 가능 여부 */
		createImageThumbnails: true,	/** 이미지 미리보기 */
		thumbnailHeight: 150,			/** 이미지 크기 조절 */
		thumbnailWidth: 150,			/** 이미지 크기 조절 */
		paramName: 'images',			/** 서버로 전송될 파일의 파라미터 이름 */
		params: { 						/** 파일과 전송될 추가 정보 */
			key: hiddenkey,
			imageStatus: imageStatus,
			imageBy: orderId + "_" + optionId
		 },
		addRemoveLinkes: true,			/** 삭제 버튼 표시 여부 */
		dicRemoveFile: 'X',				/** 삭제 버튼 텍스트 */
		parallelUploads: 10,			/** 동시 업로드가능한 파일 수 */
		uploadMultiple: true,			/** 다중 업로드 기능 */
		acceptedFiles: '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF',
		dictInvalidFileType: '이미지 파일만 업로드 가능합니다.',
 		previewTemplate: document.querySelector('#dropzone-preview').innerHTML,
 
 		/** 초기 설정 */
 		init: function() {
			var myDropzone = this;
			document.querySelector(".baseImgDiv").style.display="none";
			document.querySelector("#dropzone").style.justifyContent = "";
			reviewStatus = '포토';
			
			// 업로드 대기중인 파일 처리
			$('.insertBtn').on('click', function(e) {
				myDropzone.processQueue();	
			});
			
			// 업로드 대기중/업로드된 파일 모두 삭제
			$('.cancelBtn').on('click', function(e) {
				myDropzone.removeAllFiles(true);	
			});
			
			// maxfilesexceeded : 업로드 개수를 초과하는 경우
			this.on('maxfilesexceeded', function(file) {
				myDropzone.removeFile(file);
			});

			// sendingmultiple : 여러 파일을 동시에 업로드시, 첫 전송 직전에 발생
			this.on('sendingmultiple', function(file, xhr, formData) {
				console.log('보내는 중');
			});
			
			// successmultiple : 다중 파일 업로드 성공한 경우
			this.on('successmultiple', function(file, responseText) {
			});
			
			this.on('error', function(file, errorMessage) {
				console.log(errorMessage);
			});
			
			// queuecomplete : 이벤트 성공 여부 확인 로그
			this.on('queuecomplete', function(e) {
				console.log('queuecomplete');
			});
			
			// 업로드된 파일 삭제
			this.on('removedfile', function(data) {
				console.log("data : " + data.name);
			});
		}
 	});
}


