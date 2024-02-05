$(document).ready(function(){
	let havingPoint = parseInt($("input[name='clientPoint']").val());
	$("#totalDiscountPrice").html(0);
	$("#discountPrice").html(0);
	let usedPoint = 0;
	let couponPrice = 0;
	
	let oriPrice = 0;
	let deliveryPrice = 3000;
	let discountPrice = 0;
	let orderPrice = 0;
	
// 주문상품 가격
	let orderProductPriceList = document.querySelectorAll(".productPriceInput");
	orderProductPriceList.forEach(price => {
		oriPrice += parseInt(price.value);
	});
	$("#totalOrderPrice").html(oriPrice);

	let couponList = document.querySelectorAll(".couponOption");
	let couponOption = document.getElementById("couponId");
	couponList.forEach(coupon => {
		if(coupon.value.split("_")[1] > oriPrice){
			coupon.disabled = true;
			couponOption.appendChild(coupon);
		}
	});

// 배송비
	if(oriPrice > 50000){
		deliveryPrice = 0;
		$("#deliveryPrice").html("0 (무료)");
	} else {
		$("#deliveryPrice").html(deliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	$("#totalDeliveryPrice").html(deliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$("input[name='deliveryPrice']").val(deliveryPrice);

// 포인트 적용
	// 전체적용
	const applyAllPointBtn = document.querySelector("#applyAllPoint");
	applyAllPointBtn.addEventListener("click", function(){
		$("input[name='usedPoint']").val(havingPoint);
		
		discountPrice = havingPoint + couponPrice;
		if(discountPrice > oriPrice){
			discountPrice = oriPrice;
		}
		$("#discountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		$("#totalDiscountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		
		orderPrice = oriPrice + deliveryPrice - discountPrice;
		if(orderPrice < 0) {
		 orderPrice = 0;
		}
		$("#totalPayPrice").html(orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		$("input[name='orderPrice']").val(orderPrice);
	})
	
	//부분 할인 적용
	$("#usedPoint, #couponId").change(function(){
		usedPoint = parseInt($("input[name='usedPoint']").val());
		couponPrice = parseInt($("#couponId").val());

		if(havingPoint < usedPoint){
			playToast("보유한 포인트를 초과할 수 없습니다.", "warning");
			$("input[name='usedPoint']").val("");
			$("input[name='usedPoint']").focus();
			usedPoint = 0;
		}
		
		if(usedPoint == null || usedPoint == ''){
			usedPoint = 0;
		}
		if(couponPrice == null || couponPrice == ''){
			couponPrice = 0;
		}
		discountPrice = usedPoint + couponPrice;
		if(discountPrice > oriPrice){
			discountPrice = oriPrice;
		}
		$("#discountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		$("#totalDiscountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		
		orderPrice = oriPrice + deliveryPrice - discountPrice;
		if(orderPrice < 0) {
		 orderPrice = 0;
		}
		$("#totalPayPrice").html(orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		$("input[name='orderPrice']").val(orderPrice);
	})
	
// 최종 결제 금액
	orderPrice = oriPrice + deliveryPrice - discountPrice;
	$("#totalPayPrice").html(orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$("input[name='orderPrice']").val(orderPrice);

	$("#sameInfo").click(function(){
		$("#addressName").val($("#clientName").val());
		$("#addressNum").val($("#clientNum").val());
	});

	$("#newInfo").click(function(){
		$("#addressTitle").val("");
		$("#addressName").val("");
		$("#addressPostNum").val("");
		$("#address1").val("");
		$("#address2").val("");
		$("#addressNum").val("");
		$("#deliveryMessage").val("");
		$("#addressId").val("");
	});
	
// 기본 배송지 등록
	document.getElementById("baseAddress").addEventListener("change", function() {
		// 체크박스가 체크된 경우 "Y" 반환
		if (this.checked) {
			$("#baseAddress").val("Y");
		} else {
			$("#baseAddress").val("N");
		}
	});
});

// 배송지 설정
function selectAddress(button){
	let addressId = button.id;
	
	$.ajax({
		url: "getAddressInfo.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: {
			addressId: addressId,
		},
		success: function(res){
			if(res.code == 1){
				$("#addressTitle").val(res.data.addressTitle);
				$("#addressName").val(res.data.addressName);
				$("#addPostNum").val(res.data.addressPostNum);
				$("#address1").val(res.data.address1);
				$("#address2").val(res.data.address2);
				$("#addressNum").val(res.data.addressNum);
				$("#addressId").val(res.data.addressId);
				
				if(res.data.addressMemo){
					$("#deliveryMessage").val("inputmessage");
					$("div#deliDiv").html("<input type='text' class='form-control' name='deliMsg' id='deliMsg' required value='" + res.data.addressMemo + "'>");
				} else {
					$("#deliveryMessage").val("");
					$("div#deliDiv").html("");
				}
				
				playToast("적용되었습니다.", "success");
			} else {
				playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
			}
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

function deleteAddress(button){
	let addressId = button.id;
	
	Swal.fire({
		title: "배송지를 삭제하시겠습니까?",
		icon: "question",
	  	showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: "삭제하기",
	  	cancelButtonText: "취소하기",
		reverseButtons: true, // 버튼 순서 거꾸로
	}).then((result) => {
		if(result.isConfirmed){	// 매개변수 list 안됨
			$.ajax({
				url: "deleteAddress.do",
				type: "POST",
				async: true,
				dataType: "json",
				data: {
					addressId: addressId,
				},
				success: function(res){
					if(res.code == 1){
						playToast(res.message, "success");
						getAddressList();
					} else {
						playToast("오류가 발생했습니다. 다시 시도해주세요.", "error");
						getAddressList();
					}
				},
				error : function(error){
					playToast("오류가 발생했습니다.", 'error');
				}
			});
		}
		getAddressList();
	});
}
	
function getAddressList(){
	let addressListContent = "<div class='row' style='border-bottom:1px solid silver; margin-bottom:10px;'></div>";
	let clientId = $("#clientId").val();
	
	$.ajax({
		url: "getAddressList.do",
		type: "POST",
		async: true,
		dataType: "json",
		data: {
			clientId: clientId,
		},
		success: function(res){
			if(res.code == 1){
				for(let i=0; i<res.data.length; i++){
					addressListContent += "<div class='confirmDiv'><div class='deliDiv' id='" + res.data[i].addressId;
					addressListContent += "'><div class='deliDiv_sub'><b>";
					addressListContent += res.data[i].addressTitle;
					if(res.data[i].addressBase == 'Y' || res.data[i].addressBase == 'y'){
						addressListContent += "&nbsp;<code>[ 기본 배송지 ]</code>";
					}
					addressListContent += "</b></div><div class='deliDiv'><input type='button' class='form-control applyAddrBtn' value='선택' onclick='selectAddress(this)' id='" + res.data[i].addressId + "'>&nbsp;";
					addressListContent += "<input type='button' class='form-control deleteAddrBtn' value='삭제' onclick='deleteAddress(this)' id='" + res.data[i].addressId + "'></div></div><div class='deliDiv'><div class='deliDiv_sub'>";
					addressListContent += res.data[i].addressName;
					addressListContent += "</div><div class='deliDiv_sub'>";
					addressListContent += res.data[i].addressNum.slice(0,3) + "-" + res.data[i].addressNum.slice(3,7) + "-" + res.data[i].addressNum.slice(7);
					addressListContent += "</div></div><div class='deliDiv'>";
					addressListContent += res.data[i].addressPostNum;
					addressListContent += "</div><div class='deliDiv'>";
					addressListContent += res.data[i].address1;
					addressListContent += "</div><div class='deliDiv'>";
					addressListContent += res.data[i].address2 ;
					addressListContent += "</div></div>";
				}
				addressListContent += "</div>";
			}
			
			Swal.fire({
				title: "\n배송지 목록",
				html: addressListContent,
				icon: null,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: "취소",
				reverseButtons: true, // 버튼 순서 거꾸로
			}).then((result) => {
				if(result.isConfirmed){	// 매개변수 list 안됨
					return;
				}
			});
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}
	
// 배송메세지 직접 입력란 생성
function setMessage(){
	if(document.querySelector("#deliveryMessage").value == "inputmessage"){
		$("div#deliDiv").html("<input type='text' class='form-control' name='deliMsg' id='deliMsg' required placeholder='배송 메세지 직접 입력'>");
		$("#deliMsg").focus();
	} else {
		$("div#deliDiv").html("");
	}
}

// 비밀번호 확인
function checkPwd(data){
	// this로 받아오는 경우 값을 가져오지 않음
	
	const cookiePwd = document.getElementById("cookiePwd");
	
	if(data.value != cookiePwd.value){
		playToast("비밀번호가 일치하지 않습니다", "error");
		$("#cookiePwdCheck").val("");
		$("#cookiePwdCheck").focus();
	}
}

function submit(){
	let addressTitle = $("#addressTitle").val();
	let clientemail = $("#clientEmail").val();
	let clientName = $("#addressName").val();
	let clientNumber = $("#addressNum").val();
	let postNum = $("#addressPostNum").val();
	let addressId = $("#addressId").val();
	let address1 = $("#address1").val();
	let address2 = $("#address2").val();
	let addressMemo = $("#deliveryMessage").val();
	console.log("@@@ : " + addressMemo);
	if(addressMemo == "inputMessage"){
		addressMemo = $("#deliMsg").val();
		console.log("@@@@@@ : " + addressMemo);
	}
	let addressBase = $("#baseAddress").val();
	
	let orderPrice = parseInt($("#orderPrice").val());
	let clientId = $("#clientId").val();
	let couponId = $("#couponId").val().split("_")[2];
	let usedPoint = parseInt($("#usedPoint").val());
	let cookiePwd = $("#cookiePwd").val();
	
	let priceList = document.querySelectorAll(".productPriceInput");
	let cntList = document.querySelectorAll(".cartCnt");
	let cartId = document.querySelectorAll(".cartId");
	let optionList = document.querySelectorAll(".optionId");
	let optionIdList = '';
	let cartIdList = [];
	let orderInfoList = [];
	
	for(let i=0; i<priceList.length; i++){
	    let order = {
	        optionId: optionList[i].value,
	        orderProCnt: parseInt(cntList[i].value),
	        orderTotal: parseInt(priceList[i].value)
	    };
	    orderInfoList.push(order);
	    cartIdList.push(parseInt(cartId[i].value));
	    optionIdList += optionList[i].value;
	    if(i<priceList.length-1 ) {
	    	optionIdList += ",";
	    }
	}

	let data = {};
	
	let addressInfo = {
		addressBase: addressBase,
		addressTitle: addressTitle,
		addressId: addressId,
		clientId: clientId,
		addressName: clientName,
		addressNum: clientNumber,
		addressPostNum: postNum,
		address1: address1,
		address2: address2,
		addressMemo: addressMemo
	};

	let orderInfo = {
		clientId: clientId,
		optionIdList: optionIdList,
		addressId: addressId,
		orderEmail: clientemail,
		orderPrice: orderPrice,
		usedPoint: usedPoint,
		couponId: couponId,
		cookiePwd: cookiePwd,
	};
		
	if(couponId != null){
		let usedCouponInfo = {
			couponId: couponId,
			clientId: clientId
		};
		data["usedCouponInfo"] = usedCouponInfo;
	}
	
	data["addressInfo"] = addressInfo;
	data["addressBase"] = addressBase;
	data["cartIdList"] = cartIdList;
	data["orderInfo"] = orderInfo;
	data["orderInfoList"] = orderInfoList;

	let IMP = window.IMP;
	IMP.init('imp21162314');
	
	IMP.request_pay({
		pg: "html5_inicis",				// pg사 코드
		pay_method: "card",				// 결제 수단
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
		}
	}, function (rsp) { // callback
		if(rsp.success){
			let paymentStatus;
			
			if(rsp.status == "ready"){
				paymentStatus = "결제대기";
			} else if (rsp.status == "paid"){
				paymentStatus = "결제완료";
			} else if (rsp.status == "fail"){
				paymentStatus = "결제실패";
			}
		
			let paymentInfo = {
				paymentId: rsp.imp_uid,
				paymentMethod: "카드",
				paymentDate: rsp.paid_at,
				paymentStatus: paymentStatus
			}
			data["paymentInfo"] = paymentInfo;
/*
	고유ID : rsp.imp_uid
	상점 거래Id : rsp.merchant_uid
	결제금액 : rsp.paid_amount
	카드 승인번호 : rsp.apply_num
*/
			$.ajax({
				url: "orderRegisterProc.do",
				type: "POST",
				async: true,
				dataType: "json",
				data: JSON.stringify(data),
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
		} else {
			alert("결제 실패");
		}
	});
	
}
