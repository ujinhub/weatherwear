<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WeatherWear 관리자</title>
	<!-- Font Awesome -->
	<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
	<!-- Theme style -->
	<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
	<!-- summernote -->
	<link href="resources/util/plugins/summernote/summernote-lite.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
	
		<div class="content-header">
			<section class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>공지사항 정보</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item"><a href="noticeList.mdo">공지사항 목록</a></li>
								<li class="breadcrumb-item active">공지사항 정보</li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			
			<section class="content">
				<div class="container">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card card-secondary">
									<div class="card-header">
										<h3 class="card-title">공지사항 상세 정보</h3>
									</div>
								</div>
								<form class="form-horizontal" id="noticeUpdateForm" action="noticeUpdateProc.mdo" method="post">
									<input type="hidden" id="noticeId" name="noticeId" value="${info.noticeId}">
									<div class="card-body">
										<div class="form-group row">
											<label for="noticeTitle" class="col-sm-1 col-form-control">제목</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="noticeTitle" name="noticeTitle" value="${info.noticeTitle}">
											</div>
										</div>
										<div class="form-group row">
											<label for="noticeWriter" class="col-sm-1 col-form-control">작성자</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="noticeWriter" name="noticeWriter" value="${info.noticeWriter}" readonly>
											</div>
										</div>
										<div class="form-group row">
											<label for="noticeDate" class="col-sm-1 col-form-control">작성일</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="noticeDate" name="noticeDate" value="${info.noticeDate}" readonly>
											</div>
										</div>
										<div class="form-group row">
											<label for="noticeContent" class="col-sm-1 col-form-control">내용</label>
											<div class="col-sm-10">
												<textarea class="form-control" id="summernote" name="noticeContent">${info.noticeContent}</textarea>
											</div>
										</div>
									</div>
									
									<div class="card-footer">		
										<button type="button" id="btnDelete" class="btn btn-danger float-left">삭제</button>
										<button type="submit" class="btn btn-info float-right">수정</button>
	                 					<button type="reset" class="btn btn-default float-right">취소</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					 
				</div>
			</section>
		</div>		
		
		<%@ include file="../footer.jsp" %>
	</div>
	
	<!-- jQuery -->
	<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script	src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	<!-- Bootstrap 4 -->
	<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery-validation -->
	<script src="resources/admin/AdminLTE/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script src="resources/admin/AdminLTE/plugins/jquery-validation/additional-methods.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
	<!-- summernote -->
	<script src="resources/util/plugins/summernote/summernote-lite.js"></script>
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	
	<script src="resources/admin/js/common.js"></script>
	<script src="resources/util/js/summernote.js"></script>
	<script>
		$(function() {
			$('#noticeUpdateForm').validate({
				rules: {
					noticeTitle: {
						required: true,
					}
				},
				messages: {
					noticeTitle: {
						required: "제목을 입력해주세요.",
					}
				},
				errorElement: 'span',
				errorPlacement: function(error, element) {
					error.addClass('invalid-feedback');
					element.closest('.form-group div').append(error);
				},
				highlight: function(element, errorClass, validClass) {
					$(element).addClass('is-invalid');
				},
				unhighlight: function(element, errorClass, validClass) {
					$(element).removeClass('is-invalid');
				}
			});
			
			$('#btnDelete').on('click', function() {
				if(confirm('삭제하시겠습니까?')) {
					location.href = "noticeDeleteProc.mdo?noticeId=" + $('#noticeId').val();
				}
			});
		});
	</script>
</body>
</html>