<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<style>
.description { color: #6c757d; font-size: 13px; vertical-align: middle; }
.mg-2 { margin: 20px 0; }
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact mb-5">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row justify-content-center mt-5">
						<div class="col-lg-11">
							<c:if test="${checkPwd == null}">
								<div class="col-lg-12 text-center mb-5">
									<h1 class="comment-title">비밀번호 재확인</h1>
								</div>
								<form id="editInfoForm" action="editInfo.do" method="post">
									<div class="card" style="margin: 0 auto; width: 60%;">
										<div class="card-body col-lg-7" style="margin: 0 auto;">
											<div class="form-group row">
												<label for="clientName" class="col-sm-3 col-form-label">아이디</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="clientId" name="clientId" value="${userInfo.clientId}" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label for="clientPwd" class="col-sm-3 col-form-label">비밀번호</label>
												<div class="col-sm-7">
													<input type="password" class="form-control" id="clientPwd" name="clientPwd" required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="mg-2">
										<div class="row justify-content-center">
											<div class="col-sm-2">
												<button type="submit" class="btn btn-block btn-dark">확인</button>
											</div>
											<div class="col-sm-2">
												<button type="button" class="btn btn-block btn-outline-dark" onclick="history.go(-1)">취소</button>
											</div>
										</div>
									</div>
								</form>
							</c:if>
							
							<c:if test="${checkPwd != null}">
								<div class="col-lg-12 text-center mb-5">
									<h1 class="comment-title">회원정보수정</h1>
								</div>
								<form id="editInfoForm" action="editInfoProc.do" method="post">
									<div class="row">
										<div class="php-email-form" style="width: 70%; margin: 0 auto;">
											<div class="card-body">
												<div class="form-group row required">
													<label for="clientName" class="col-sm-2 col-form-label">이름</label>
													<div class="col-sm-7">
														<input type="text" class="form-control" id="clientName" name="clientName" value="${info.clientName}" readonly>
													</div>
												</div>
												<div class="form-group row required">
													<label for="clientId" class="col-sm-2 col-form-label">아이디</label>
													<div class="col-sm-7">
														<input type="text" class="form-control" id="clientId" name="clientId" value="${info.clientId}" readonly>
													</div>
												</div>
												<div class="form-group row required">
													<label for="clientPwd" class="col-sm-2 col-form-label">비밀번호</label>
													<div class="col-sm-7">
														<input type="password" class="form-control" id="clientPwd" name="clientPwd">
													</div>
													<div class="col-sm-3" style="display: table-cell; vertical-align: middle;">
														<span class="description">영문 대소문자/숫자, 8자~16자</span>
													</div>
												</div>
												<div class="form-group row required">
													<label for="clientPwdCheck" class="col-sm-2 col-form-label">비밀번호 확인</label>
													<div class="col-sm-7">
														<input type="password" class="form-control" id="clientPwdCheck" name="clientPwdCheck">
													</div>
												</div>
												<div class="form-group row required">
													<label for="clientEmail" class="col-sm-2 col-form-label">이메일</label>
													<div class="col-sm-7">
														<input type="email" class="form-control" id="clientEmail" name="clientEmail" value="${info.clientEmail}">
													</div>
												</div>
												<div class="form-group row required">
													<label for="clientNum" class="col-sm-2 col-form-label">휴대전화</label>
													<input type="hidden" class="form-control" id="clientNum" name="clientNum">
													<div class="col-sm-2">
														<select class="form-control" id="clientNum1">
								                        	<option value="010" <c:if test="${fn:substring(info.clientNum,0,3) == '010'}">selected</c:if>>010</option>
									                        <option value="011" <c:if test="${fn:substring(info.clientNum,0,3) == '011'}">selected</c:if>>011</option>
									                        <option value="016" <c:if test="${fn:substring(info.clientNum,0,3) == '016'}">selected</c:if>>016</option>
									                        <option value="017" <c:if test="${fn:substring(info.clientNum,0,3) == '017'}">selected</c:if>>017</option>
									                        <option value="018" <c:if test="${fn:substring(info.clientNum,0,3) == '018'}">selected</c:if>>018</option>
									                        <option value="019" <c:if test="${fn:substring(info.clientNum,0,3) == '019'}">selected</c:if>>019</option>
								                        </select>
													</div>
													&nbsp;-&nbsp;
													<div class="col-sm-2">
														<input type="text" class="form-control" id="clientNum2" name="clientNum2" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${fn:substring(info.clientNum,3,7)}">
													</div>
													&nbsp;-&nbsp;
													<div class="col-sm-2">
														<input type="text" class="form-control" id="clientNum3" name="clientNum3" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${fn:substring(info.clientNum,7,12)}">
													</div>
												</div>
												<div class="form-group row">
													<label for="clientBirth" class="col-sm-2 col-form-label">생년월일</label>
													<input type="hidden" name="clientBirth" id="clientBirth">
													<c:set var="birth" value="${fn:replace(info.clientBirth, '-', '')}" />
													<div class="col-sm-2">
														<input type="text" class="form-control" id="clientYear" name="clientYear" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${fn:substring(birth,0,4)}">
													</div>
													<div class="col-sm-auto" style="margin: 0; padding: 0;">
														<span class="description">년</span>
													</div>
													<div class="col-sm-2">
														<input type="text" class="form-control" id="clientMonth" name="clientMonth" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${fn:substring(birth,4,6)}">
													</div>
													
													<div class="col-sm-auto" style="margin: 0; padding: 0;">
														<span class="description">월</span>
													</div>
													<div class="col-sm-2">
														<input type="text" class="form-control" id="clientDay" name="clientDay" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${fn:substring(birth,6,8)}">
													</div>
													<div class="col-sm-auto" style="margin: 0; padding: 0;">
														<span class="description">일</span>
													</div>
												</div>
												<div class="text-right">
													<button type="button" class="btn btn-flat btn-sm btn-outline-dark" id="btnWithdraw">회원탈퇴</button>
												</div>
											</div>							
										</div>
									</div>
									<div class="mg-2">
										<div class="row justify-content-center">
											<div class="col-sm-2">
												<button type="submit" class="btn btn-block btn-dark">확인</button>
											</div>
											<div class="col-sm-2">
												<button type="button" class="btn btn-block btn-outline-dark" onclick="history.go(-1)">취소</button>
											</div>
										</div>
									</div>
								</form>
							</c:if>
						
						</div>
					</div>
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
	
<script>
$(document).ready(function() {
	<c:if test="${msg != null && msg != ''}">
		playToast('${msg}', 'error');
		<% session.removeAttribute("msg"); %>
	</c:if>	
	
	$('#btnWithdraw').on('click', function() {
		if(confirm('탈퇴할 경우 회원관련 데이터가 복구되지 않습니다.\n정말로 탈퇴를 하시겠습니까?')) {
			location.href = "clientWithdraw.do?clientId=" + $('#clientId').val();
		}
	});
	
	$('#editInfoForm').validate({
		rules: {
			clientEmail: {
				required: true
			},
			clientNum1: {
				required: true
			},
			clientNum2: {
				required: true,
				minlength: 4
			},
			clientNum3: {
				required: true,
				minlength: 4
			},
		},
		messages: {
			clientPwd: {
				required: "비밀번호를 입력해주세요."
			},
			clientEmail: {
				required: "",
			},
			clientNum1: {
				required: "",
			},
			clientNum2: {
				required: "",
                minlength: "휴대폰번호는 4자 입력해주세요.",
			},
			clientNum3: {
				required: "",
                minlength: "휴대폰번호는 4자 입력해주세요.",
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
		},
		submitHandler: function(form) {
			$('#clientNum').val($('#clientNum1').val() + $('#clientNum2').val() + $('#clientNum3').val());
			$('#clientBirth').val($('#clientYear').val() + $('#clientMonth').val().padStart(2, "0") + $('#clientDay').val().padStart(2, "0"));

			form.submit();
		}
	}); 
});
</script>
</body>
</html>