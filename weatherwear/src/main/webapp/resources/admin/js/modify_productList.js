/**
 * 상품 상태 수정
 */

function modify(){
	let checkList = [];
	let selectedList = document.querySelectorAll("input[type='checkbox']:checked");
	let productSell = $("#productSell_value").val();
	
	if(selectedList.length < 1){
		alert("변경할 데이터를 선택해주세요");
		return;
	}
	
	for(let i=0; i<selectedList.length; i++){ 
		if(!checkList.includes(selectedList[i].value)){
			let product = {};
			
			product.productSell = productSell;
			product.productId = selectedList[i].value;
			checkList.push(product);
		}
	}
	
	$.ajax({
		url: "/w2/modifyProductStatus.mdo",
		type: "POST",
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(){
			alert("수정되었습니다.");
			window.location.reload();
		},
		error: function(){
			console.log("실패");
		}
	});
}