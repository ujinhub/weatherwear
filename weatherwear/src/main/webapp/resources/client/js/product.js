
// 옵션 정보 가져오기
function getOpList(){
	let optionList = $("input[name='optionInfo']").val();
	let opList = [];
	
	optionList = optionList.slice(1, -1);
	let option = optionList.split("}, {");
	
	option.forEach(op => {
		let option = new Map();
		op = op.split(', ');
		
		for(let i=0; i < op.length; i++){
			let keyValue = op[i].split('=');
			option.set(keyValue[0], keyValue[1]);
		}
		opList.push(option);
	});
	return opList;
}

// 옵션 수량 변경
function count(num, name) {
	var cnt = parseInt($("div[name='" + name + "'] input[name='cnt" + name + "']").val());
	var stCnt = parseInt($("div[name='" + name + "'] input[name='pro_" + name + "_cnt']").val());
	var odTotal = parseInt($("input[name='odTotal']").val());
	let productPrice = parseInt($("input[name='productPrice']").val());
	
	if (num == -1) {
		odTotal += productPrice;
		cnt++;
	} else if (num == -2){
		odTotal -= productPrice;
		cnt--;
	} else {
		let totalPriceList = document.getElementsByClassName("optioncount");
		let productCnt = 0;
		Array.prototype.forEach.call(totalPriceList, function(proCnt){
			productCnt += parseInt(proCnt.value);
		});
		odTotal = productPrice*productCnt;
		cnt = num;
	}
	
	if (cnt < 1) {
		cnt = 0;
		odTotal = 0;
		$("div[name='" + name + "']").remove();
	} else if(cnt > stCnt){
		cnt = stCnt;
		playToast("재고가 부족합니다.", 'warning');
	}
	
	$("div[name='" + name + "'] input[name='cnt" + name + "']").val(cnt);
	$("input[name='odTotal']").val(odTotal);
	setOdTotal();
}

function setOdTotal(){
	var odTotal = parseInt($("input[name='odTotal']").val());
	var formatter = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW', minimumFractionDigits: 0, maximumFractionDigits: 0 });
	var formattedNum = formatter.format(odTotal);
	
	$(".totalPrice").html(formattedNum);
}

// 선택 버튼 활성화
function activeBtn(selectElement){
	if(selectElement == null){
		let optionBtns = document.getElementsByClassName("optionBtn");
		Array.prototype.forEach.call(optionBtns, function(option){
			option.classList.remove("active");
		});
		$("input[name='colorValue'").val("");
		$("input[name='sizeValue'").val("");
		return;
	}

	let className = (selectElement.id).split("_")[0]+"Option";

	let optionBtns = document.getElementsByClassName(className);
	
	Array.prototype.forEach.call(optionBtns, function(option){
		option.classList.remove("active");
	});
	
	$("#" + selectElement.id).addClass("active");
	$("input[name='"+(selectElement.id).split("_")[0]+"Value'").val(selectElement.value);
}

