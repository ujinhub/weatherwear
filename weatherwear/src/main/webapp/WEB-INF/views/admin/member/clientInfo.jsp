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
							<h1>회원 정보</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item"><a href="clientList.mdo">회원 목록</a></li>
								<li class="breadcrumb-item active">회원 정보</li>
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
										<h3 class="card-title">회원 상세 정보</h3>
									</div>
								</div>
								<form class="form-horizontal" id="clientDeleteForm" action="clientDeleteProc.mdo" method="post">
									<div class="card-body">
										<div class="form-group row">
											<label for="clientId" class="col-sm-2 col-form-control">아이디</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="adminId" name="clientId" value="${info.clientId}" readonly>
											</div>
										</div>
										<div class="form-group row">
											<label for="clientName" class="col-sm-2 col-form-control">이름</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientName" name="clientName" value="${info.clientName}">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientNum" class="col-sm-2 col-form-control">전화번호</label>
											<div class="col-sm-9">
												<c:set var="clientNum" value="${info.clientNum}"/>
												<input type="text" class="form-control" id="clientNum" name="clientNum" value="${fn:substring(clientNum,0,3)}-${fn:substring(clientNum,3,7)}-${fn:substring(clientNum,7,12)}" 
														maxlength="13" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, '$1-$2-$3')">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientBirth" class="col-sm-2 col-form-control">생년월일</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientBirth" name="clientBirth" value="${info.clientBirth}">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientEmail" class="col-sm-2 col-form-control">이메일</label>
											<div class="col-sm-7">
												<input type="email" class="form-control" id="clientEmail" name="clientEmail" value="${info.clientEmail}">
											</div>
											<div class="form-check">
												<input type="checkbox" id="clientEmailCheck" class="form-check-input" <c:if test="${info.clientEmailCheck == 'Y'}">checked</c:if>>
												<label for="clientEmailCheck" class="form-check-label">수신 동의</label>
											</div>
										</div>
										<div class="form-group row">
											<label for="gradeId" class="col-sm-2 col-form-control">회원 등급</label>
											<div class="col-sm-9">
												<select class="form-control" id="gradeId" name="gradeId">
													<option value="S" <c:if test="${info.gradeId == 'S'}">selected="selected"</c:if>>S</option>
													<option value="G" <c:if test="${info.gradeId == 'G'}">selected="selected"</c:if>>G</option>
													<option value="B" <c:if test="${info.gradeId == 'B'}">selected="selected"</c:if>>B</option>
												</select>
											</div>
										</div>
										<div class="form-group row">
											<label for="clientPoint" class="col-sm-2 col-form-control">포인트</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientPoint" name="clientPoint" value="${info.clientPoint}">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientBuyCnt" class="col-sm-2 col-form-control">누적 구매 금액</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientBuyCnt" name="clientBuyCnt" value="${info.clientBuyCnt}">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientRegDate" class="col-sm-2 col-form-control">가입일 정보</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientRegDate" name="clientRegDate" value="${info.clientRegDate}">
											</div>
										</div>
										<div class="form-group row">
											<label for="clientLogDate" class="col-sm-2 col-form-control">최근 로그인 정보</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="clientLogDate" name="clientLogDate" value="${info.clientLogDate}">
											</div>
										</div>
									</div>
									
									<div class="card-footer">
										<button type="button" id="btnDelete" class="btn btn-danger float-left">삭제</button>
										<button type="submit" id="btnUpdate" class="btn btn-info float-right">수정</button>
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
						data: {
							adminId: $('#adminId').val(),
							adminPwd: $('#adminPwd').val(),
							type: 'adminPwd'
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
		if(confirm('수정하시겠습니까?')) {
			if($('#adminUpdateForm').valid()) {
				$.ajax({
					url: "adminCheck.mdo",
					type: "post",
					data: {
						adminId: $('#adminId').val(),
						adminPwd: $('#adminPwd').val(),
						type: 'adminPwd'
					},
					success: function(result) {
						alert(result);
						/*$.ajax({
							url: "adminUpdateProcs.mdo",
							type: "post",
							dataType: "json",
							data: {
								adminId: $('#adminId').val(),
								adminPwd: $('#adminPwd').val(),
								changePwd: 'true'
							},
							success: function(data) {
								location.href = data;
							},
							error: function(request, status, error) {
								console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
							}
						});*/
					},
					error: function(request, status, error) {
						console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
					}
				});
			}
		}
	});
});
</script>
</body>
</html>