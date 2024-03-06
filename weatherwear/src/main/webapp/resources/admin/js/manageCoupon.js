/**
 * 
 */

//삭제
function deleteCoupon(target){
	let id = target.parentElement.id;
	let couponId = id.split("_")[1];
	
	playConfirm("쿠폰 정보를 삭제하시겠습니까?", "이미 발급된 쿠폰은 삭제되지 않습니다.", "question", "삭제하기", "취소하기", "deleteCouponAjax('" + couponId + "')", "return");
}

function deleteCouponAjax(couponId){
	$.ajax({
		type: "post",
		url: "couponDelete.mdo?couponId=" + couponId,
		dataType: "json",
		success: function(res) {
			if(res.code == -1) {
				playToast(res.message, 'error');
				return;
			} 
			
			if(res.code == 1) {
				playToast(res.message, 'success');
				window.location.reload();
			}
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});
}

// 정보 입력
	let couponName = '';
	let couponPrice = '';
	let minPrice = '';
	let couponStDate;
	let couponEndDate;
	let couponStTime;
	let couponEndTime;
	let gradeList = '';
	
	function getInfo(){
		gradeList = '';
		couponName = $("input[name='couponName']").val();
		couponPrice = $("input[name='couponPrice']").val();
		minPrice = $("input[name='minPrice']").val();
		couponStDate = $("input[name='couponStDate']").val();
		couponEndDate = $("input[name='couponEndDate']").val();
		couponStTime = $("input[name='couponStTime']").val();
		couponEndTime = $("input[name='couponEndTime']").val();
		
		let grade = document.querySelectorAll("input[type='checkbox']:checked");
		for(let i=0; i<grade.length; i++){
			gradeList += grade[i].value;
			if(i <grade.length-1){
				gradeList += ",";
			}
		}
	}

// 신규 등록
function insertCoupon(){
	getInfo();
	
	if(couponName == '' || couponName == null){
		playToast("쿠폰 이름은 필수 정보입니다.", "warning");
		$("input[name='couponName']").focus();
		return;
	} else if (couponPrice == '' || couponPrice == null ){
		playToast("쿠폰 가격은 필수 정보입니다.", "warning");
		$("input[name='couponPrice']").focus();
		return;
	} else if (minPrice == '' || minPrice == null ){
		playToast("쿠폰 최소 금엑은 필수 정보입니다.", "warning");
		$("input[name='minPrice']").focus();
		return;
	} else if (couponStDate == '' || couponStDate == null ){
		playToast("시작 일자를 선택해주세요", "warning");
		$("input[name='couponStDate']").focus();
		return;
	} else if (couponEndDate == '' || couponEndDate == null ){
		playToast("종료 일자를 선텍헤주세요", "warning");
		$("input[name='couponEndDate']").focus();
		return;
	} else if( gradeList =='' || gradeList == null){
		playToast("적용 등급을 선택해주세요", "warning");
		return;
	} 
	
	if(couponEndTime == "" || couponEndTime == null){
		couponEndTime = "00:00";
	} 
	
	if(couponStTime == "" || couponStTime == null){
		couponStTime = "00:00";
	} 
	
	console.log("couponStDate : " + couponStDate);
	console.log("couponStTime : " + couponStTime);
	console.log("couponEndDate : " + couponEndDate);
	console.log("couponEndTime : " + couponEndTime);
	
	$.ajax({
		type: "post",
		url: "couponInsert.mdo",
		dataType: "json",
		data: {
			couponName: couponName,
			couponPrice: parseInt(couponPrice),
			minPrice: parseInt(minPrice),
			couponStDate: couponStDate,
			couponStTime: couponStTime,
			couponEndDate: couponEndDate,
			couponEndTime: couponEndTime,
			gradeList: gradeList
		},
		success: function(res) {
			if(res.code == -1) {
				playToast(res.message, 'error');
				return;
			} 
			
			if(res.code == 1) {
				playToast(res.message, 'success');
				window.location.reload();
			}
			
			if(res.code == -2) {
				playToast(res.message, 'warning');
				$("input[name='couponId']").val("");
				$("input[name='couponId']").focus();
				return;
			}
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});
}

// 신규 등록
function newCoupon(){
	let newcoupon = "";
	newcoupon += "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><h4 class='modal-title'>신규 등록</h4>";
	newcoupon += "<button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick='closeModal()'><span aria-hidden='true'>×</span></button></div>";
	newcoupon += "<div class='modal-body'><div class='form-group'><label for='couponName'>쿠폰 이름<code></code></label>";
	newcoupon += "<input type='text' name='couponName' class='form-control form-control-border border-width-2' id='couponName' placeholder='쿠폰 이름'>";
	newcoupon += "</div><div class='form-group'><label for='couponPrice'>쿠폰 금액<code></code></label>";
	newcoupon += "<input type='number' name='couponPrice' class='form-control form-control-border border-width-2' id='couponPrice' placeholder='쿠폰 금액'>";
	newcoupon += "</div><div class='form-group'><label for='minPrice'>쿠폰 적용 최소 금액<code></code></label>";
	newcoupon += "<input type='number' name='minPrice' class='form-control form-control-border border-width-2' id='minPrice' placeholder='쿠폰 적용 최소 금액'>";
	newcoupon += "</div><div class='form-group'><label for='gradeList'>쿠폰 적용 등급<code></code></label>";
	newcoupon += "<div class='row checkboxDiv'><div class='checkDiv'><input type='checkbox' value='S' name='gradeList' class='checkbox' id='grade_S'>&nbsp;<label for='grade_S'>&nbsp;Silver&nbsp;</label>&nbsp;</div>";
	newcoupon += "<div class='checkDiv'><input type='checkbox' value='G' name='gradeList' class='checkbox' id='grade_G'>&nbsp;<label for='grade_G'>&nbsp;Gold&nbsp;</label>&nbsp;</div>";
	newcoupon += "<div class='checkDiv'><input type='checkbox' value='B' name='gradeList' class='checkbox' id='grade_B'>&nbsp;<label for='grade_B'>&nbsp;Black&nbsp;</label>&nbsp;</div></div>";
	newcoupon += "</div><div class='form-group'><label for='couponStDate'>쿠폰 시작 일자<code></code></label><div class='row'>";
	newcoupon += "<input type='date' name='couponStDate' class='form-control form-control-border border-width-2' id='couponStDate'>";
	newcoupon += "</div></div><div class='form-group'><label for='couponEndDate'>쿠폰 만료 일자<code></code></label><div class='row'>";
	newcoupon += "<input type='date' name='couponEndDate' class='form-control form-control-border border-width-2' id='couponEndDate'>";
	newcoupon += "</div></div></div><div class='modal-footer justify-content-between'><button type='button' class='btn btn-default' data-dismiss='modal' onclick='closeModal()'>취소하기</button>";
	newcoupon += "<button type='button' class='btn btn-primary' onclick='insertCoupon()'>등록하기</button></div></div></div>";
	openModel(newcoupon);
	
	let today = new Date();
	document.getElementById("couponStDate").min = today.toISOString().split("T")[0];
	document.getElementById("couponEndDate").min = today.toISOString().split("T")[0];
	document.getElementById("couponStDate").value = today.toISOString().split("T")[0];
	
	document.getElementById("couponStDate").addEventListener("change", function() {
		var startDate = new Date(this.value);
		document.getElementById("couponStDate").min = startDate.toISOString().split("T")[0];
		startDate.setDate(startDate.getDate() + 1); // 하루 이후 날짜로 설정
		startDate.setHours(0, 0, 0, 0);
		document.getElementById("couponEndDate").min = startDate.toISOString().split("T")[0];
	});
}
