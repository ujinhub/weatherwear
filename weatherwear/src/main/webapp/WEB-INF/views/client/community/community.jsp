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
							<h1 class="page-title">COMMUNITY</h1>
						</div>
					</div>
				</div>
				
				<div class="col-12 col-sm-9" style="margin: 0 auto;">
					<div class="card card-secondary card-outline card-outline-tabs">
						<div class="card-header p-0">
							<ul class="nav nav-tabs text-center" id="tabs-tab" role="tablist">
								<li class="nav-item col-sm-4">
									<a class="nav-link active" id="notice-tab" data-toggle="pill" href="#notice" role="tab" aria-controls="notice" aria-selected="true">Notice</a>
								</li>
								<li class="nav-item col-sm-4">
									<a class="nav-link" id="qna-tab" data-toggle="pill" href="#qna" role="tab" aria-controls="qna" aria-selected="false">QnA</a>
								</li>
								<li class="nav-item col-sm-4">
									<a class="nav-link" id="review-tab" data-toggle="pill" href="#review" role="tab" aria-controls="review" aria-selected="false">Review</a>
								</li>
							</ul>
						</div>
						<div class="card-body">
							<div class="tab-content" id="tabContent">
								<div class="tab-pane fade show active" id="notice" role="tabpanel" aria-labelledby="notice-tab">
									<c:import url="/noticeList.do"></c:import>
								</div>
								<div class="tab-pane fade" id="qna" role="tabpanel" aria-labelledby="qna-tab">
									<c:import url="/qnaList.do"></c:import>
								</div>
								<div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
									<c:import url="/reviewList.do"></c:import>
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
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
</body>
</html>