// 옵션 선택
function select(selectElement) {
	let option = selectElement.id;

	activeBtn(selectElement);

	let opList = getOpList();
	
	// 선택한 값
	let color = $("input[name='colorValue'").val();
	let size = $("input[name='sizeValue'").val();
	let cnt = 0;
	
	// 재고 확인
	let stockCnt = 0;
	
	// 색상 + 사이즈 + 수량
	let stockList = $("input[name='stockList']").val();
	let stList = [];
	
	stockList = stockList.slice(1, -1);
	stockList = stockList.slice(1, -1);
	let stock = stockList.split("}, {");
	
	let odTotal = parseInt($("input[name='odTotal']").val());
	let productName = $("input[name='productName']").val();
	let productId = $("input[name='productId']").val();
	let productPrice = parseInt($("input[name='productPrice']").val());
	if(color == null || color == "" || size == null || size == ""){
		return;
	}
	
	stock.forEach(st => {
		let stock = new Map();
		st = st.split(', ');
		
		for(let i=0; i < st.length; i++){
			let keyValue = st[i].split('=');
			stock.set(keyValue[0], keyValue[1]);
		}
		stList.push(stock);
	});
	
	stList.forEach(st => {
		if(st.get("optionColor") == color && st.get("optionSize") == size){
			stockCnt = st.get("stockCnt");
		}
	});
	
	if (stockCnt == '0'){
		cnt = 0;
		playToast("재고가 부족합니다.", 'warning');
		// 초기화
		activeBtn(null);
		return;
	}
	
	var name= color+size;
	
	if($("#"+name).length){
		playToast("이미 선택한 옵션입니다.", 'warning');
		count(-1, name);
	} else {
		var select = "";
		select += "<div name='" + color + size + "' id='" + color + size + "' class='selectValue'>"
				+ "<p class='optionbox_p'>" + productName + "<br><span class='selectOption'>&nbsp;/&nbsp;" + color + "&nbsp;/&nbsp;" + size + "&nbsp;&nbsp;&nbsp;</span></p><p class='optionbox_p'>"
				+ "<span class='author mb-3 d-block'>" + productPrice + "</span><button id='id' name='" + name 
				+ "'class='pro_btn' onclick='deleteSelected(this)' style='float:right; margin-left:10px;'>X</button><span class='quantity' style='width:65px;'><a href='#' class='pro_btn' onclick='count(-2,\"" + name +"\")'>-</a>&nbsp;"
				+ "<input type='text' name='cnt" + name + "' class='quantity_opt eProductQuantityClass optioncount' id='cnt" + name + "'  name='cnt" + name 
				+ "' style='width:35px; height:27px; margin-left:3px; margin-right:3px; text-align:center;' value=1 max-value=" + stockCnt + " onChange='count(this.value,\"" + name +"\")'>&nbsp;"
				+ "<a href='#' class='pro_btn' onclick='count(-1,\"" + name +"\")'>+</a></span>"
				+ "<input type='hidden' name='optionId' value='" + productId + name + "'><input type='hidden' name='pro_" + name + "_cnt' value='" + stockCnt + "'>"
				+ "</p></div>";
		count(-1, name);
		$("#optionbox").append(select);
	}			
	// 초기화
	activeBtn(null);
}

function moveToProductInfoMenu(){
	document.querySelector('.product_info_menu').scrollIntoView();
}

function deleteSelected(element){
	let cnt = $(element).parent().parent().find("input[name='cnt" + element.name + "']").val();
	for(let i=0; i<cnt; i++){
		count(-2, element.name);
	}
	$(element).parent().parent().remove();
}

// 장바구니에 추가
function addCart(){
	// cartVO 객체를 가지는 리스트
	let productId = $("input[name='productId']").val();
	let list = addList(productId);
	
	if(list == null || list == ""){
		playToast("상품을 선택해주세요", 'warning');
		return;
	}
	
	$.ajax({
		url: "/w2/cartInsert.do",
		type: "POST",
		async: true,
		data: JSON.stringify(list),
		contentType: "application/json",
		success: function(response){
			playConfirm("장바구니에 상품이 담겼습니다.", "장바구니로 이동하시겠습니까?", "success", "장바구니로 이동", "계속 쇼핑하기", "location.href='cart.do'", 'window.location.reload()');
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 바로 구매하기
function orderNow(){
	// cartVO 객체를 가지는 리스트
	let productId = $("input[name='productId']").val();
	let list = addList(productId);
	
	if(list == null || list == ""){
		playToast("상품을 선택해주세요", 'warning');
		return;
	}
	
	$.ajax({
		url: "/w2/buyNowProduct.do",
		type: "POST",
		async: true,
		data: JSON.stringify(list),
		contentType: "application/json",
		success: function(response){
			location.href="orderRegister.do?cartList="+response;
		},
		error : function(error){
			playToast("오류가 발생했습니다.", 'error');
		}
	});
}

// 상품 리스트에 담기
function addList(productId){
	let list = [];
	let productCounts = document.querySelectorAll(".selectValue");

	productCounts.forEach(product => {
		let colorSize = product.id;
		let cart = {}; // 객체 생성
		
		cart.productId = productId;
		cart.optionId = product.querySelector("input[name='optionId']").value;
		cart.cartCnt = product.querySelector("input[name='cnt" + colorSize + "']").value;
		
		list.push(cart);
	});
	
	return list;
}

// 상품 위시리스트에 추가
function addWishList(){
	let checkList = [];
	checkList.push($("input[name='productId']").val());
	insertWishList(checkList);
}

