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
</head>
<body>
	<h2>Ajax Test</h2>
	
	<h3>1. 버튼 클릭 시 get 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 : <input type="text" id="input1">
	<button id="btn1">전송</button><br>
	응답 : <label id="output1">현재 응답 없음</label>
	<script>
		$(function(){
			$('#btn1').click(function(){
				// 동기식 통신 : location.href= 'url?param';
				// 비동기식 통신 : $.ajax()
				console.log($('#input1').val());
			
				$.ajax({
					url: '/w2/ajax1.to',
					type: "GET",
					dataType: "text",
					data: {input1: $('#input1').val()},
					success: function(result){
						$("#output1").text(result);
					},
					error: function(){
						alert("ajax1 실패");
					},
					complete: function(){
						console.log("ajaxTest1.complete");
					}
				});
			});
		});
	</script>
	
	<hr>
	
	<h3>2. 버튼 클릭 시 post 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 1 : <input type="text" id="input2_1"><br>
	입력 2 : <input type="text" id="input2_2"><br>
	<button onclick="test2()">전송</button>
	응답 : <label id="output2">현재 응답 없음</label>
	<script>
		function test2(){
			$.ajax({
				url: '/w2/ajax2.to', // 요청할 주소
				type: "POST",
				dataType: "text",
				data: {
					input2_1: $("#input2_1").val(),
					input2_2: $("#input2_2").val()
				}, 
				success: function(result){
					$("#output2").text(result);
				},
				error: function(){
					alert("ajax2 실패");
				}
			});
		}			
	</script>
	
	<hr>
	
	<h3>3. 버튼 클릭시 get 방식으로 서버에 데이터 전송 후 다른 페이지에서 응답</h3>
	입력 : <input type="text" id="input3"><br>
	<button onclick="test3()">전송</button><br>
	<script>
		function test3(){
			$.ajax({
				url: '/w2/ajax3.to',
				type:"get",
				async: false,
				dataType: "text",
				data: {
					input3 : $("#input3").val()
				},
				success: function(result){
					$("output3").text(result);
					location.href="/w2/ajax3to.to?result=" + result;
				},
				error: function(){
					alert("ajax3 실패");
				},
				complete: function(){
					console.log("ajax3 complete");
				}
			});
		}
	</script>
</body>
</html>