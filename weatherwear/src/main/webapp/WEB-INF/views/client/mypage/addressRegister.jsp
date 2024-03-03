<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WeatherWear 사용자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Google Fonts -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=EB+Garamond:wght@400;500&amp;family=Inter:wght@400;500&amp;family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap" rel="stylesheet">
	<!-- Theme style -->
	<link rel="stylesheet" href="resources/admin/AdminLTE/dist/css/adminlte.min.css">
	<!-- Vendor CSS Files -->
	<link href="resources/client/ZenBlog/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/client/ZenBlog/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<!-- Swiper -->
	<link href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" rel="stylesheet"/>
	<!-- Template Main CSS Files -->
	<link href="resources/client/ZenBlog/assets/css/main.css" rel="stylesheet">
	<link href="resources/client/ZenBlog/assets/css/variables.css" rel="stylesheet">
	<!-- Font Awesome -->
	<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
	<style>
		.mg-1 { margin-top: 10px; margin-bottom: 10px; }
		.mg-2 { margin: 20px 0; }
		.mg-3 { margin: 30px 0 0; }
		.pd-1 { padding: 10px; }
		.table td {
			vertical-align: middle;
		}
		.required .col-form-label:after { content: '\00a0\2713'; }
		.description { color: #6c757d; font-size: 15px; vertical-align: middle; }
	</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact mb-2">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">ADDRESS</h1>
						</div>
					</div>
				</div>
				
				<div style="width: 70%; margin: 0 auto;">
					<form action="addressRegProc.do" method="post">
						<div class="card-body">
							<div class="form-group row required">
								<label for="addressTitle" class="col-sm-2 col-form-label">배송지명</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="addressTitle" name="addressTitle" required>
								</div>
							</div>
							<div class="form-group row required">
								<label for="addressName" class="col-sm-2 col-form-label">받는사람</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="addressName" name="addressName" required>
								</div>
							</div>
							<div class="form-group row required">
								<label for="addressTitle" class="col-sm-2 col-form-label">주소</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="addressPostNum" name="addressPostNum" required>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-outline-secondary" id="findAddressBtn" onclick="daumPost()">주소검색</button>
								</div>
							</div>
							<div class="form-group row">
								<label for="address1" class="col-sm-2 col-form-label"></label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="address1" name="address1">
								</div>
								<div class="col-sm-3">
									<span class="description">기본주소</span>
								</div>
							</div>
							<div class="form-group row">
								<label for="address2" class="col-sm-2 col-form-label"></label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="address2" name="address2" required>
								</div>
								<div class="col-sm-3">
									<span class="description">상세주소</span>
								</div>
							</div>
							<div class="form-group row">
								<label for="addressNum" class="col-sm-2 col-form-control">연락처</label>
								<input type="hidden" class="form-control" id="addressNum" name="addressNum">
								<div class="col-sm-2">
									<select class="form-control" id="addressNum1" required>
			                        	<option value="010">010</option>
				                        <option value="011">011</option>
				                        <option value="016">016</option>
				                        <option value="017">017</option>
				                        <option value="018">018</option>
				                        <option value="019">019</option>
			                        </select>
								</div>
								&nbsp;-&nbsp;
								<div class="col-sm-2">
									<input type="text" class="form-control" id="addressNum2" name="addressNum2" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
								</div>
								&nbsp;-&nbsp;
								<div class="col-sm-2">
									<input type="text" class="form-control" id="addressNum3" name="addressNum3" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
								</div>
							</div>
							<div class="form-group row" id="deliMsgDiv">
								<label for="addressMemo" class="col-sm-2 col-form-control">배송메시지</label>
								<div class="col-sm-7">
									<select class="form-control selectOption" id="addressMemo" name="addressMemo" onchange="setMessage()">
										<option value="" selected>---------- 배송메시지 선택 (선택사항) ----------</option>
										<option value="배송 전에 미리 연락바랍니다.">배송 전에 미리 연락바랍니다.</option>
										<option value="부재 시 경비실에 맡겨주세요.">부재 시 경비실에 맡겨주세요.</option>
										<option value="부재 시 문 앞에 놓아주세요.">부재 시 문 앞에 놓아주세요.</option>
										<option value="빠른 배송 부탁드립니다.">빠른 배송 부탁드립니다.</option>
										<option value="택배함에 보관해 주세요.">택배함에 보관해 주세요.</option>
										<option value="inputmessage" <c:if test="${ userInfo != null && baseAddress.addressMemo != null}">selected="selected"</c:if>>직접 입력</option>
									</select>
									<div class="form-group" id="deliDiv">
										<c:if test="${ userInfo != null && baseAddress.addressMemo != null}">
											<input type='text' class='form-control' name='deliMsg' id='deliMsg' required value="${ baseAddress.addressMemo }">
										</c:if>
									</div>
								</div>
							</div>
							<div class="form-group float-right">
								<input type="checkbox" class="form-checkbox-input" id="addressBase" name="addressBase">
								<label for="addressBase" class="form-input-label">기본 배송지로 등록</label>
							</div>
						</div>
						<div class="card-footer float-right">
							<button type="submit" class="btn btn-primary">등록</button>
							<button type="button" class="btn btn-outline-dark" onclick="history.go(-1)">취소</button>
						</div>
					</form>
				</div>
			</section>
		</main>
		
		<%@ include file="../footer.jsp" %>
	</div>

	<script src="resources/client/ZenBlog/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script  src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	
	<!-- Template Main JS File -->
	<script src="resources/client/ZenBlog/assets/js/main.js"></script>
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
	<!-- 주소 검색 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<script src="resources/client/js/post.js"></script>
	<script src="resources/util/js/paging.js"></script>
	<script>
		$(document).ready(function() {
			$('#checkAll').on('click', function() {
				if($("#checkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
				else $("input[name=chk]").prop("checked", false);
			});
			
			$("input[name=chk]").click(function() {
				var total = $("input[name=chk]").length;
				var checked = $("input[name=chk]:checked").length;
		
				if(total != checked) $("#checkAll").prop("checked", false);
				else $("#checkAll").prop("checked", true); 
			});
			
			//배송메세지 직접 입력란 생성
			function setMessage(){
				if(document.querySelector("#deliveryMessage").value == "inputmessage"){
					$("div#deliDiv").html("<input type='text' class='form-control' name='deliMsg' id='deliMsg' required placeholder='배송 메세지 직접 입력'>");
					$("#deliMsg").focus();
				} else {
					$("div#deliDiv").html("");
				}
			}
		});
	</script>
</body>
</html>