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
a:not(:first-child)::before { content: "\0020\007C\0020" }
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
							<h1>비회원 주문조회</h1>
						</div>
						<div class="col-lg-12 text-center mb-5">
							<div style="width: 35%; display: inline-block;">
								<form action="noClientOrderProc.do" method="post">
									<input type="text" class="form-control form-control-border border-width-2 mg-2" name="orderName" placeholder="주문자명">
									<input type="text" class="form-control form-control-border border-width-2 mg-2" name="orderNum" placeholder="주문번호">
									<input type="password" class="form-control form-control-border border-width-2 mg-2" name="orderPwd" placeholder="비회원주문 비밀번호">
									<button type="submit" class="btn btn-block btn-primary pd-1"><i class="fas fa-truck"></i>&nbsp;&nbsp;비회원 배송조회</button>
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

</body>
</html>