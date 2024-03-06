/**
 * 
 */
 
// 모달 열기
function openModel(content){
	document.querySelector("#modal-default").style.display="block";
	$(".modal-backdrop").addClass("show");
	document.querySelector(".modal-backdrop").style.display="block";
	$("#modal-default").html(content);
}

// 모달 닫기
function closeModal(){
	document.querySelector(".modal-backdrop").style.display="none";
	$(".modal-backdrop").removeClass("show");
	document.querySelector("#modal-default").style.display="none";
	$("#modal-default").html("");
}

$(document).ready(function(){
	// ESC 버튼으로 나가기
	$(document).keydown(function(e){
		var code = e.keyCode || e.which;
		if(code == 27){	// code 27 = ESC
			closeModal();
		}
	});
});