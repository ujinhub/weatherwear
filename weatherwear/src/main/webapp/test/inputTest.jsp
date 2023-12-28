<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IndexList</title>
	<link rel="stylesheet" href="/w2/resources/admin/admin_css/admin_base_style.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	
/* input 숨겨준다 */
input[type="checkbox"]{
  display:none;
  }

input[type="checkbox"] + label{
  cursor:pointer;
 }

input[type="checkbox"] + label > span{
  vertical-align: middle;
  padding-left: 5px;
 }

/* label:before에 체크하기 전 상태 CSS */
input[type="checkbox"] + label:before{
  content:"";
  display:inline-block;
  width:17px;
  height:17px;
  border:2px solid #F47C7C;
  border-radius: 4px;
  vertical-align:middle;
  }
  
/* label:before에 체크 된 상태 CSS */  
input[type="checkbox"]:checked + label:before{
  content:"";
  background-color:#F47C7C;
  border-color:#F47C7C;
  background-image: url('check_btn.png');
  background-repeat: no-repeat;
  background-position: 50%;
  }
</style>
</head>
<body>
	<h2>float 해제</h2>
	>>> 아래 div 삽입
	<div class="clear_con"></div>
	
	<hr>
	<h2>Input Type</h2>
	>>> 아래 코드 삽입하기<br>
	< link rel="stylesheet" href="/w2/resources/admin/admin_css/admin_base_style.css" >
	
	<h4>Sample</h4>
	<input type="text" placeholder="text">	<br>
	<input type="submit" value="submit">	<br>
	<input type="password" placeholder="password">	<br>
	<input type="search" placeholder="search">	<br>
	<input type="number" placeholder="number">	<br>
	<input type="reset" value="reset">	<br>
	<input type="email" placeholder="email">	<br>
	<input type="tel" placeholder="tel">	<br>
	<input type="email" placeholder="email">	<br>
	<input type="tel" placeholder="tel">	<br>
	<input type="date" placeholder="date">	<br><br>
	
	<input type="file" placeholder="file">	<br><br>
	
	<select class="select">
		<option selected value="0">Option 1</option>
		<option value="1">Option 2</option>
		<option value="2">Option 3</option>
		<option value="3">Option 4</option>
	</select>

	<h2>전체 체크/해제</h2>
	>>> 아래 코드 삽입하기<br>
	< script src="/w2/resources/util/util_js/util_checkbox.js" >< /script >
	
	<h4>Sample</h4>
	<script>
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
	</script>
	
	<div class="checklist">
		<input type="checkbox" id="checkAll" name="checkAll">
		<label for="checkAll"> 모든 항목에 동의합니다.</label><br>
	
		<input type="checkbox" value="" id="check01" class="check">
		<label for="check01">ch1</label><br>
	
		<input type="checkbox" value="" id="check02" class="check">
		<label for="check02">ch2</label><br>
	
		<input type="checkbox" value="" id="check03" class="check">
		<label for="check03">ch3</label><br>
	</div>		
</body>
</html>