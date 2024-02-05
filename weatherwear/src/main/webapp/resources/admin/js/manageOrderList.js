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
			checkList.push(order);
		}
	}
	
	$.ajax({
		url: "/w2/orderUpdate.mdo?modifyType=" + modifyType,
		type: "POST",
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(){
			playToast("수정되었습니다", 'success');
			window.location.reload();
		},
		error: function(){
			console.log("실패");
		}
	});
}