
function count(num, name) {
	var cnt = parseInt($("div[name='" + name + "'] input[name='cnt" + name + "']").val());
	var odTotal = parseInt($("input[name='odTotal']").val());
	let proPrice = parseInt($("input[name='proPrice']").val());
	
	if (num == 1) {
		odTotal += proPrice;
		cnt++;
	} else if (num == 2){
		odTotal -= proPrice;
		cnt--;
	}
	
	if (cnt < 1) {
		cnt = 0;
		$("div[name='" + name + "']").remove();
	}
	
	$("div[name='" + name + "'] input[name='cnt" + name + "']").val(cnt);
	$("input[name='odTotal']").val(odTotal);
}

function select(selectElement) {
	var color = document.getElementById("opColor").value;
	//console.log(color);
	var size = document.getElementById("opSize").value;
	//console.log(size);
	var odTotal = parseInt($("input[name='odTotal']").val());
	var proName = $("input[name='proName']").val();
	var proId = $("input[name='proId']").val();
	
	if(color === "SELECT" || size === "SELECT"){
		return;
	}
	
	var name= color+size;
	var rename = "'" + name + "'";
	
	if($("#"+name).length){
		alert("이미 있다");
		count(1, name);
	} else {
		var select = "";
		select += "<div name='" + color + size + "' id='" + proId + name + "' class='selectValue'>"
		//"<div name='" + color + size + "' id='" + color + size + "' class='selectValue'>"
				+ "<span class='proName'>" + proName + "</span>&nbsp;/&nbsp;" + color + "&nbsp;/&nbsp;" + size + "&nbsp;&nbsp;&nbsp;<br>"
				+ "<button type='button' class='pro_btn' onclick='count(2,\"" + name +"\")'>-</button>"
				+ "<input class='pro_btn' name='cnt" + name + "' style='width:35px; height:27px; margin-left:3px; margin-right:3px; text-align:center;' value=1>"
				+ "<button type='button' class='pro_btn' onclick='count(1,\"" + name +"\")'>+</button>"
				+ "<button id='id' name='" + name + "' class='pro_btn' onclick='deleteSelected(this)' style='flozat:right; margin-left:10px;'>X</button>"
				+ "</div><br>";

		count(1, name);
		$("#selectOption").append(select);
	}			
	// 초기화
	document.getElementById("opColor").value = "SELECT";
	document.getElementById("opSize").value = "SELECT";
}

function moveToProductInfoMenu(){
	document.querySelector('.product_info_menu').scrollIntoView();
}

function deleteSelected(element){
	let cnt = $(element).parent().find("input[name='cnt" + element.name + "']").val();
	for(let i=0; i<cnt; i++){
		count(2, element.name);
	}
	$(element).parent().remove();
}

//========== 2023.12.26 ujin ==========
function insertCart() {
	var optCnt = $('#selectOption').children('div').length;

	if(optCnt < 1) {
		alert('옵션을 선택하지 않으셨습니다. 옵션을 선택해주세요.');
		return false;
	} 
	
	var optList = new Array();
	
	for(var i=0; i<optCnt; i++) {
		var proId = $('#proId').val();
		var optId = $('#selectOption').children('div:eq(' + i + ')').attr('id');
		var caCnt = $('#' + optId).children('input').val();

		var obj = new Object();
		
		obj.proId = proId;
		obj.opId = optId;
		obj.caCnt = caCnt;
		
		optList.push(obj);
	}
	
	var jsonData = JSON.stringify(optList);
	
	// 장바구니에 상품이 존재하는지 확인
	$.ajax({
		type: "post",
		url: "checkClientCart.do",
		dataType: "text",
		contentType: "application/json",
		data: jsonData,
		success: function(data) {
			if(data == 'exist') {
				if(confirm('장바구니에 동일한 상품이 있습니다.\n장바구니에 추가하시겠습니까?') == false) {
					return;
				}
			} 
			
			$.ajax({
				type: "post",
				url: "insertClientCart.do",
				dataType: "text",
				contentType: "application/json",
				data: jsonData,
				success: function(data) {
					console.log('장바구니 담기 성공 ' + data);
					if(data == 'isOver') {
						alert('구매 가능한 수량을 초과하였습니다.');
						return;
					}
					
					if(data == 'success') {
						if(confirm("장바구니에 상품을 담았습니다.\n장바구니로 이동하시겠습니까?") == true) {
							location.href = "clientCart.do";
						}
					}
				},
				error: function(request, status, error) {
					console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
				}
			});
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});
}