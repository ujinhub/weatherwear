/**
 * 
 */
 // 기본 정보 수정
function deliveryInfo(id){
	let deliveryId = id;
	let deliveryComName = document.querySelector("#deliveryComName_"+id).innerText;
	let deliveryComNum = document.querySelector("#deliveryComNum_"+id).innerText;
	let deliveryPrice = document.querySelector("#deliveryPrice_"+id).innerText;
	let deliveryName = document.querySelector("#deliveryName_"+id).innerText;
	let deliveryNum = document.querySelector("#deliveryNum_"+id).innerText;
	
	let deliInfo = '';
	deliInfo += "<div class='modal-dialog' id='" + deliveryId + "'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>정보 수정</h4>";
	deliInfo += "<button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
	deliInfo += "<div class='modal-body'><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사번호 <code>변경할 수 없습니다.</code></label>";
	deliInfo += "<input type='text' name='deliveryId' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryId + "' disabled='disabled'>";
	deliInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사이름<code></code></label>";
	deliInfo += "<input type='text' name='deliveryComName' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryComName + "'>";
	deliInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사 연락처<code></code></label>";
	deliInfo += "<input type='text' name='deliveryComNum' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryComNum + "'>";
	deliInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배비<code></code></label>";
	deliInfo += "<input type='text' name='deliveryPrice' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryPrice + "'>";
	deliInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>담당자<code></code></label>";
	deliInfo += "<input type='text' name='deliveryName' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryName + "'>";
	deliInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>담당자 연락처<code></code></label>";
	deliInfo += "<input type='text' name='deliveryNum' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + deliveryNum + "'>";
	deliInfo += "</div></div><div class='modal-footer justify-content-between'><button type='button' class='btn btn-default' data-dismiss='modal' onclick='closeModal()'>취소하기</button>";
	deliInfo += "<div style='width:30%'></div>";
	deliInfo += "<button type='button' class='btn btn-outline-danger' onclick='deleteDeli(this)'>삭제하기</button><button type='button' class='btn btn-primary' onclick='deli(1)'>수정하기</button></div></div></div>";
	openModel(deliInfo);
}

//삭제
function deleteDeli(target){
	let deliveryId = target.parentElement.parentElement.parentElement.id;
	if(confirm("택배사 정보를 삭제하시겠습니까?")){
		$.ajax({
			type: "post",
			url: "deliveryDelete.mdo",
			dataType: "json",
			data: {
				deliveryId: deliveryId,
			},
			success: function(res) {
				if(res.code == -1) {
					alert(res.message);
					return;
				} 
				
				if(res.code == 1) {
					alert(res.message);
					window.location.reload();
				}
			},
			error: function(request, status, error) {
				console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			}
		});
	}
}

// 정보 입력
	let deliveryId = '';
	let deliveryComName = '';
	let deliveryComNum = '';
	let deliveryPrice = '';
	let deliveryName = '';
	let deliveryNum = '';
	
	function getInfo(){
		deliveryId = $("input[name='deliveryId']").val();
		deliveryComName = $("input[name='deliveryComName']").val();
		deliveryComNum = $("input[name='deliveryComNum']").val();
		deliveryPrice = $("input[name='deliveryPrice']").val();
		deliveryName = $("input[name='deliveryName']").val();
		deliveryNum = $("input[name='deliveryNum']").val();
	}

// 신규 등록/ 정보 수정
function deli(num){
	let url_type = '';
	if(num == 1){
		url_type = 'deliveryUpdate.mdo';
	} else if(num == 2){
		url_type = 'deliveryInsert.mdo';
	} else {
		alert("오류가 발생했습니다. 다시 시도해주세요");
		return;
	}
	getInfo();
	$.ajax({
		type: "post",
		url: url_type,
		dataType: "json",
		data: {
			deliveryId: deliveryId,
			deliveryComName: deliveryComName,
			deliveryComNum: deliveryComNum,
			deliveryPrice: deliveryPrice,
			deliveryName: deliveryName,
			deliveryNum: deliveryNum
		},
		success: function(res) {
			if(res.code == -1) {
				alert(res.message);
				return;
			} 
			
			if(res.code == 1) {
				alert(res.message);
				window.location.reload();
			}
			
			if(res.code == -2) {
				alert(res.message);
				$("input[name='deliveryId']").val("");
				$("input[name='deliveryId']").focus();
				return;
			}
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	})
}

// 신규 등록
function newDeli(){
	let newDelivery = "";
	newDelivery += "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>신규 등록</h4>";
	newDelivery += "<button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
	newDelivery += "<div class='modal-body'><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사번호<code></code></label>";
	newDelivery += "<input type='text' name='deliveryId' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='택배회사'>";
	newDelivery += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사이름<code></code></label>";
	newDelivery += "<input type='text' name='deliveryComName' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='택배회사이름'>";
	newDelivery += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배회사 연락처<code></code></label>";
	newDelivery += "<input type='text' name='deliveryComNum' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='택배회사 연락처'>";
	newDelivery += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>택배비<code></code></label>";
	newDelivery += "<input type='text' name='deliveryPrice' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='택배비'>";
	newDelivery += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>담당자<code></code></label>";
	newDelivery += "<input type='text' name='deliveryName' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='담당자 이름'>";
	newDelivery += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>담당자 연락처<code></code></label>";
	newDelivery += "<input type='text' name='deliveryNum' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' placeholder='담당자 연락처'>";
	newDelivery += "</div></div><div class='modal-footer justify-content-between'><button type='button' class='btn btn-default' data-dismiss='modal' onclick='closeModal()'>취소하기</button>";
	newDelivery += "<button type='button' class='btn btn-primary' onclick='deli(2)'>등록하기</button></div></div></div>";
	openModel(newDelivery);
}
