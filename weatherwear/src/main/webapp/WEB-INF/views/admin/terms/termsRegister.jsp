<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<h1>약관 추가</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item"><a href="termsList.mdo">약관 목록</a></li>
								<li class="breadcrumb-item active">약관 추가</li>
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
										<h3 class="card-title">약관 정보 입력</h3>
									</div>
								</div>
								<form class="form-horizontal" id="termsRegForm" action="termsRegProc.mdo" method="post">
									<div class="card-body">
										<div class="form-group row">
											<label for="termTitle" class="col-sm-1 col-form-control">제목</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="termTitle" name="termTitle" placeholder="제목을 입력하세요">
											</div>
										</div>
										<div class="form-group row">
											<label for="termContent" class="col-sm-1 col-form-control">내용</label>
											<div class="col-sm-10">
												<textarea class="form-control" id="summernote" name="termContent"></textarea>
											</div>
										</div>
										<div class="form-group row">
											<label for="inputApplyDate" class="col-sm-1 col-form-control">적용일자</label>
											<div class="col-sm-10">
												<input type="hidden" id="termApplyDate" name="termApplyDate">
												<input type="date" class="form-control" id="inputApplyDate" name="inputApplyDate">
											</div>
										</div>
										<div class="form-group row">
											<label for="inputEndDate" class="col-sm-1 col-form-control">만료일자</label>
											<div class="col-sm-10">
												<input type="hidden" id="termEndDate" name="termEndDate">
												<input type="date" class="form-control" id="inputEndDate" name="inputEndDate">
											</div>
										</div>
										<div class="form-group row">
											<label for="termNecessary" class="col-sm-1 col-form-control">필수 동의</label>
											<div class="col-sm-10">
												<select class="form-control" id="termNecessary" name="termNecessary">
													<option value="Y">필수</option>
													<option value="N">선택</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="card-footer">		
										<button type="submit" class="btn btn-info float-right">등록</button>
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
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery-validation -->
<script src="resources/admin/AdminLTE/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="resources/admin/AdminLTE/plugins/jquery-validation/additional-methods.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
<!-- summernote -->
<script src="resources/util/plugins/summernote/summernote-lite.js"></script>

<script src="resources/util/js/summernote.js"></script>
<script src="resources/admin/js/common.js"></script>
<script>
	$(function() {
		$('#termsRegForm').validate({
			rules: {
				termTitle: {
					required: true,
				},
				inputApplyDate: {
					convertType: true,
				},
				inputEndDate: {
					convertType: true,
				}
			},
			messages: {
				termTitle: {
					required: "제목을 입력해주세요.",
				},
				inputApplyDate: {
					convertType: "적용일자를 입력해주세요.",
				},
				inputEndDate: {
					convertType: "만료일자를 입력해주세요.",
				},
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
		
		$.validator.addMethod("convertType", function(value, element) {
			var date = new Date(value);
			
			var year = date.getFullYear().toString();
			var month = ("0" + (date.getMonth() + 1)).slice(-2);
			var day = ("0" + date.getDate()).slice(-2);
			var hour = ("0" + date.getHours()).slice(-2);
			var minute = ("0" + date.getMinutes()).slice(-2);
			var second = ("0" + date.getSeconds()).slice(-2);
			
			var timestamp = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
			$(element).prev().val(timestamp);
			
			return true;
		});
	});
</script>
</body>
</html>