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
.a-before a:not(:first-child)::before { content: "\0020\007C\0020" }
.btnNaver { content: url(resources/client/images/btn_naver.png); height: 50px; }
.btnKakao { content: url(resources/client/images/btn_kakao.png); height: 50px; }
.btnGoogle { content: url(resources/client/images/btn_google.png); height: 50px; }
.mg-2 { margin: 20px 0; }
.mg-3 { margin: 30px 0 0; }
.pd-1 { padding: 10px; }
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
							<h1 class="page-title">LOGIN</h1>
						</div>
						<div class="col-lg-12 text-center mb-5">
							<div style="width: 35%; display: inline-block;">
								<form id="loginOrderForm" action="loginOrder.do?cartList=${ param.cartList }" method="post">
									<input type="text" class="form-control form-control-border border-width-2 mg-3" name="clientId" placeholder="아이디">
									<input type="password" class="form-control form-control-border border-width-2 mg-3" name="clientPwd" placeholder="비밀번호">
									<button type="submit" class="btn btn-block btn-primary pd-1 mg-2"><i class="fas fa-fingerprint"></i>&nbsp;&nbsp;로그인</button>
								</form>
								
								<div class="a-before text-center">
									<a href="findClientId.do" class="text-secondary">아이디찾기</a>
									<a href="findClientPwd.do" class="text-secondary">비밀번호찾기</a>
									<a href="clientRegister.do" class="text-secondary">회원가입</a>
								</div>
								 
								<div>
									<button type="button" class="btn btn-block btn-secondary pd-1 mg-2" onclick="location.href='orderRegister.do?cartList=${param.cartList}&cartOk=Y'"><i class="fas fa-shopping-cart"></i>&nbsp;&nbsp;비회원 주문하기</button>
								</div>
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

<!-- jQuery-validation -->
<script src="resources/admin/AdminLTE/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="resources/admin/AdminLTE/plugins/jquery-validation/additional-methods.min.js"></script>

<script>
$(function() {
	<c:if test="${msg != '' && msg != null}">
		playToast('${msg}', 'error');
	</c:if>	
	
	$('#loginOrderForm').validate({
		rules: {
			clientId: {
				required: true
			},
			clientPwd: {
				required: true
			}
		},
		messages: {
			clientId: {
				required: "아이디를 입력해주세요."
			},
			clientPwd: {
				required: "비밀번호를 입력해주세요.",
			}
		},
		errorElement: 'span',
		errorPlacement: function(error, element) {
			error.addClass('invalid-feedback');
			element.closest('.form-control').append(error);
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		}
	});
});
</script>
</body>
</html>