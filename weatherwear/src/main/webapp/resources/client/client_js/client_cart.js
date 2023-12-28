$(document).ready(function() {
	
	$('.cart_content').each(function(index, item) {		
		var cnt = parseInt($(this).find('#odTotal').val());
		var proPrice = parseInt($(this).find('#proPrice').val());
		var proAmount = cnt * proPrice;
		
		if(isNaN(cnt)) cnt = 0;
		if(isNaN(proPrice)) proPrice = 0;
		if(isNaN(proAmount)) proAmount = 0;
		
		$(this).find('.proAmount span').text(priceFormat(proAmount));
	});
	
	totalPrice();
	
	// 전체 선택
	$('#checkAll').on("click", function() {
		if($(this).prop('checked')) {
			$('.checklist input[name=checkbox]').prop('checked', true);
		} else {
			$('.checklist input[name=checkbox]').prop('checked', false);
		}
		
		totalPrice();
	});
	
	// 개별 선택
	$('.check').on("click", function() {
		
		$(this).prop('checked', $(this).is(":checked"));
		
		var chkCnt = $('.check').length;
		var tCnt = 0;
		$('.check').each(function() {
			if($(this).is(":checked")) {
				tCnt++;
			}
			if(tCnt == chkCnt) {
				$('#checkAll').prop('checked', true);			
			} else {
				$('#checkAll').prop('checked', false);	
			}
		});
		
		totalPrice();
		
		cartdao
	});
	
	// 전체 삭제
	$('#deleteAll').on("click", function() {
		$('#checkAll').click();
		
		if(confirm("전체 상품을 삭제하시겠습니까?") == true) {
			deleteCartItem(1);
		}
	});

	// 선택 삭제
	$('#deleteSelected').on("click", function() {
		
		var tCnt = 0;
		$('.check').each(function() {
			if($(this).is(":checked")) {
				tCnt++;
			}
		});

		if(tCnt == 0) {
			alert("삭제하실 상품을 선택해주세요.");
			return;
		}
		
		// tCnt > 0
		if(confirm("선택하신 상품을 삭제하시겠습니까?") == true) {
			deleteCartItem(2);
		}
	});

});

function deleteCartItem(flag) {
	
	var optList = new Array();
	
	$('.check').each(function() {
		if($(this).is(":checked")) {
			var caId = $(this).siblings(".caId").val();
			var obj = new Object();
			
			obj.caId = caId;
			
			optList.push(obj);
		}
	});

	var jsonData = JSON.stringify(optList);
	
	$.ajax({
		type: "post",
		url: "deleteClientCart.do",
		dataType: "html",
		contentType: "application/json",
		data: jsonData,
		success: function(data) {
			console.log('장바구니 삭제 성공');
			location.reload();
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});
}

//전체 금액 계산
function totalPrice() {
	var totalPrice = 0;
	var deliPrice = 3000;
	var endPrice = 0;

	var chkCnt = $('.check').length;
	var tCnt = 0;
	$('.check').each(function() {
		if($(this).is(":checked")) {
			tCnt++;
		}
	});
	
	if(tCnt == 0 || tCnt == chkCnt) {	// 전체 상품 계산
		$('.proAmount').each(function(incex, item) {
			var price = parseInt($(this).find('span').text().replaceAll(",", ""));
			totalPrice += price;
		});
	} else {
		$('.check').each(function() {
			if($(this).is(":checked")) {
				var price = parseInt($(this).parents().siblings('.price').text().replaceAll(",", ""));
				totalPrice += price;
			}
		});
	}
		
	if(totalPrice > 50000) deliPrice = 0;
	
	$('#totalPrice').text(priceFormat(totalPrice));
	$('#deliPrice').text(priceFormat(deliPrice));
	$('#endPrice').text(priceFormat(totalPrice + deliPrice));
}

function count(num, name) {
	var caId = $('#caId_' + name).val();
	var cnt = parseInt($("div[name='cnt_" + name + "'] input[name='odTotal']").val());
	let proPrice = parseInt($("div[name='price_" + name + "'] input[name='proPrice']").val());
	var purAmount = 0;
	var opQty = parseInt($("input[name='opQty_" + name + "']").val());
	
	if (num == 1) {
		if(cnt == opQty) {
			alert("구매 가능 수량을 초과하였습니다.");
			return;
		}
		cnt++;
		purAmount = proPrice * cnt;
	} else if (num == 2){
		cnt--;
		purAmount = proPrice * cnt;
	}
	
	if (cnt < 1) {
		alert("1개 이상부터 구매하실 수 있습니다.");
		return;
	}
	
	$("div[name='cnt_" + name + "'] input[name='odTotal']").val(cnt);
	$("div[name='price_" + name + "'] span").text(priceFormat(purAmount));
	
	totalPrice();
	updateCart(caId, name, cnt);
}

// 장바구니 수량 업데이트
function updateCart(caId, opId, caCnt) {
	
	var obj = new Object();
		
	obj.caId = caId;
	obj.opId = opId;
	obj.caCnt = caCnt;
	
	var jsonData = JSON.stringify(obj);
	
	$.ajax({
		type: "post",
		url: "updateClientCart.do",
		dataType: "text",
		contentType: "application/json",
		data: jsonData,
		success: function(data) {
			console.log('장바구니 업데이트 성공');
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});
}

function priceFormat(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

