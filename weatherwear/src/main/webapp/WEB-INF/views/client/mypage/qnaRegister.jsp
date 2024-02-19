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

<!-- summernote -->
<link href="resources/util/plugins/summernote/summernote-lite.css" rel="stylesheet">

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
							<h1 class="page-title">1:1 문의</h1>
						</div>
					</div>
				</div>
				
				<div style="width: 70%; margin: 0 auto;">
					<form id="qnaRegForm" action="qnaRegProc.do" method="post" enctype="multipart/form-data">
						<input type="hidden" id="key" value="qna">
						<div class="card-body">
							<div class="form-group row">
								<input type="hidden" name="clientId" value="${userInfo.clientId}">
								<label for="clientName" class="col-sm-2 col-form-label">이름</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" value="${userInfo.clientName}" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="clientEmail" class="col-sm-2 col-form-label">이메일</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="clientEmail" name="clientEmail" value="${userInfo.clientEmail}" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="clientNum" class="col-sm-2 col-form-label">전화번호</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="clientNum" name="clientNum" value="${fn:substring(userInfo.clientNum,0,3)}-${fn:substring(userInfo.clientNum,3,7)}-${fn:substring(userInfo.clientNum,7,12)}" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label for="qnaType" class="col-sm-2 col-form-label">분류</label>
								<div class="col-sm-3">
									<select class="form-control" id="qnaType" name="qnaType" required>
			                        	<option value="">선&nbsp;&nbsp;택</option>
				                        <option value="회원정보">회원정보</option>
			                        	<option value="상품확인">상품확인</option>
				                        <option value="주문/결제">주문/결제</option>
				                        <option value="배송">배송</option>
				                        <option value="교환/취소(반품)">교환/취소(반품)</option>
				                        <option value="서비스">서비스</option>
			                        </select>
								</div>
							</div>
							<div class="form-group row">
								<label for="qnaTitle" class="col-sm-2 col-form-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="qnaTitle" name="qnaTitle">
								</div>
							</div>
							<div class="form-group row">
								<label for="qnaContent" class="col-sm-2 col-form-label">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="summernote" name="qnaContent"></textarea>
								</div>
							</div>
<!-- 							<div class="form-group row"> -->
<!-- 								<label for="qnaImage" class="col-sm-2 col-form-label">첨부파일</label> -->
<!-- 								<div class="col-sm-10"> -->
<!-- 									<input type="file" name="qnaImage" accept="image/jpeg,image/png" multiple> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="form-group row">
								<label for="qnaSecPwd" class="col-sm-2 col-form-label">비밀번호</label>
								<div class="col-sm-3">
									<input type="password" class="form-control" id="qnaSecPwd" name="qnaSecPwd">
								</div>
							</div>
							<div class="form-group row">
								<label for="qnaSecCheck" class="col-sm-2 col-form-label">비밀글설정</label>
								<div class="float-left col-sm-1">
									<input type="radio" id="noSec" name="qnaSecCheck" value="N">
									<label for="noSec" class="form-check-label">공개글</label>
								</div>
								<div class="float-left col-sm-1">
									<input type="radio" id="yesSec" name="qnaSecCheck" value="Y" checked>
									<label for="yesSec" class="form-check-label">비밀글</label>
								</div>
							</div>
						</div>
						<div class="card-footer mg-3">
							<div class="row col-lg-8" style="margin-left: 25%;">
								<div class="col-sm-4">
									<button type="submit" class="btn btn-block btn-primary">문의하기</button>
								</div>
								<div class="col-sm-4">
									<button type="button" class="btn btn-block btn-outline-dark" onclick="history.go(-1)">취소</button>
								</div>
							</div>
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

<!-- jQuery-validation -->
<script src="resources/admin/AdminLTE/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="resources/admin/AdminLTE/plugins/jquery-validation/additional-methods.min.js"></script>

<!-- summernote -->
<script src="resources/util/plugins/summernote/summernote-lite.js"></script>

<script src="resources/util/js/summernote.js"></script>

<script>
$(document).ready(function() {
	$('#qnaRegForm').validate({
		rules: {
			qnaTitle: {
				required: true,
			},
			qnaContent: {
				required: true,
			},
			qnaSecPwd: {
				required: "#yesSec:checked",
			},
		},
		messages: {
			qnaTitle: {
				required: "",
			},
			qnaContent: {
				required: "",
			},
			qnaSecPwd: {
				required: "",
			},
		},
		errorElement: 'span',
		errorPlacement: function(error, element) {
// 			error.addClass('invalid-feedback');
// 			element.closest('.form-group div').append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		},
	}); 
	
// 	$('#summernote').summernote({
//  		height: 500,
//  		minHeight: null,
//  		maxHeight: null,
//  		focus: false,
//  		lang: 'ko-KR',
//  		toolbar: [
//  					['fontname', ['fontname']],
//  					['fontsize', ['fontsize']],
//  					['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
//  					['color', ['forecolor', 'color']],
//  					['table', ['table']],
//  					['para', ['ul', 'ol', 'paragraph']],
//  					['height', ['height']],
//  					['insert', ['link']],
//  					['view', ['help']]
//  				],
//  		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움', '돋움체', '바탕체'],
//  		fontSize: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
//  	});
});
</script>
</body>
</html>