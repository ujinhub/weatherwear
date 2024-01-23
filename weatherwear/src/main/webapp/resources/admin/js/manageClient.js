/**
 * 
 */
 // 기본 정보 수정
function clientInfo(id){
	let clientId = id;
	let clientName = document.querySelector("#clientName_"+id).innerText;
	let clientNum = document.querySelector("#clientNum_"+id).innerText;
	let clientEmail = document.querySelector("#clientEmail_"+id).innerText;
	let gradeId = document.querySelector("#gradeId_"+id).innerText;
	let clientPoint = document.querySelector("#clientPoint_"+id).innerText;
	let clientRegDate = document.querySelector("#clientRegDate_"+id).innerText;
	let withdraw = $("input[name='"+ id + "_is']").val();
	
	let clientInfo = '';
	clientInfo += "<div class='modal-dialog' id='" + clientId + "'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>정보 수정</h4>";
	clientInfo += "<button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
	clientInfo += "<div class='modal-body'><div class='form-group'><label for='exampleInputBorderWidth2'>아이디<code></code></label>";
	clientInfo += "<input type='text' name='clientId' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + clientId + "' disabled='disabled'>";
	clientInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>이름<code></code></label>";
	clientInfo += "<input type='text' name='clientName' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + clientName + "'>";
	clientInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>연락처<code></code></label>";
	clientInfo += "<input type='text' name='clientNum' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + clientNum + "'>";
	clientInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>이메일<code></code></label>";
	clientInfo += "<input type='text' name='clientEmail' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + clientEmail + "'>";
	clientInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>등급<code> S, G, B</code></label>";
	clientInfo += "<input type='text' name='gradeId' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + gradeId + "'>";
	clientInfo += "</div><div class='form-group'><label for='exampleInputBorderWidth2'>포인트<code></code></label>";
	clientInfo += "<input type='text' name='clientPoint' class='form-control form-control-border border-width-2' id='exampleInputBorderWidth2' value='" + clientPoint + "'>";
	clientInfo += "</div></div><div class='modal-footer justify-content-between'><button type='button' class='btn btn-default' data-dismiss='modal' onclick='closeModal()'>취소하기</button>";
	clientInfo += "<div style='width:30%'></div>";
	
	if(withdraw != null && withdraw != ''){
		clientInfo += "<button type='button' class='btn btn-outline-danger' onclick='modify(2)'>탈퇴해제</button>";
	}
	
	clientInfo += "<button type='button' class='btn btn-primary' onclick='modify(1)'>수정하기</button></div></div></div>";
	openModel(clientInfo);
}

// 정보 입력
	let clientId = '';
	let clientName = '';
	let clientNum = '';
	let clientEmail = '';
	let gradeId = '';
	let clientPoint = '';
	
	function getInfo(){
		clientId = $("input[name='clientId']").val();
		clientName = $("input[name='clientName']").val();
		clientNum = $("input[name='clientNum']").val().replace("-","");
		clientEmail = $("input[name='clientEmail']").val();
		gradeId = $("input[name='gradeId']").val();
		clientPoint = $("input[name='clientPoint']").val().replace(",","");
	}

// 신규 등록/ 정보 수정
function modify(num){
	let url_type = '';
	if(num == 1){
		url_type = 'modifyClient.mdo';
	} else if(num == 2){
		if(!confirm("탈퇴를 해제하시겠습니까?")){
			return;
		} 
		url_type = 'redoClient.mdo';
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
			clientId: clientId,
			clientName: clientName,
			clientNum: clientNum,
			clientEmail: clientEmail,
			gradeId: gradeId,
			clientPoint: clientPoint
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
	})
}
