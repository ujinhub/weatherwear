/**
 * 
 */
 $(document).ready(function() {
 	var sessionLogin = '';
 	$.ajax({
 		url: 'sessionCheck.mdo',
 		type: 'post',
 		async: false,
 		success: function(result) {
 			sessionLogin = result;
 		},
 		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
 		}
 	});
 	
 	if(sessionLogin == '') {
 		playalert('장시간 미사용으로 자동 로그아웃 처리되었습니다.');
 		location.href = 'login.mdo';
 	}
 });