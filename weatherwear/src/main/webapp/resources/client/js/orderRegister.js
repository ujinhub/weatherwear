/**
 * - 결제방식 선택
 * - (비회원)비밀번호 확인
 * - 데이터 체크
 * - 결제 (payment_info 추후 삽입)
 */
 
 /** 데이터 체크 */
 function checkCondition(inputId, message){
 	let input = document.getElementById(inputId);
 	let value = input.value;
 	
 	if(value == null || value == ""){
 		playToast(message, "warning");
 		input.focus();
 		$("#" + inputId).css("border-color", "red");
 		return null;
 	} else {
 		input.blur();
 		$("#" + inputId).css("border", "var(--bs-border-width) solid var(--bs-border-color)");
 	}
 	return value;
 }
 
/** 비밀번호 확인 */
function checkPwd(data){
	// this로 받아오는 경우 값을 가져오지 않음
	
	const cookiePwd = document.getElementById("cookiePwd");
	
	if(data.value != cookiePwd.value){
		playToast("비밀번호가 일치하지 않습니다", "error");
		$("#cookiePwdCheck").val("");
		$("#cookiePwdCheck").focus();
	}
}

// 결제 정보 (일반결제/ 카카오페이/ 토스페이/ 가상결제)
let pg = "";			// PG사
let payMethod = "";		// 결제방법
let sqlPayMethod = "";

/** 결제 방식 선택 */
$(() => {
	$("#html5_inicis").on("click", ()=>{
		pg = "html5_inicis";
		payMethod = "card";
		sqlPayMethod = "카드";
		console.log("일반결제 : " + pg + " / " + payMethod);
	});

	$("#kakaopay").on("click", ()=>{
		pg = "kakaopay";
		payMethod = "card";
		sqlPayMethod = "카카오페이";
		console.log("카카오페이 : " + pg + " / " + payMethod);
	});
	
	$("#tosspay").on("click", ()=>{
		pg = "tosspay";
		payMethod = "card";
		sqlPayMethod = "토스페이";
		console.log("토스페이 : " + pg + " / " + payMethod);
	});
	
	$("#vbank").on("click", ()=>{
		pg = "vbank";
		payMethod = "vbank";
		sqlPayMethod = "가상결제";
		console.log("가상결제 : " + pg + " / " + payMethod);
	});
});

