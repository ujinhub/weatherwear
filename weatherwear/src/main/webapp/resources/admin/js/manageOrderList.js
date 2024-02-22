/**
 * 주문 상태/송장번호 수정
 */
 function change(type){
	let value = type.value;
	switch(value){
	case 'orderStatus':
		document.querySelector(".orderStatus_select").style.display='flex';
		document.querySelector(".deliveryNum_select").style.display='none';
		break;
	case 'deliverNum':
		document.querySelector(".deliveryNum_select").style.display='flex';
		document.querySelector(".orderStatus_select").style.display='none';
		break;
	default: return;
	}
}

function modify(){
	let checkList = [];
	let selectedList = document.querySelectorAll("input[type='checkbox']:checked");
	let modifyType = $("#modifyType").val();
	let changeValue;
	let orderId = selectedList[0].value.split("_")[0];

	if(modifyType == 'orderStatus'){
		changeValue = $("#orderStatus_value").val();
	} else if (modifyType == 'deliverNum'){
		changeValue = $("input[name='deliverNum_value']").val();
	}
	
	if(selectedList.length < 1){
		playToast("변경할 데이터를 선택해주세요", 'warning');
		return;
	}
	
	for(let i=0; i<selectedList.length; i++){ 
		if(!checkList.includes(selectedList[i].value)){
			let order = {};
			
			order.modifyType = modifyType;
			order.changeValue = changeValue;
			order.orderId = selectedList[i].value.split("_")[0];
			order.productId = selectedList[i].value.split("_")[1];
			order.optionName = selectedList[i].value.split("_")[2];
			checkList.push(order);
		}
	}
	
	if(checkList.some(order => order.orderId !== checkList[0].orderId)){
		playToast("같은 주문번호만 일시 환불 처리가 가능합니다.", "error");
		return; 
	}
			
	$.ajax({
		url: "/w2/orderUpdate.mdo?modifyType=" + modifyType,
		type: "POST",
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(){
			if(changeValue != "환불완료"){
				let swapId = $("#swapId_" + orderId).val();
				let swapStatus = "교환완료";
				
				updateStatus("swap", swapId, swapStatus);
			} else {
				let proPrice = 0;
				let orderPrice = parseInt($("#orderPrice_" + orderId).val());
				let usedPoint = parseInt($("#usedPoint_" + orderId).val());
				let couponPrice = parseInt($("#couponPrice_" + orderId).val());
				let refundPrice = 0;
				let refundId = $("#refundId_" + orderId).val();
				let refundCost = $("#refundCost_" + refundId).val();
				let refundCostMtd = $("#refundCostMtd_" + refundId).val();
				
				checkList.forEach(order => {
					proPrice = parseInt($("#proPrice_" + orderId).val());
					refundPrice += proPrice;
				});
				
				let percentage = Math.floor(refundPrice/orderPrice*100)/100;
				console.log("percentage : " + percentage);
				let checkPrice = refundPrice - Math.floor((usedPoint + couponPrice)* percentage/100)*100;
				console.log("checkPrice : " + checkPrice);
				
				
				let refundInput = "<div class='row' style='border-bottom:1px solid silver; margin-bottom:10px;'></div>";
				refundInput += "<div class='confirmDiv'><div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>전체 주문 금액 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + orderPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";						
				refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>환불 상품 금액 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + refundPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";
				refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>사용 포인트 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:lex-start; padding-left: 20px;'><b>" + usedPoint.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";
				refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>쿠폰 적용 금액 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + couponPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div><hr>";
				refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>환불 금액 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + checkPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";
				if(refundCost > 0){
					refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>배송비 지불 방식 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + refundCostMtd +"</b></div></div>";
					refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>- 배송비 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + refundCost +"</b></div></div>";
					refundInput += "<div class='refundDiv'><div class='row'><div class='refundDiv_sub' style='width:60%; display:flex; justify-content:flex-end;'>배송비 제외 금액 : </div><div class='refundDiv_sub' style='width:40%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + (checkPrice-refundCost).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";
					refundInput += "</div><hr><div class='refundDiv'><div class='refundDiv_sub'>환불 금액 입력하기 : <input type='text' id='checkPrice' placeholder='" + (checkPrice-refundCost).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "'></div></div>";
				} else {
					refundInput += "</div><hr><div class='refundDiv'><div class='refundDiv_sub'>환불 금액 입력하기 : <input type='text' id='checkPrice' placeholder='" + checkPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "'></div></div>";
				}
				let paymentId = $("#paymentId_"+orderId).val();
				
				Swal.fire({
				  title: "환불금액",
				  html: refundInput,
				  icon: "success",
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: "환불요청하기",
				  cancelButtonText: "취소하기",
				  reverseButtons: true, // 버튼 순서 거꾸로
				}).then((result) => {
					if(result.isConfirmed){	// 매개변수 list 안됨
						let price = $("#checkPrice").val();
						console.log("환불하기 : " + price);
						$.ajax({
							url: "/w2/refundPriceProc.do",
							type: "POST",
							data: {
								refundPrice: price,
								totalPrice: orderPrice,
								paymentId: paymentId
							},
							dataType: "json",
							success: function(res){
								if(res.code == 1) {
									updateStatus("refund", refundId, "환불완료");
								} else {
									playToast("환불 중 오류가 발생했습니다.", "error");
									return;
								}
							},
							error: function(){
								console.log("환불 요청 실패");
							}
						});
					} else {
						console.log("환불 취소");
					}
				});
				
			}
		},
		error: function(){
			console.log("실패");
		}
	});
}

