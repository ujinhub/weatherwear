<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
.mg-2 { margin: 20px 0; }
.mg-3 { margin: 30px 0 0; }
.pd-1 { padding: 10px; }
.description {
    color: #6c757d;
    font-size: 13px;
    vertical-align: middle;
}
.required .col-form-label:after {
	content: "*";
	color: red;
}
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact mb-5">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">JOIN</h1>
						</div>
						<div style="width: 70%; margin: 0 auto;">
							<div >
								<form class="form-horizontal" id="clientRegForm" action="clientRegProc.do" method="post">
									<div class="card card-default">
										<div class="card-header">
											<h3 class="card-title"><b>기본 정보</b></h3>
											<div class="card-tools">
												<button type="button" class="btn btn-tool" data-card-widget="collapse">
													<i class="fas fa-minus"></i>
												</button>
											</div>
										</div>
									
										<div class="card-body">
											<div class="form-group row required">
												<label for="clientId" class="col-sm-2 col-form-label">아이디</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="clientId" name="clientId">
												</div>
												<div class="col-sm-3" style="display: table-cell; vertical-align: middle;">
													<span class="description">영문소문자/숫자, 4~16자</span>
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
												<label for="clientName" class="col-sm-2 col-form-label">이름</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="clientName" name="clientName">
												</div>
											</div>
											<div class="form-group row required">
												<label for="clientEmail" class="col-sm-2 col-form-label">이메일</label>
												<div class="col-sm-7">
													<input type="email" class="form-control" id="clientEmail" name="clientEmail">
												</div>
											</div>
											<div class="form-group row required">
												<label for="clientNum" class="col-sm-2 col-form-label">휴대전화</label>
												<input type="hidden" class="form-control" id="clientNum" name="clientNum">
												<div class="col-sm-2">
													<select class="form-control" id="clientNum1">
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
													<input type="text" class="form-control" id="clientNum2" name="clientNum2" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
												&nbsp;-&nbsp;
												<div class="col-sm-2">
													<input type="text" class="form-control" id="clientNum3" name="clientNum3" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
											</div>
											<div class="form-group row">
												<label for="clientBirth" class="col-sm-2 col-form-label">생년월일</label>
												<input type="hidden" name="clientBirth">
												<div class="col-sm-2">
													<input type="text" class="form-control" id="clientYear" name="clientYear" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
												<div class="col-sm-auto" style="margin: 0; padding: 0;">
													<span class="description">년</span>
												</div>
												<div class="col-sm-2">
													<input type="text" class="form-control" id="clientMonth" name="clientMonth" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
												
												<div class="col-sm-auto" style="margin: 0; padding: 0;">
													<span class="description">월</span>
												</div>
												<div class="col-sm-2">
													<input type="text" class="form-control" id="clientDay" name="clientDay" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
												</div>
												<div class="col-sm-auto" style="margin: 0; padding: 0;">
													<span class="description">일</span>
												</div>
											</div>
											<!-- div class="form-group row">
												<label for="client" class="col-sm-2 col-form-label">주소</label>
												<div class="col-sm-7">
													<input type="email" class="form-control" id="clientEmail" name="clientEmail">
												</div>
											</div -->
										</div>							
									</div>
									
									<div class="card card-default">
										<div class="card-header">
											<h3 class="card-title"><b>약관 동의</b></h3>
											<div class="card-tools">
												<input type="checkbox" class="form-check-input" id="checkAll"><label for="checkAll" class="form-check-label">약관 전체 동의</label>
												<button type="button" class="btn btn-tool" data-card-widget="collapse">
													<i class="fas fa-minus"></i>
												</button>
											</div>
										</div>
										
										<div class="card-body">
											<div id="accordion">
												<c:forEach var="item" items="${termList}">
													<c:if test="${item.termNecessary == 'Y'}">
														<div class="card card-danger card-outline">
															<div class="card-header">
																<h5 class="card-title">
																	<a class="d-block w-100" data-toggle="collapse" href="#${item.termId}">
																		[필수] ${item.termTitle}
																	</a>
																</h5>
																<div class="card-tools">
																	<input type="checkbox" class="form-check-input" name="checkTerms" id="chk${item.termId}" value="${item.termNecessary}"><label for="chk${item.termId}" class="form-check-label">동의함</label>
																</div>
															</div>
															
															<div id="${item.termId}" class="collapse" data-parent="#accordion">
																<div class="card-body" style="overflow-y: scroll; height: 200px;">
																	${item.termContent}
																</div>
															</div>
														</div>
													</c:if>
													
													<c:if test="${item.termNecessary == 'N'}">
														<div class="card card-warning card-outline">
															<div class="card-header">
																<h5 class="card-title">
																	<a class="d-block w-100" data-toggle="collapse" href="#${item.termId}">
																		[선택] ${item.termTitle}
																	</a>
																</h5>
																<div class="card-tools">
																	<input type="checkbox" class="form-check-input" name="checkTerms" id="chk${item.termId}" value="${item.termNecessary}"><label for="chk${item.termId}" class="form-check-label">동의함</label>
																</div>
															</div>
															
															<div id="${item.termId}" class="collapse" data-parent="#accordion">
																<div class="card-body" style="overflow-y: scroll; height: 200px;">
																	${item.termContent}
																</div>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
									<button type="submit" class="btn btn-block btn-primary pd-1 mg-2">회원가입</button>
								</form>			
							</div>
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
$(function() {
	$('#clientRegForm').validate({
		rules: {
			clientId: {
				required: true,
				minlength: 4,
				maxlength: 16,
				isEqualId: true
			},
			clientPwd: {
				required: true,
				minlength: 8,
				maxlength: 16
			},
			clientPwdCheck: {
				required: true,
				equalTo: "#clientPwd"
			},
			clientName: {
				required: true
			},
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
			clientNum: {
				required: function() {
					$('#clientNum').val($('#clientNum1').val() + $('#clientNum2').val() + $('#clientNum3').val());
					if($('#clientNum').val().length == 11) { true; }
				}
			},
			checkTerms: {
				required: function() {
					alert($('input:checkbox[name="checkTerms"]').length);
						alert('a');
						if($(this).val() == 'Y') {
							alert('b');
							true;
						}
						else {
							alert('c');
							false;
						}
				}
			}
		},
		messages: {
			clientId: {
				required: "",
				minlength: "아이디는 4자 이상 16자 이하 입니다.",
				maxlength: "아이디는 4자 이상 16자 이하 입니다.",
				isEqualId: "동일한 아이디가 존재합니다. 다른 아이디를 입력해주세요."
			},
			clientPwd: {
				required: "",
				minlength: "비밀번호는 8자 이상 16자 이하 입니다.",
				maxlength: "비밀번호는 8자 이상 16자 이하 입니다."
			},
			clientPwdCheck: {
				required: "",
				equalTo: "비밀번호가 일치하지 않습니다."
			},
			clientName: {
				required: "",
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
			clientNum: {
				required: "휴대폰번호를 입력해주세요."
			},
			checkTerms: {
				required: "필수 항목을 체크해주세요."
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
	
	// 아이디 중복 체크
	$.validator.addMethod("isEqualId", function(value, element) {
		var res = false;
		$.ajax({
			url: "clientCheck.do",
			type: "post",
			dataType:'json',
			async: false,
			data: {
				clientId: $('#clientId').val(),
				chkType: 'clientId'
			},
			success: function(result) {
				res = result;
			}
		});
		return res;
	});
	
	$('#checkAll').on('click', function() {
		if($("#checkAll").is(":checked")) $("input[name=checkTerms]").prop("checked", true);
		else $("input[name=checkTerms]").prop("checked", false);
	});
	
	$("input[type=checkbox]").click(function() {
		var total = $("input[name=checkTerms]").length;
		var checked = $("input[name=checkTerms]:checked").length;
		
		if(total != checked) $("#checkAll").prop("checked", false);
		else $("#checkAll").prop("checked", true); 
	});
});
</script>
</body>
</html>