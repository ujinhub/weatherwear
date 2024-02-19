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

<link rel="stylesheet" href="/w2/resources/client/css/main.css">
<script>
$(document).ready(function() {
	let weatherId = ${ weatherList.get('day3').get('weather_id') };
	image = '';
	
	selectWeather(weatherId);
	
	<c:if test="${withdrawMsg != null && withdrawMsg != ''}">
		location.href = "logoutProc.do"
		alert("${withdrawMsg}");
	</c:if>
	
});
</script>
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
				<div id="weather">
					<article id="province">
						<ul class="w_province">
						<li><a id="prov" onClick="setProvince('seoul')">서울</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('chuncheon')">춘천</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('suwon')">수원</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('incheon')">인천</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('daegu')">대구</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('busan')">부산</a></li>
						<li> | </li>
						<li><a id="prov" onClick="setProvince('jeju')">제주</a></li>
						</ul>
						<br>
					</article>
					<article id="today">
						<span> </span><span> </span>
						<span id="today_day">
							<span id="provSelect">
								<c:if test="${ param.province == 'seoul' || param.province == null }">서울</c:if> 
								<c:if test="${ param.province == 'chuncheon' }">춘천</c:if> 
								<c:if test="${ param.province == 'suwon' }">수원</c:if> 
								<c:if test="${ param.province == 'incheon' }">인천</c:if> 
								<c:if test="${ param.province == 'daegu' }">대구</c:if> 
								<c:if test="${ param.province == 'busan' }">부산</c:if> 
								<c:if test="${ param.province == 'jeju' }">제주</c:if> 
								&nbsp;&nbsp;&nbsp;
							</span>
							${ weatherList.get('day3').get('weatherday') }
						</span><br>
						<span id="today_date"> ${ weatherList.get('day3').get('weatherdate') } </span>
					</article>
					<span id="today_temp"><strong> ${ weatherList.get('day3').get('temp_min') } °C / ${ weatherList.get('day3').get('temp_max') } °C</strong></span>
					<article id="week">
						<ul id = "weekTable">
							<li class="weekTable_li">
								<div class="weekTable_li_day">
									${ weatherList.get('day1').get('weatherday') }
								</div>
								<div class="weekTable_li_temp">
									${ weatherList.get('day1').get('temp_min') } / ${ weatherList.get('day3').get('temp_max') }
								</div>
							</li>
							<li class="weekTable_li">
								<div class="weekTable_li_day">
									${ weatherList.get('day2').get('weatherday') }
								</div>
								<div class="weekTable_li_temp">
									${ weatherList.get('day2').get('temp_min') } / ${ weatherList.get('day3').get('temp_max') }
								</div>
							</li>
							<li class="weekTable_li">
								<div class="weekTable_li_day">
									${ weatherList.get('day3').get('weatherday') }
								</div>
								<div class="weekTable_li_temp">
									${ weatherList.get('day3').get('temp_min') } / ${ weatherList.get('day3').get('temp_max') }
								</div>
							</li>
							<li class="weekTable_li">
								<div class="weekTable_li_day">
									${ weatherList.get('day4').get('weatherday') }
								</div>
								<div class="weekTable_li_temp">
									${ weatherList.get('day4').get('temp_min') } / ${ weatherList.get('day3').get('temp_max') }
								</div>
							</li>
							<li class="weekTable_li">
								<div class="weekTable_li_day">
									${ weatherList.get('day5').get('weatherday') }
								</div>
								<div class="weekTable_li_temp">
									${ weatherList.get('day5').get('temp_min') } / ${ weatherList.get('day3').get('temp_max') }
								</div>
							</li>
						</ul>
					</article>
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
<script src="resources/client/js/main.js"></script>

</body>
</html>