function updateStatus(requestWhat, id, status){
	$.ajax({
		url: "/w2/updateSwapRefundStatus.mdo",
		type: "POST",
		data: {
			requestWhat: requestWhat,
			id: id,
			status: status
		},
		dataType: "json",
		success: function(res){
			if(res.code > 0){
				playToast(res.message, "success");
				setTimeout(function(){
					window.location.reload();
				}, 2000);
			} else {
				switch(requestWhat){
					case "refund" : updateStatus("refund", id, "환불진행"); break;
					case "swap" : updateStatus("swap", id, "교환진행"); break;
				}
				playAlert(res.message, "error", "오류 정보 : " + res.data, "orderList.mdo");
			}
		},
		error: function(){
			console.log("요청 실패");
			if(requestWhat == "refund"){
				playToast("환불 정보 업데이트에 실패했습니다.", "error");
			} else if (requestWhat == "swap") {
				playToast("교환 정보 업데이트에 실패했습니다.", "error");
			}
			return;
		}
	});
}

function checkInfo(orderId, refundId, swapId){
	let word;
	let tagName;
	let clientId = $("#clientId_" + orderId).val();
	let clientNum = $("#clientNum_" + orderId).val();
	
	if(refundId == '' && swapId == ''){
		console.log("교환/환불 없음");
		return;
	} 
	
	if(refundId == '' || swapId != ''){
		console.log("교환 요청");
		word = "교환";
		tagName = "#swap";
	} 
	
	if(swapId == '' || refundId != ''){
		console.log("환불 요청");
		word = "환불";
		tagName = "#refund";
	}
	
	let id = $(tagName + "Id_" + orderId).val();
	let	bankId = $(tagName + "BankId_" + id).val();
	let	bankNum = $(tagName + "BankNum_" + id).val();
	let optionId = $("#optionId_" + orderId).val();
	let reason = $(tagName + "Reason_" + id).val();
	let keyword = $(tagName + "Keyword_" + id).val();
	let way = $(tagName + "Way_" + id).val();
	let cost = $(tagName + "Cost_" + id).val();
	let costMtd = $(tagName + "CostMtd_" + id).val();
	let status = $(tagName + "Status_" + id).val();
	let regDate = $(tagName + "regDate_" + id).val();
	let email = $(tagName + "Email_" + id).val();
	
					
	let InfoInput = "<div class='row' style='border-bottom:1px solid silver; margin-bottom:10px;'></div>";
	InfoInput += "<div class='confirmDiv'><div class='InfoDiv'><div class='row'><div class='InfoDiv_sub'><h4>" + word + " 요청</h4></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>주문 번호 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + orderId +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>옵션 번호 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + optionId +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>카테고리 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + keyword +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>" + word + " 사유 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + reason +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>수거 방법 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + way +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>추가 비용 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>" + word + " 상태 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + status +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>신청일자 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + regDate +"</b></div></div><hr>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub'><h4> 기본 정보</h4></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>회원 아이디 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + clientId +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>연락처 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + clientNum +"</b></div></div>";
	InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>이메일 : </div>";
	InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + email +"</b></div></div>";
	InfoInput += "<input type='hidden' value='" + id + "' id='id'>";
	
	if(word == "환불"){
		InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>" + word + " 은행 : </div>";
		InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + bankId +"</b></div></div>";
		InfoInput += "<div class='InfoDiv'><div class='row'><div class='InfoDiv_sub' style='width:30%; display:flex; justify-content:flex-end;'>" + word + " 은행 계좌 : </div>";
		InfoInput += "<div class='InfoDiv_sub' style='width:70%; display:flex; justify-content:flex-start; padding-left: 20px;'><b>" + bankNum +"</b></div></div>";
	}
	
	InfoInput += "</div>";

	Swal.fire({
	  title: word + "정보",
	  html: InfoInput,
	  icon: "success",
	  showCancelButton: false,
	  confirmButtonColor: '#3085d6',
	  confirmButtonText: "닫기",
	}).then((result) => {
		if(result.isConfirmed){	// 매개변수 list 안됨
			return;	
		}
	});
	
}