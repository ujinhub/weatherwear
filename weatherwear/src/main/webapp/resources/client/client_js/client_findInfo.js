$(document).ready(function(){
	const findId_a = document.querySelector('#findId');
	const findPwd_a = document.querySelector('#findPwd');
	const findId_num = document.querySelector('#findId_num');
	const findPwd_num = document.querySelector('#findPwd_num');
	const findId_email = document.querySelector('#findId_email');
	const findPwd_email = document.querySelector('#findPwd_email');
	
	const findInfo_id_num = document.querySelector('#findInfo_id_num');
	const findInfo_id_email = document.querySelector('#findInfo_id_email');
	const findInfo_pwd_num = document.querySelector('#findInfo_pwd_num');
	const findInfo_pwd_email = document.querySelector('#findInfo_pwd_email');
	const result_div = document.querySelector('#result_div');

	const showForm = (target) => {
		findInfo_id_num.style.display = 'none';
		findInfo_id_email.style.display = 'none';
		findInfo_pwd_num.style.display = 'none';
		findInfo_pwd_email.style.display = 'none';
		result_div.style.display = 'none';
		
		target.style.display = 'flex';
		
		document.querySelectorAll("input[type='text']").forEach(input => {
		  input.value = '';
		});
	}
	
	const select = (target) => {
		findId_a.style.color="dimgrey";
		findId_a.style.fontWeight="normal";
		findPwd_a.style.color="dimgrey";
		findPwd_a.style.fontWeight="normal";
		
		target.style.color="dimgrey";
		target.style.fontWeight="bold";
	}

	findId_a.addEventListener('click', () => {
		showForm(findInfo_id_num);
		select(findId_a);
	});

	findId_num.addEventListener('click', () => {
		showForm(findInfo_id_num);
		select(findId_a);
	});

	findId_email.addEventListener('click', () => {
		showForm(findInfo_id_email);
		select(findId_a);
	});

	findPwd_a.addEventListener('click', () => {
		showForm(findInfo_pwd_num);
		select(findPwd_a);
	});
	
	findPwd_num.addEventListener('click', () => {
		showForm(findInfo_pwd_num);
		select(findPwd_a);
	});
	
	findPwd_email.addEventListener('click', () => {
		showForm(findInfo_pwd_email);
		select(findPwd_a);
	});
	
	// 아이디 찾기
	function findId(url_add, clientName, keyword){
		$.ajax({
			url: url_add,
			type: "POST",
			async: true,
			dataType: "text",
			data: JSON.stringify({
				clientName : clientName,
				keyword : keyword
			}),
			contentType: "application/json",
			success: function(result){
				console.log("ajax result : " + result);
				const result_div = document.querySelector('#result_div');
				
				if( result != "null"){
					$("#result_div").html("<h3><font color='dimgrey'>고객님의 아이디는 <font color='blue'>" 
											+ result + "</font>입니다.</font></h3>"
											+ "<h4><a class='result_div_a' href='/w2/clientLogin.do'>로그인하기</a></h4>");
				} else {
					$("#result_div").html("<h3><font color='dimgrey'>일치하는 정보가 존재하지 않습니다. 다시 시도해주세요.</font></h3>"
							+ "<h4><a class='result_div_a' onclick='findId_num.click()'>아이디 찾기</a></h4>");
				}
				
				showForm(result_div);
			},
			error: function(error){
				alert("아이디 찾기 중 오류가 발생했습니다.<br>다시 시도해주세요");
			}
		});
	}
	
	// 전화번호로 아이디 찾기
	document.getElementById("findIdNum").addEventListener("submit", function(event){
		// submit 기본 동작 방지
		event.preventDefault();
		
		let form = document.getElementById("findIdNum");
		let url_add = "/w2/clientFindId.do?type=cNum";
		let clientName = form.querySelector("input[name='clientName']").value;
		let keyword = form.querySelector("input[name='clientNum']").value;
		
		console.log("name : " + clientName);
		console.log("keyword : " + keyword);
		
		findId(url_add, clientName, keyword);				
	});
	
	// 이메일로 아이디 찾기
	document.getElementById("findIdEmail").addEventListener("submit", function(event){
		// submit 기본 동작 방지
		event.preventDefault();

		let form = document.getElementById("findIdEmail");
		let url_add = "/w2/clientFindId.do?type=cEmail";
		let clientName = form.querySelector("input[name='clientName']").value;
		let keyword = form.querySelector("input[name='clientEmail']").value;
		
		console.log("name : " + clientName);
		console.log("keyword : " + keyword);
		
		findId(url_add, clientName, keyword);				
	});
	
	// 전화번호로 비밀번호 찾기
	document.getElementById("findPwdNum").addEventListener("submit", function(event){
		// submit 기본 동작 방지
		event.preventDefault();
		
		let form = document.getElementById("findPwdNum");
		let url_add = "/w2/clientFindPwd.do?type=cNum";
		let clientName = form.querySelector("input[name='clientName']").value;
		let clientId = form.querySelector("input[name='clientId']").value;
		let keyword = form.querySelector("input[name='clientNum']").value;
		
		console.log("clientName : " + clientName);
		console.log("clientId : " + clientId);
		console.log("keyword : " + keyword);
		
		findPwd(url_add, clientName, clientId, keyword);				
	});
	
	// 이메일로 비밀번호 찾기
	document.getElementById("findPwdEmail").addEventListener("submit", function(event){
		// submit 기본 동작 방지
		event.preventDefault();

		let form = document.getElementById("findPwdEmail");
		let url_add = "/w2/clientFindPwd.do?type=cEmail";
		let clientName = form.querySelector("input[name='clientName']").value;
		let clientId = form.querySelector("input[name='clientId']").value;
		let keyword = form.querySelector("input[name='clientEmail']").value;
		
		console.log("clientName : " + clientName);
		console.log("clientId : " + clientId);
		console.log("keyword : " + keyword);
		findPwd(url_add, clientName, clientId, keyword);				
	});

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
	
	// 비밀번호 찾기
	function findPwd(url_add, clientName, clientId, keyword) {
		$.ajax({
			url: url_add,
			type: "POST",
			async: true,
			dataType: "text",
			data: JSON.stringify({
				clientName: clientName,
				keyword: keyword,
				clientId: clientId
			}),
			contentType: "application/json",
			success: function(result){
				console.log("ajax result : " + result);
				
				if(result == "1"){
					console.log("5. clientId : " + clientId);
					$("#result_div").html("<form id='resetPassword' method='post'></form>");
					$("#resetPassword").html("<h3>비밀번호 재설정</h3>"
									+ "<div class='client_table'>"
									+ "<ul class='client_table_ul'><li class='client_table_th'>비밀번호 : </li>"
									+ "<li class='client_table_td'><input type='password' name='clientPwd' id='clientPwd' required='required' placeholder='비밀번호'></li>"
									+ "<li class='client_table_info'></li></ul>"
									+ "<ul class='client_table_ul'><li class='client_table_th'>비밀번호 확인 : </li>"
									+ "<li class='client_table_td'><input type='password' name='clientCheckPwd' id='clientCheckPwd' required='required' placeholder='비밀번호 확인'></li>"
									+ "<li class='client_table_info'><span id='checkPw'></span></li></ul>"
									+ "<ul class='client_table_ul'><li class='client_table_li col'><input type='submit' class='btn_form' value='변경하기'></li></ul></div>");
				} else {
					$("#result_div").html("<h3><font color='dimgrey'>일치하는 정보가 존재하지 않습니다. 다시 시도해주세요.</font></h3>"
							+ "<h4><a class='result_div_a' onclick='findPwd_num.click()'>비밀번호 찾기</a></h4>");
				}
				showForm(result_div);
				
				// 비밀번호 변경하기
				document.getElementById("resetPassword").addEventListener("submit", function(event){
					// submit 기본 동작 방지
					event.preventDefault();
					
					let form = document.getElementById("resetPassword");
					let clientPwd = form.querySelector("input[name='clientPwd']").value;
					console.log("clientPwd : " + clientPwd);
					
					$.ajax({
						url: "/w2/clientSetPwd.do",
						type: "POST",
						async: true,
						dataType: "text",
						data: JSON.stringify({
							clientPwd : clientPwd,
							clientId : clientId
						}), 
						contentType: "application/json",
						success: function(result){
							console.log("ajax result : " + result);
							
							if(result == "1"){
								$("#result_div").html("성공적으로 변경되었습니다. 3초 후 로그인 페이지로 이동합니다.");
								
								setTimeout(function(){
									location.href = "/w2/clientLogin.do";
								}, 3000);
							} else {
								alert("비밀번호 변경 중 오류 발생");
							}
						},
						complete: function(){
							console.log("비밀번호 변경 완료");
						}
					})				
				});
			},
			error: function(error){
				alert("비밀번호 찾기 중 오류가 발생했습니다.<br>다시 시도해주세요");
			}
		});
	}
});

