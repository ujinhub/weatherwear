/**
 * 전체선택/해제 버튼으로 체크
 */
 $(document).ready(function(){
	// 전체선택
	$("#checkAll").click(function(){
		if($(this).hasClass("checkAll")){	// 전체선택
			$(this).removeClass("checkAll").addClass("uncheckAll").addClass("active");
			$(this).text("전체해제");
			$(".check").prop("checked", true);
		} else if($(this).hasClass("uncheckAll")){	// 전체해제
			$(this).removeClass("active").removeClass("uncheckAll").addClass("checkAll");
			$(this).text("전체선택");
			$(".check").prop("checked", false);
		}
	});
	
	// 전체선택(부가)
	$(".check").click(function(){
		if($(".check:checked").length < $(".checklist .check").length){	// 전체선택 아닌 경우
			$("#checkAll").removeClass("uncheckAll").removeClass("active").addClass("checkAll");
			$("#checkAll").text("전체선택");
		} else { // 전체선택인 경우
			$("#checkAll").removeClass("checkAll").addClass("uncheckAll").addClass("active");
			$("#checkAll").text("전체해제");
		}
	});
});
