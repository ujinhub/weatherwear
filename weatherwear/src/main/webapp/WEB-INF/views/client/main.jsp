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

<!-- Vendor CSS Files -->
<link href="resources/client/ZenBlog/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/client/ZenBlog/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

<!-- Swiper -->
<link href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" rel="stylesheet"/>

<!-- Template Main CSS Files -->
<link href="resources/client/ZenBlog/assets/css/main.css" rel="stylesheet">
<link href="resources/client/ZenBlog/assets/css/variables.css" rel="stylesheet">

<style>
.mb-2 { width: 210px; height: 50px; overflow: hidden}
.col-lg-3 { height: 440px; padding-left: 5%;}
.productDiv:hover { cursor: pointer; }
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="header.jsp" %>
		<main id="main">
			
			<section id="hero-slider" class="hero-slider">
				<div class="container-md aos-init aos-animate" data-aos="fade-in">
					<div class="row">
						<div class="col-12">
							<div class="swiper sliderFeaturedPosts swiper-initialized swiper-horizontal swiper-pointer-events swiper-backface-hidden">
							<div class="swiper-wrapper" id="swiper-wrapper-f11091073a160510f1f" aria-live="off" style="transition-duration: 0ms; transform: translate3d(-814px, 0px, 0px);">
								<div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="3" role="group" aria-label="4 / 4" style="width: 407px;">
									<a href="#" class="img-bg d-flex align-items-end" style="background-image: url('resources/client/images/eventbanner.png');">
									</a>
								</div>
								<div class="swiper-slide swiper-slide-prev" data-swiper-slide-index="0" role="group" aria-label="1 / 4" style="width: 407px;">
									<a href="#" class="img-bg d-flex align-items-end" style="background-image: url('resources/client/images/eventbanner.png')">
									</a>
								</div>
									
								<div class="swiper-slide swiper-slide-active" data-swiper-slide-index="1" role="group" aria-label="2 / 4" style="width: 407px;">
									<a href="#" class="img-bg d-flex align-items-end" style="background-image: url('resources/client/images/eventbanner2.png')">
									</a>
								</div>
							
							</div>
							<div class="custom-swiper-button-next" tabindex="0" role="button" aria-label="Next slide" aria-controls="swiper-wrapper-f11091073a160510f1f">
								<span class="bi-chevron-right"></span>
							</div>
							<div class="custom-swiper-button-prev" tabindex="0" role="button" aria-label="Previous slide" aria-controls="swiper-wrapper-f11091073a160510f1f">
								<span class="bi-chevron-left"></span>
							</div>
						
							<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets swiper-pagination-horizontal"><span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 1"></span><span class="swiper-pagination-bullet swiper-pagination-bullet-active" tabindex="0" role="button" aria-label="Go to slide 2" aria-current="true"></span><span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 3"></span><span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 4"></span></div>
							<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
						</div>
					</div>
				</div>
			</section>
			<section class="category-section">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="section-header d-flex justify-content-between align-items-center mb-5">
			        	<h2>BEST</h2>
			        	<div>
			        		<a href="#" class="more">MORE</a>
			        	</div>
					</div>
					<!-- 상품 한 줄 시작 -->
					<div class="row">
					<c:forEach var="item" items="${ bestItem }" varStatus="num">
						<c:if test="${ num.index%4 == 0 }">
							</div>
							<!-- 상품 한 줄 끝 -->
							<!-- 상품 한 줄 시작 -->
							<div class="row">
						</c:if>
							<!-- 상품 하나 반복 시작 -->
							<div class="col-lg-3">
								<div class="post-entry-1 border-bottom productDiv" onclick="location.href='productInfo.do?productId=${ item.productId }'">
									<img src="${ item.mainImage }" alt="mainLogo" class="img-fluid" style="width: 200px; height: 200px;">
									<div class="post-meta">
										<span class="date">
											<c:choose>
												<c:when test="${ item.productCate == 11}">OUTER</c:when>
												<c:when test="${ item.productCate == 12}">TOPS</c:when>
												<c:when test="${ item.productCate == 13}">PANTS</c:when>
												<c:when test="${ item.productCate == 14}">SKIRTS</c:when>
												<c:when test="${ item.productCate == 15}">DRESS</c:when>
											</c:choose>
										</span> 
										<span class="mx-1">•</span> 
										<span>view: ${ item.productView }</span>
										<span class="mx-1">•</span> 
										<span>buy: ${ item.productCnt }</span>
									</div>
									<h2 class="mb-2">${ item.productName }</h2>
									<span class="author mb-3 d-block"><fmt:formatNumber value="${item.productPrice}" pattern="###,###"/></span>
									<p class="mb-4 d-block">1/26일 순차배송</p>
								</div>
							</div>
							<!-- 상품 하나 반복 끝 -->
						<c:if test="${ num.index%4 == 0 }">
						</c:if>
					</c:forEach>
					</div>
					<!-- 상품 한 줄 끝 -->
				</div>
			</section>
			<section class="category-section">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="section-header d-flex justify-content-between align-items-center mb-5">
			        	<h2>NEW</h2>
			        	<div>
			        		<a href="#" class="more">MORE</a>
			        	</div>
					</div>
					<!-- 상품 한 줄 시작 -->
					<div class="row">
					<c:forEach var="item" items="${ newItem }" varStatus="num">
						<c:if test="${ num.index%4 == 0 }">
							</div>
							<!-- 상품 한 줄 끝 -->
							<!-- 상품 한 줄 시작 -->
							<div class="row">
						</c:if>
							<!-- 상품 하나 반복 시작 -->
							<div class="col-lg-3">
								<div class="post-entry-1 border-bottom productDiv" onclick="location.href='productInfo.do?productId=${ item.productId }'">
									<img src="${ item.mainImage }" alt="mainLogo" class="img-fluid" style="width: 200px; height: 200px;">
									<div class="post-meta">
										<span class="date">
											<c:choose>
												<c:when test="${ item.productCate == 11}">OUTER</c:when>
												<c:when test="${ item.productCate == 12}">TOPS</c:when>
												<c:when test="${ item.productCate == 13}">PANTS</c:when>
												<c:when test="${ item.productCate == 14}">SKIRTS</c:when>
												<c:when test="${ item.productCate == 15}">DRESS</c:when>
											</c:choose>
										</span> 
										<span class="mx-1">•</span> 
										<span>view: ${ item.productView }</span>
									</div>
									<h2 class="mb-2">${ item.productName }</h2>
									<span class="author mb-3 d-block"><fmt:formatNumber value="${item.productPrice}" pattern="###,###"/></span>
									<p class="mb-4 d-block">1/26일 순차배송</p>
								</div>
							</div>
							<!-- 상품 하나 반복 끝 -->
						<c:if test="${ num.index%4 == 0 }">
						</c:if>
					</c:forEach>
					</div>
					<!-- 상품 한 줄 끝 -->
				</div>
			</section>
		</main>
		
		<%@ include file="footer.jsp" %>
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