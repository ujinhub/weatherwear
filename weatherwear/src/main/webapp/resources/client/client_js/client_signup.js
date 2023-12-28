/**
 * 
 */
$(document).ready(function(){

	// 약관 동의 페이지 
	const client_info_box = document.querySelector(".client_info_box");
	const client_term_box = document.querySelector(".client_term_box");
	const next_bt = document.querySelector(".term_next");
	const back_term = document.querySelector(".back_term");
	
	// 약관 > 개인정보 입력
	next_bt.addEventListener('click', () => {
		client_info_box.style.display = "flex";
		client_term_box.style.display = "none";
	});
	
	// 개인정보 > 약관
	back_term.addEventListener('click', () => {
		client_info_box.style.display = "none";
		client_term_box.style.display = "flex";
	});
});

// 정보 중복 확인
function check(comp){
	console.log("$ : " + $("#clientId").val());
	let checkWith = '';
	let keyword = '';
	let input = '';
	let span = '';
	
	// 아이디 확인
	if(comp == 'cId'){
		checkWith = $("#clientId").val();
		keyword = '아이디';
		span = "#checkId";
		input = "#clientId";
	// 닉네임 확인
	} else if(comp == 'cNickName'){
		checkWith = $("#clientNickName").val();
		keyword = '닉네임';
		span = "#checkNickName";
		input = "#clientNickName";
	// 이메일 확인
	} else if(comp == 'cEmail'){
		checkWith = $("#clientEmail").val();
		keyword = '이메일';
		span = "#checkEmail";
		input = "#clientEmail";
	}
	
	console.log(comp + " 체크 ajax 시작");
	$.ajax({
		url: "/w2/clientCheck.do?checkWith=" + checkWith + "&comp=" + comp,
		type: "POST",
		async: true,
		dataType: "text",
		data: JSON.stringify({
			checkWith : checkWith,
			comp: comp
		}),
		contentType: "application/json",
		success: function(result){
			
			if(result == 1) {
				$(span).html("<font color='red' size='2'><b>이미 존재하는 " + keyword + "입니다.</b></font>");
				$(input).val('');
			} else {
				$(span).html("<font color='green' size='2'><b>사용 가능한 " + keyword + "입니다.</b></font>");
			}
		},
		error : function(error){
			$("#clientId").val('');
		}
	})
}

// 비밀번호 확인
function checkPwd(data){
	// this로 받아오는 경우 값을 가져오지 않음
	
	const clientPwd = document.getElementById("clientPwd");
	const check = document.getElementById("checkPw");
	
	if(data.value == clientPwd.value){
		check.innerHTML = "<font color='green' size='2'><b>비밀번호가 일치합니다.</b></font>";
	} else {
		check.innerHTML = "<font color='red' size='2'><b>비밀번호가 일치하지 않습니다.</b></font>";
		data.val('');
	}
}