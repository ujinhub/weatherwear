<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header" class="header d-flex align-items-center fixed-top">
	<div class="container-fluid container-xl d-flex align-items-center justify-content-between">
		<a href="main.do" class="logo d-flex align-items-center">
<!-- 			<img src="resources/client/images/logo_pull.png" alt="mainLogo"> -->
			<h1>WeatherWear</h1>
		</a>
		<nav id="navbar" class="navbar">
			<ul>
				<li><a href="productList.do">NEW</a></li>
				<li><a href="productList.do?searchType=outer">OUTER</a></li>
				<li><a href="productList.do?searchType=tops">TOP</a></li>
				<li><a href="productList.do?searchType=pants">PANTS</a></li>
				<li><a href="productList.do?searchType=skirts">SKIRTS</a></li>
				<li><a href="productList.do?searchType=dress">DRESS</a></li>
				<li class="dropdown"><a href="noticeList.do"><span>COMMUNITY</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
					<ul>
						<li><a href="noticeList.do">NOTICE</a></li>
						<li><a href="qnaList.do">QNA</a></li>
						<li><a href="reviewList.do">REVIEW</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<!-- .navbar -->
		
		<div class="position-relative">
			<a href="#" class="mx-2 js-search-open"><span class="bi-search"></span></a>
			<c:if test="${userInfo == null}">
				<a href="login.do">Login</a>&nbsp;
				<a href="clientRegister.do">Join</a>&nbsp;
				<a href="#">Cart</a>&nbsp;
			</c:if>
			<c:if test="${userInfo != null}">
				<a href="logoutProc.do">Logout</a>&nbsp;
				<a href="mypage.do">Mypage</a>&nbsp;
				<a href="#">Cart</a>&nbsp;
			</c:if>
			<i class="bi bi-list mobile-nav-toggle"></i>
			<!-- ======= Search Form ======= -->
			<div class="search-form-wrap js-search-form-wrap">
				<form action="search-result.html" class="search-form">
					<span class="icon bi-search"></span>
					<input type="text" placeholder="Search" class="form-control">
					<button class="btn js-search-close"><span class="bi-x"></span></button>
				</form>
			</div>&nbsp;
			<!-- End Search Form -->
		</div>
	</div>
</header>

<!-- Swiper -->
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<script>
$(document).ready(function() {
 	$.ajax({
 		url: 'sessionCheck.do',
 		type: 'post',
 		async: false,
 		success: function(result) {

 		},
 		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
 		}
 	});
 	
 });
</script>
	