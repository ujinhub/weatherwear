/**
 * 상품 옵션/재고 적용
 */
 $(document).ready(function(){
 	// summernote 실행
	$('#summernote').summernote({
		height: 300
	});
 
	const optionDiv = document.querySelector("#option-div");
	
	let optionColorList = applyOption('optionColor');
	let optionSizeList = applyOption('optionSize');
		
	// 옵션 적용
	$("#apply-option").click(function(){
	
		optionColorList = applyOption('optionColor');
		optionSizeList = applyOption('optionSize');
		if(optionColorList == "" || optionSizeList == ""){
			return;
		}
		
		optionDiv.style.display = 'flex';
		$(".option-stock").html("");	
		
		for(var i=0; i<optionColorList.length; i++){
			for(var j=0; j<optionSizeList.length; j++){
				$(".option-stock").append("<tr><td>" + optionColorList[i] +"</td><td>" + optionSizeList[j] + "</td><td>"
							 + "<input type='number' class='custom-select form-control-border' name='stCnt' id='stCnt" 
							 + optionColorList[i] + optionSizeList[j] + "' value='0'></td></tr>");
			}
		}
	});
	
	// 재고 일괄 적용
	$("#applyStockAll").click(function(){
		var stCntList = [];
		var cnt = $("input[name='allStock']").val();
		
		for(var i=0; i<optionColorList.length; i++){
			for(var j=0; j<optionSizeList.length; j++){
				
				stCntList.push(cnt);
				$("#stCnt" + optionColorList[i] + optionSizeList[j]).val(cnt);
			}
		}
		setStock(stCntList);
	});
});

// 재고 적용
function setStock(stCntList){
	$("input[name='stockList']").val(stCntList);
}

// 옵션 적용
function applyOption(name){
	var optionList = $("input[name='" + name + "']").val().replace(/\s/g, '');
	var list = [];

	if(optionList){
		optionList.split(",").forEach(function(option){
			if(list.includes(option)){
				alert("동일한 값을 적용할 수 없습니다.");
				$("input[name='" + name + "']").val("");
				document.querySelector("input[name='" + name + "']").focus();
				list = [];
				return null;
			}
			list.push(option);
		});
	}
	
	$("input[name='" + name + "List']").val(list);
	
	return list;
}

// 재고 적용
function applyStock(optionColorList, optionSizeList){
	var stCntList = [];
	
	for(var i=0; i<optionColorList.length; i++){
		for(var j=0; j<optionSizeList.length; j++){
			console.log("cnt : " + $("#stCnt" + optionColorList[i] + optionSizeList[j]).val());
			stCntList.push($("#stCnt" + optionColorList[i] + optionSizeList[j]).val());
		}
	}
	setStock(stCntList);
}

// 판매가 확인
function checkCost(){
	let price = parseInt($("input[name='productPrimeCost']").val());
	let cost = Math.ceil((price + (price*0.1)*2.5)/100)*100;
	document.querySelector("#cost").innerHTML = cost;
}

// 적용
function submit(type){
	let colorList = applyOption('optionColor');
	let sizeList = applyOption('optionSize');
	let url_type = '';
	let productId = 'W2';

	applyStock(colorList, sizeList);
	
	// 카테고리
	let productCate = document.querySelector("#exampleSelectBorder").value;
	let productName = $("input[name='productName']").val();
	let productContent = $('#summernote').summernote('code');
	let productPrimeCost = $("input[name='productPrimeCost']").val();
	let stockCntList = $("input[name='stockList']").val();
	let optionColorList = $("input[name='optionColorList']").val();
	let optionSizeList = $("input[name='optionSizeList']").val();
	
	if(type == 'register'){
		url_type = 'productRegisterProc.mdo';
	
		switch(productCate){
		case "11":
			productId += 'OT'; break;
		case "12":
			productId += 'TS'; break;
		case "13":
			productId += 'PT'; break;
		case "14":
			productId += 'SK'; break;
		case "15":
			productId += 'DR'; break;
		default :
			productId += 'AL'; break;
		}
	} else if(type == 'modify'){
		url_type = 'productUpdateProc.mdo';
		productId = $("input[name='productId']").val();
	}
	
	$.ajax({
		type: "post",
		url: url_type,
		dataType: "json",
		data: {
			productId: productId,
			productCate: productCate,
			productName: productName,
			productContent: productContent,
			productPrimeCost: productPrimeCost,
			colorList: optionColorList,
			sizeList: optionSizeList,
			cntList: stockCntList,
		},
		success: function(res) {
			if(res.code == -1) {
				alert(res.message);
				return;
			} 
			
			if(res.code == 1) {
				if(confirm(res.message)){
					if(type == 'register'){
						location.href = 'productInfo.mdo?productId=' + res.data.productId;
					} else {
						location.href = 'productInfo.mdo?productId=' + productId;
					}
				} else {
					location.href = 'productList.mdo';
				}
			}
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	})
}

// 상품 삭제
function deleteProduct(){
	if(confirm("상품을 삭제하시겠습니까?")){
		const params = new URLSearchParams(location.search);
		let productId = params.get('productId');

		$.ajax({
			type: "post",
			url: "productDelete.mdo",
			dataType: "json",
			data: {
				productId: productId,
			},
			success: function(res) {
				if(res.code == -1) {
					alert(res.message);
					return;
				} 
				
				if(res.code == 1) {
					alert(res.message);
					location.href="productList.mdo";
				}
			},
			error: function(request, status, error) {
				console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}
		})
	}
}

// 상품 상태 변경
function update(){
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
		url: "/w2/productUpdateStatus.mdo",
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