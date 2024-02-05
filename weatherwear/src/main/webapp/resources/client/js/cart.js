// 가격 지정
$(document).ready(function(){
	setPrice();
});

// 위시리스트로 이동
function moveWishList(){
	// 선택된 상품 없는 경우
	if(deleteList.length == 0){
		playToast("위시리스트에 추가할 상품을 선택해주세요", 'warning');
		return;
	}
	let productList = [];
	deleteList.forEach(check => {
		productList.push($("input[id='cart_" + check + "']").val());
		console.log($("input[id='cart_" + check + "']").val());
	});
	
	insertWishList(productList);
	deleteSelect('addWishList');
}

// 선택 상품 주문
function orderSelect(){
	// 선택된 상품 없는 경우
	if(deleteList.length == 0){
		playToast("주문할 상품을 선택해주세요", 'warning');
		return;
	}
	location.href="orderRegister.do?cartList=" + deleteList;
}

// 전체 상품 주문
function orderAll(){
	location.href="orderRegister.do?cartList=" + checkList;
}

// ajax_상품 삭제
function delProduct(checkList, type){
	$.ajax({
		url: "/w2/cartDelete.do",
		type: "POST",
		async: true,
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(res){
			if(type == 'delete')
				playToast(res.message, 'success');
			return;
		},
		error : function(error){
			playToast("ajax는 실패입니다.", 'error');
		}
	});
}

// 선택 상품 삭제
function deleteSelect(type){
	// 선택된 상품 없는 경우
	if(deleteList.length == 0){
		playToast("삭제할 상품을 선택해주세요", 'warning');
		return;
	}
	
	let cartIdDiv;
	deleteList.forEach(check => {
		cartIdDiv = document.querySelector("#div_"+check);
		cartIdDiv.closest(".cart_content.checklist").remove();
	});
	delProduct(checkList, type);
	checkList.splice(0, checkList.length);
	deleteList.splice(0, deleteList.length);
	
	checkCounts = document.querySelectorAll(".check");
	setPrice();
}

// 전체 상품 삭제
function deleteAll(){
	checkCounts = document.querySelectorAll(".check");
	let cartIdDiv;
	
	// 선택된 상품 없는 경우
	checkNone(deleteList);
	
	checkCounts.forEach(check => {
		cartIdDiv = document.querySelector("#div_"+check.value);
		cartIdDiv.closest(".cart_content.checklist").remove();
	});
	delProduct(deleteList);
	checkList.splice(0, checkList.length);
	deleteList.splice(0, deleteList.length);
	
	checkCounts = document.querySelectorAll(".check");
	setPrice();
}

// 체크박스 적용
 $(document).ready(function(){
	// 전체선택
	$("#checkAll").click(function(){
		checkCounts = document.querySelectorAll(".check");
		
		if($(this).hasClass("checkAll")){	// 전체선택
			$(this).removeClass("checkAll").addClass("uncheckAll").addClass("active");
			$(this).text("전체해제");
			$(".check").prop("checked", true);
			
			checkCounts.forEach((check, index) => {
				pushList(checkList, check.value);
				pushList(deleteList, check.value);
			});
		} else if($(this).hasClass("uncheckAll")){	// 전체해제
			$(this).removeClass("active").removeClass("uncheckAll").addClass("checkAll");
			$(this).text("전체선택");
			$(".check").prop("checked", false);
			
			checkCounts.forEach(check => {
				spliceList(checkList, check.value);
				spliceList(deleteList, check.value);
			});
		}
		setPrice();
	});
	
	
	$(".check").click(function(){
		
		if(this.checked){
			// 첫 화면시 모두 추가되어 있는 상태
			if(checkList.length == $(".checklist .check").length){
				checkList.splice(0, checkList.length);
			}
			
			pushList(checkList, this.value);
			pushList(deleteList, this.value);
		} else {
			spliceList(checkList, this.value);
			spliceList(deleteList, this.value);
		}
		
		if($(".check:checked").length < $(".checklist .check").length){
			$("#checkAll").prop("checked", false);
		} else {
			$("#checkAll").prop("checked", true);
		}
		setPrice();
	});
});

// checkList/deleteList 에 추가
function pushList(list, Value){
	const index = list.indexOf(Value);
	
	// 인덱스에 없는 경우
	if(index == -1){
		list.push(Value);
	}
}

