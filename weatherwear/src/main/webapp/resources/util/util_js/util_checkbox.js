/**
 * 전체 선택/해제
 * #checkAll : 전체 체크 checkbox id
 * .checkList : checkbox가 전체포함된 div의 class 이름에 부여
 * .check : checkbox class에 포함
 */
 	
$(function(){
	$("#checkAll").click(function(){
		$(".check").prop("checked", this.checked);
	});
	
	$(".check").click(function(){
		if($(".check:checked").length < $(".checklist .check").length){
			$("#checkAll").prop("checked", false);
		} else {
			$("#checkAll").prop("checked", true);
		}
	});
});
