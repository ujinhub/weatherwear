/**
 * - 화면초기화
 * - 주문상품 가격
 * - 쿠폰리스트 
 * - 배송비 적용
 * - 포인트 적용(전체/부분) 
 * - 기본배송지
 * - 신규배송지
 * - 배송지 설정
 * - 배송지 삭제
 * - 배송지 목록 불러오기
 */
$(document).ready(function(){
/** 화면 초기화 */
	$("#totalDiscountPrice").html(0);
	$("#discountPrice").html(0);
	
	// 보유 포인트
	let havingPoint = parseInt($("#clientPoint").val());
	
	let usedPoint = 0;			//사용포인트
	let couponPrice = 0;		//쿠폰적용값
	let oriPrice = 0;			//기존금액
	let deliveryPrice = 3000;	//배송비
	let discountPrice = 0;		//할인가격(포인트+쿠폰값)
	let orderPrice = 0;			//주문금액(결제금액)
	
/** 주문상품 가격 */
	let orderProductPriceList = document.querySelectorAll(".productPriceInput");
	orderProductPriceList.forEach(price => {
		oriPrice += parseInt(price.value);
	});
	$("#totalOrderPrice").html(oriPrice);

/** 쿠폰리스트 */
	let couponList = document.querySelectorAll(".couponOption");
	let couponOption = document.getElementById("couponId");
	couponList.forEach(coupon => {
		if(coupon.value.split("_")[1] > oriPrice){	//최소금액이 결제금액보다 큰 경우
			coupon.disabled = true;
			couponOption.appendChild(coupon);	//아래로 정렬
		}
	});

/** 배송비 적용 */
	if(oriPrice > 50000){	// 50000원 이상인 경우
		deliveryPrice = 0;
		$("#deliveryPriceDiv").html("0 (무료)");
	} else {
		$("#deliveryPriceDiv").html(deliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	$("#totalDeliveryPrice").html(deliveryPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$("input[name='deliveryPrice']").val(deliveryPrice);

/** 포인트 적용 */
	// 전체적용(회원 주문인 경우)
	const applyAllPointBtn = document.querySelector("#applyAllPoint");
	if(applyAllPointBtn){
		applyAllPointBtn.addEventListener("click", function(){
			$("input[name='usedPoint']").val(havingPoint);
			discountPrice = havingPoint + couponPrice;	//할인금액
			setDiscount();
		})
	
		//부분 할인 적용
		$("#usedPoint, #couponId").change(function(){
			usedPoint = parseInt($("input[name='usedPoint']").val());
			couponPrice = parseInt($("#couponId").val());
	
			if(havingPoint < usedPoint){	// 포인트 적용
				playToast("보유한 포인트를 초과할 수 없습니다.", "warning");
				$("input[name='usedPoint']").val("");
				$("input[name='usedPoint']").focus();
				usedPoint = 0;
			}
			
			if(usedPoint == null || usedPoint == ''){	//포인트 초기화
				usedPoint = 0;
			}
			if(couponPrice == null || couponPrice == ''){	//쿠폰 초기화
				couponPrice = 0;
			}
			discountPrice = usedPoint + couponPrice;
			setDiscount();
		});
		
		//할인금액 화면 출력
		function setDiscount(){
			if(discountPrice > oriPrice){	// 할인금액이 결제 금액보다 큰 경우
				discountPrice = oriPrice;	// 최소 결제 금액
			}
			$("#discountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
			$("#totalDiscountPrice").html(discountPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
			orderPrice = oriPrice + deliveryPrice - discountPrice;
			if(orderPrice < 0) {// 주문 금액이 0원 이하인 경우
			 orderPrice = 0;
			}
			$("#totalPayPrice").html(orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
			$("#orderPrice").val(orderPrice);
		}
	
/** 기본 배송지 등록 */
		document.getElementById("baseAddress").addEventListener("change", function() {
			if (this.checked) {	// 체크박스가 체크된 경우 "Y" 반환 (기본배송지)
				$("#baseAddress").val("Y");
			} else {
				$("#baseAddress").val("N");
			}
		});
	}

/** 최종 결제 금액 */
	orderPrice = oriPrice + deliveryPrice - discountPrice;
	$("#totalPayPrice").html(orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$("#orderPrice").val(orderPrice);

	$("#sameInfo").click(function(){
		$("#addressName").val($("#clientName").val());
		$("#addressNum").val($("#clientNum").val());
	});

/** 새로운 배송지 초기화 */
	$("#newInfo").click(function(){
		$("#addressTitle").val("");
		$("#addressName").val("");
		$("#addressPostNum").val("");
		$("#address1").val("");
		$("#address2").val("");
		$("#addressNum").val("");
		$("#deliveryMessage").val("");
		$("#addressId").val("");
		$("#baseAddress").val("N");
	});
});

/** 배송지 설정 */
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

/** 배송지 삭제 */
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

/** 배송지 목록 불러오기 */
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