/**
 * 위시리스트 추가/제거
 */
 
 // 위시리스트 추가
function insertWishList(checkList){
	$.ajax({
		url: "/w2/wishListInsert.do",
		type: "POST",
		dataType: "json",
		async: true,
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(res){
			if(res.code == -1) {
				playToast(res.message, 'error');
				return;
			} else if (res.code == -2){
				playConfirm(res.message, res.data, 'question', '로그인하기', '취소하기', "location.href='login.do'");
			} else if(res.code == 1) {
				playConfirm(res.message, res.data, 'question', '위시리스트로 이동', '계속 쇼핑하기', "location.href='mywishList.do'", null);
			} else if(res.code == -3){
				Swal.fire({	// 매개변수 있어서 직접 작성
					title: res.message,
					text: res.data,
					icon: 'question',
					showCancelButton: true,
					confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',
					confirmButtonText: '위시리스트에서 삭제',
					cancelButtonText: '취소하기',
					reverseButtons: true, // 버튼 순서 거꾸로
				}).then((result) => {
					if(result.isConfirmed){
						deleteWishList(checkList);
					} else {
						return;
					}
				});
			}
		},
		error : function(error){
			playToast(error.message, 'error');
		}
	});
}

// 위시리스트 삭제
function deleteWishList(checkList){
	$.ajax({
		url: "/w2/wishListDelete.do",
		type: "POST",
		async: true,
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(res){
			if(res.code == -1) {
				playToast(res.message, 'error');
				return;
			}else if (res.code == -2){
				playConfirm(res.message, res.data, 'question', '로그인하기', '취소하기', "location.href='login.do'", null);
			} 
			
			if(res.code == 1) {
				playToast(res.message, 'success');
			} 
			return;
		},
		error : function(error){
			playToast(error.message, 'error');
		}
	});
}