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
							<h1>관리자 정보</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item"><a href="adminList.mdo">관리자 목록</a></li>
								<li class="breadcrumb-item active">관리자 정보</li>
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
										<h3 class="card-title">관리자 상세 정보</h3>
									</div>
								</div>
								<form class="form-horizontal" id="adminUpdateForm" action="adminUpdateProc.mdo" method="post">
									<div class="card-body">
										<div class="form-group row">
											<label for="adminId" class="col-sm-2 col-form-control">아이디</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminId" name="adminId" value="${info.adminId}" readonly>
											</div>
										</div>
										<div class="form-group row">
											<label for="adminName" class="col-sm-2 col-form-control">이름</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminName" name="adminName" value="${info.adminName}" readonly>
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
												<c:set var="adminNum" value="${info.adminNum}"/>
												<input type="text" class="form-control" id="adminNum" name="adminNum" value="${fn:substring(adminNum,0,3)}-${fn:substring(adminNum,3,7)}-${fn:substring(adminNum,7,12)}" maxlength="13" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, '$1-$2-$3')">
											</div>
										</div>
										<div class="form-group row">
											<label for="gradeId" class="col-sm-2 col-form-control">관리자 등급</label>
											<div class="col-sm-9">
												<select class="form-control" id="gradeId" name="gradeId">
													<option value="manager" <c:if test="${info.gradeId == 'manager'}">selected="selected"</c:if>>매니저</option>
													<c:if test="${userInfo.gradeId == 'admin'}">
														<option value="admin" <c:if test="${info.gradeId == 'admin'}">selected="selected"</c:if>>최고 관리자</option>
													</c:if>
												</select>
											</div>
										</div>
									</div>
									
									<div class="card-footer">
										<c:if test="${(userInfo.gradeId != 'admin' and userInfo.adminId == info.adminId) or (userInfo.gradeId == 'admin')}">
											<button type="button" id="btnDelete" class="btn btn-danger float-left">삭제</button>
											<button type="button" id="btnUpdate" class="btn btn-info float-right">수정</button>
	                 						<button type="reset" class="btn btn-default float-right">취소</button>
										</c:if>
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
	$('#adminUpdateForm').validate({
		rules: {
			adminName: {
				required: true,
				minlength: 2
			},
			adminPwd: {
				required: function() {
					if("${userInfo.gradeId}" != 'admin') {
						return true;
					}
					return false;
				},
				minlength: 8
			},
			adminPwdChk: {
				required: function() {
					if("${userInfo.gradeId}" != 'admin') {
						return true;
					}
					return false;
				},
				minlength: 8,
				equalTo: "#adminPwd"
			},
			adminNum: {
				required: true,
				minlength: 13
			},
		},
		messages: {
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
	
	$('#btnDelete').on('click', function() {
		if(confirm('삭제하시겠습니까?')) {
			if('${userInfo.gradeId}' != 'admin') {
				if($('#adminUpdateForm').valid()) {
					$.ajax({
						url: "adminCheck.mdo",
						type: "post",
						dataType: "json",
						async: false,
						data: {
							adminId: $('#adminId').val(),
							adminPwd: $('#adminPwd').val(),
							chkType: 'adminPwd'
						},
						success: function(data) {
							if(data == false) {
								alert('비밀번호가 일치하지 않습니다.');
							} else {
								location.href = "adminDeleteProc.mdo?adminId=" + $('#adminId').val();
							}
						},
						error: function(request, status, error) {
							console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
						}
					});
				}
			} else {
				location.href = "adminDeleteProc.mdo?adminId=" + $('#adminId').val();
			}
		}
	});
	
	$('#btnUpdate').on('click', function() {
		if($('#adminUpdateForm').valid()) {
			if(confirm('수정하시겠습니까?')) {
				if('${userInfo.gradeId}' != 'admin') {
					$.ajax({
						url: "adminCheck.mdo",
						type: "post",
						dataType: "json",
						async: false,
						data: {
							adminId: $('#adminId').val(),
							adminPwd: $('#adminPwd').val(),
							chkType: 'adminPwd'
						},
						success: function(result) {
							var newInput = '<input type="hidden" name="changePwd" value="'+ result + '">';
							$('#adminUpdateForm').append(newInput);
							$('#adminUpdateForm').submit();
						},
						error: function(request, status, error) {
							console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
						}
					});
				} else {
					$('#adminUpdateForm').submit();
				}
			}
		}
	});
});
</script>
</body>
</html>