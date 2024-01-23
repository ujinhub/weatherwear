<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeatherWear 관리자</title>

<!-- Font Awesome -->
<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
	
		<div class="content-header">
			<section class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>관리자 추가</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item"><a href="adminList.mdo">관리자 목록</a></li>
								<li class="breadcrumb-item active">관리자 추가</li>
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
										<h3 class="card-title">관리자 정보 등록</h3>
									</div>
								</div>
								<form class="form-horizontal" id="adminRegForm" action="adminRegProc.mdo" method="post">
									<div class="card-body">
										<div class="form-group row">
											<label for="adminId" class="col-sm-2 col-form-control">아이디</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminId" name="adminId" placeholder="아이디를 입력하세요">
											</div>
										</div>
										<div class="form-group row">
											<label for="adminName" class="col-sm-2 col-form-control">이름</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminName" name="adminName" placeholder="이름을 입력하세요">
											</div>
										</div>
										<div class="form-group row">
											<label for="adminPwd" class="col-sm-2 col-form-control">비밀번호</label>
											<div class="col-sm-9">
												<input type="password" class="form-control" id="adminPwd" name="adminPwd" placeholder="비밀번호를 입력하세요">
											</div>
										</div>
										<div class="form-group row">
											<label for="adminPwdChk" class="col-sm-2 col-form-control">비밀번호 확인</label>
											<div class="col-sm-9">
												<input type="password" class="form-control" id="adminPwdChk" name="adminPwdChk" placeholder="비밀번호 확인">
											</div>
										</div>
										<div class="form-group row">
											<label for="adminNum" class="col-sm-2 col-form-control">전화번호</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminNum" name="adminNum" placeholder="전화번호를 입력하세요" maxlength="13" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, '$1-$2-$3')">
											</div>
										</div>
										<div class="form-group row">
											<label for="gradeId" class="col-sm-2 col-form-control">관리자 등급</label>
											<div class="col-sm-9">
												<select class="form-control" id="gradeId" name="gradeId">
													<option value="manager">매니저</option>
													<option value="admin">최고 관리자</option>
												</select>
											</div>
										</div>
									</div>
								
									<div class="card-footer">		
										<button type="submit" class="btn btn-info float-right">등록</button>
	                 					<button type="button" class="btn btn-default float-right">취소</button>
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

<script src="resources/admin/js/common.js"></script>
<script>
$(function() {
	$('#adminRegForm').validate({
		rules: {
			adminId: {
				required: true,
				minlength: 2,
				isEqualId: true
			},
			adminName: {
				required: true,
				minlength: 2
			},
			adminPwd: {
				required: true,
				minlength: 8
			},
			adminPwdChk: {
				required: true,
				minlength: 8,
				equalTo: "#adminPwd"
			},
			adminNum: {
				required: true,
				minlength: 13
			},
		},
		messages: {
			adminId: {
				required: "아이디를 입력해주세요.",
				minlength: "최소 2글자 이상이어야 합니다.",
				isEqualId: "동일한 아이디가 존재합니다. 다른 아이디를 입력해주세요."
			},
			adminName: {
				required: "이름을 입력해주세요.",
				minlength: "최소 2글자 이상이어야 합니다."
			},
			adminPwd: {
				required: "비밀번호를 입력해주세요.",
				minlength: "비밀번호는 8자리 이상이어야 합니다."
			},
			adminPwdChk: {
				required: "비밀번호 확인을 입력해주세요.",
				minlength: "비밀번호는 8자리 이상이어야 합니다.",
				equalTo: "비밀번호가 일치하지 않습니다."
			},
			adminNum: {
				required: "전화번호를 입력해주세요.",
				minlength: "전화번호를 확인해주세요."
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
		},
	});
	
	// 아이디 중복 체크
	$.validator.addMethod("isEqualId", function(value, element) {
		var res = false;
		$.ajax({
			url: "adminCheck.mdo",
			type: "post",
			dataType:'json',
			async: false,
			data: {
				adminId: $('#adminId').val(),
				chkType: 'adminId'
			},
			success: function(result) {
				res = result;
			}
		});
		return res;
	});
});
</script>
</body>
</html>