// checkList/deleteList 에서 제거
function spliceList(list, Value) {
	const index = list.indexOf(Value);

	// 인덱스에 있는 경우
	if(index !== -1){
		list.splice(index, 1);
	}
}

// list 상태 확인
function checkNone(list){
	if(list == ''){
		// 상품별 count li 태그 찾기
		const cartCounts = document.querySelectorAll(".cart_con.count");
		cartCounts.forEach(cartId => {
			list.push((cartId.querySelector(".cartValue").id).split("_")[1]);
		});
	}
}

// 값 설정
function setPrice(){
	let totalPriceVal = 0;
	let deliPriceVal = 3000;

	checkNone(checkList);
	
	// 상품별
	checkList.forEach(check => {
		const cartId = check;

		// 수량
		const countInput = document.querySelector("input[name='" + cartId + "']");
		
		// 기본 가격
		const oriPriceInput = document.querySelector("input[name='oriPrice_" + cartId + "']");
		
		const priceDiv = document.querySelector("#price_" + cartId);
		const pro_totalPriceSpan = priceDiv.querySelector("#totalPrice_" + cartId);
		const pro_totalPriceInput = priceDiv.querySelector("input");
		
		// (기본가격 * 수량)
		let pro_totalPrice = countInput.value * oriPriceInput.value
		
		pro_totalPriceSpan.innerHTML = setFormat(pro_totalPrice);
		pro_totalPriceInput.value = pro_totalPrice;

		// 총 상품 금액
		totalPriceVal += pro_totalPrice;				
	});
	
	// 배송비 조건부 무료
	if(totalPriceVal > 50000 || totalPriceVal == 0){
		deliPriceVal = 0;
	}

	// input
	const totalPriceInput = document.querySelector("input[name='totalPrice']");
	const deliPriceInput = document.querySelector("input[name='deliPrice']");
	const endPriceInput = document.querySelector("input[name='endPrice']");
	
	//span
	const totalPriceSpan = document.querySelector("#totalPrice");
	const deliPriceSpan = document.querySelector("#deliPrice");
	const endPriceSpan = document.querySelector("#endPrice");
	
	totalPriceInput.value = totalPriceVal;
	deliPriceInput.value = deliPriceVal;
	endPriceInput.value = totalPriceVal + deliPriceVal;
	
	totalPriceSpan.innerHTML = setFormat(totalPriceVal);
	deliPriceSpan.innerHTML = setFormat(deliPriceVal);
	endPriceSpan.innerHTML = setFormat(totalPriceVal + deliPriceVal);
}

let checkList = [];		// 주문 리스트
let deleteList = [];	// 삭제 리스트

// 체크박스 전체
let checkCounts = document.querySelectorAll(".check");

// 수량 변경
function count(num, name) {
	const countInput = document.querySelector("input[name='" + name + "']");
	const pro_oriPriceInput = document.querySelector("input[name='oriPrice_" + name + "']");
	const pro_totalPriceInput = document.querySelector("input[name='totalPrice_" + name + "']");
	const pro_totalPriceSpan = document.querySelector("#totalPrice_" + name);
	
	let cnt = parseInt(countInput.value);
	let pro_oriPrice = parseInt(pro_oriPriceInput.value);
	let pro_totalPrice = parseInt(pro_totalPriceInput.value);
	
	if (num == -1) { // 수량 증가
		cnt++;
	} else if (num == -2){ // 수량 감소
		cnt--;
	}
	
	pro_totalPrice = pro_oriPrice*num;
	
	if(cnt < 1){
		playToast("수량은 1개 이상이어야 합니다.", 'warning');
		cnt = 1;
		pro_totalPrice = pro_oriPrice;
	}
	
	countInput.value = cnt;
	pro_totalPriceInput.value = pro_totalPrice;
	pro_totalPriceSpan.innerHTML = setFormat(pro_totalPrice);
	
	setPrice();

	$.ajax({
		url: "/w2/cartUpdate.do",
		type: "POST",
		async: true,
		dataType: "text",
		data: JSON.stringify({
			cartId : name,
			cnt : cnt
		}),
		contentType: "application/json",
		success: function(result){
			playToast("수량이 변경되었습니다.", 'success');
		},
		error : function(error){
			playToast("ajax는 실패입니다.", 'error');
		}
	});
}

// 숫자 표기
function setFormat(num){
	let result = num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return result;
}