/** 결제하기 버튼(정보 확인) */
function checkSubmit(){	
	if(pg == "" || payMethod == ""){
		playToast("결제 방식을 선택해주세요", "warning");
		return;
	}
	console.log("1. checkSubmit() 시작");
	let clientemail = checkCondition("clientEmail", "이메일은 필수 입력값입니다");
	let clientName = checkCondition("addressName", "받는 사람을 입력해주세요");
	let clientNumber = checkCondition("addressNum", "연락처를 입력해주세요");
	let postNum = checkCondition("addressPostNum", "우편번호를 입력해주세요");
	let address1 = checkCondition("address1", "기본 주소는 필수 입력값입니다.");
	let address2 = checkCondition("address2", "상세 주소를 입력해주세요");
	
	let addressMemo = $("#deliveryMessage").val();
	if(addressMemo == "inputMessage"){
		addressMemo = $("#deliMsg").val();
	}
	
	//회원
	let addressTitle = $("#addressTitle").val();
	let addressId = $("#addressId").val();
	let addressBase = $("#baseAddress").val();
	let clientId = $("#clientId").val();
	let couponId = $("#couponId").val().split("_")[2];
	let usedPoint = parseInt($("#usedPoint").val());
	
	//비회원
	let cookiePwd = $("#cookiePwd").val();
	
	let orderPrice = parseInt($("#orderPrice").val());					//주문금액
	let priceList = document.querySelectorAll(".productPriceInput");	//상품별 금액 
	let cntList = document.querySelectorAll(".cartCnt");				//상품별 수량
	let cartId = document.querySelectorAll(".cartId");					//장바구니 번호
	let optionList = document.querySelectorAll(".optionId");			//상품별 옵션
	let optionIdList = '';
	let cartIdList = [];
	let orderInfoList = [];
	
	//orders_info
	for(let i=0; i<priceList.length; i++){
	    let order = {
	        "optionId": optionList[i].value,
	        "orderProCnt": parseInt(cntList[i].value),
	        "orderTotal": parseInt(priceList[i].value)
	    };
	    orderInfoList.push(order);
	    cartIdList.push(parseInt(cartId[i].value));
	    optionIdList += optionList[i].value;
	    if(i<priceList.length-1 ) {
	    	optionIdList += ",";
	    }
	}
	
	let data = {};
	data["cartIdList"] = cartIdList;
	data["orderInfoList"] = orderInfoList;
	
	//배송지
	let addressInfo = {
		"addressId": addressId,
		"clientId": clientId,
		"addressTitle": addressTitle,
		"addressName": clientName,
		"addressNum": clientNumber,
		"addressPostNum": postNum,
		"address1": address1,
		"address2": address2,
		"addressMemo": addressMemo,
		"addressBase": addressBase
	};
	data["addressInfo"] = addressInfo;
	data["addressBase"] = addressBase;

	//주문정보
	let orderInfo = {
		"clientId": clientId,
		"optionIdList": optionIdList,
		"addressId": addressId,
		"orderEmail": clientemail,
		"orderPrice": orderPrice,
		"usedPoint": usedPoint,
		"couponId": couponId,
		"cookiePwd": cookiePwd,
	};
	data["orderInfo"] = orderInfo;
	data["orderPrice"] = orderPrice;
	
	// 회원	
	if(couponId != null){
		let usedCouponInfo = {
			"couponId": couponId.split("_")[2],
			"clientId": clientId
		};
		data["usedCouponInfo"] = usedCouponInfo;
	}
	
	$.ajax({
		url: "orderRegisterProc.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: JSON.stringify(data),
		contentType: "application/json",
		success: function(res){
			if(res.code == 1){
				let orderId = res.data;
				let IMP = window.IMP;
				IMP.init('imp21162314');
				
				IMP.request_pay({
					pg: pg,						// pg사 코드
					pay_method: payMethod,		// 결제 수단
					merchant_uid: 'merchant_'+new Date().getTime(),   // 주문번호(고유)
					name: optionIdList,			// 주문명
					amount: orderPrice,				// 결제 금액(숫자)
					buyer_email: clientemail,		// 이메일
					buyer_name: clientName,			// 구매자 이름
					buyer_tel: clientNumber,		// 구매자 연락처
					buyer_addr: address1 + address2,// 구매자 주소
					buyer_postcode: postNum,			// 구매자 우편번호
					card: {
						detail: [
							{card_cod: "*", enabled:true}
						]
					},
					display: {
					  card_quota: [1,2,3,4,6],  	// 할부개월 6개월만 활성화
					  only_installment: true  		// 일시불 항목은 제외
					}
				}, function (rsp) { // callback
					if(rsp.success){
						let paymentStatus;
						//결제검증
						$.ajax({
							type: "POST",
							url: "verifyIamport.do?imp_uid=" + rsp.imp_uid
						}).done(function(data){
							let result = $(data).find("amount").text();
							
							if(rsp.paid_amount == result){
								if(rsp.status == "ready"){
									paymentStatus = "결제대기";
								} else if (rsp.status == "paid"){
									paymentStatus = "결제완료";
								} else if (rsp.status == "fail"){
									paymentStatus = "결제실패";
								}

								let paymentInfo = {
									"orderId": orderId,
									"paymentId": rsp.merchant_uid,
									"paymentMethod": sqlPayMethod,
									"paymentDate": rsp.paid_at,
									"paymentStatus": paymentStatus
								}
								
								$.ajax({
									url: "paymentInsert.do",
									type: "POST",
									async: true,
									dataType: "json",
									data: JSON.stringify(paymentInfo),
									contentType: "application/json",
									success: function(res){
										if(res.code == 1){
											let successAction = "location.href='orderInfo.do?orderId=" + res.data + "'";
											playConfirm(res.message, "주문 상세페이지로 이동하시겠습니까?", "success", "이동하기", "메인페이지로", successAction, "location.href='main.do'");
										}
										
										if(res.code == -1){
											playToast(res.message, "error");
										}
									},
									error : function(error){
										playToast("오류가 발생했습니다.", 'error');
									}
								});
							}
						});
					}
					if(res.code == -1){
						playToast(res.message, "error");
					}
				});
			}
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

