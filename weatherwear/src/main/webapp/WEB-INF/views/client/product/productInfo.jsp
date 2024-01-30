<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.selectOption {color: #909090; font-size: 17px;}
.optionbox { display: flex; flex-direction: column;}
.optionbox_p { width: 50%; }
.total_div {display: flex; justify-content: space-around; font-size:20px; font-weight: 300;}
.selectValue{ width:100%; display: flex; flex-direction:row;}
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		<main id="main">
			<section>
				<div class="container">
					<div class="row">
						<div class="col-md-9 post-content aos-init aos-animate" data-aos="fade-up">
							<!-- ======= Single Post Content ======= -->
							<div class="single-post border-bottom">
								<div class="post-meta">
									<input type="hidden" name="stockList" value="${ optionList }">
									<input type="hidden" name="productName" value="${ product.productName }">
									<input type="hidden" name="productId" value="${ product.productId }">
									<input type="hidden" name="optionInfo" value="${ optionInfo }">
									<input type="hidden" name="productPrice" value="${ product.productPrice }">
									<input type="hidden" name="odTotal" value="0">
									<span class="date"><a href="main.do">HOME</a></span> 
									<span class="mx-1">•</span> 
									<span><a href="#">OUTER</a></span>
								</div>
								<h1 class="mb-5">${ product.productName }</h1>
								<img src="${ product.mainImage }" alt="" class="img-fluid"><br><br>
							</div>
							<div class="single-post"><br>
								<c:forEach var="detail" items="${ detailImageList }">
									<img src="${ detail.detailImage }" alt="" class="img-fluid"><br><br>
								</c:forEach>
								<p>${ product.productContent }</p>
							</div>
							<!-- End Single Post Content -->
						</div>
						<!-- Category -->
						<div class="col-md-3">
							<div class="aside-block">
								<div class="tab-content" id="pills-tabContent">
									<!-- Popular -->
									<div class="tab-pane fade show active" id="pills-popular" role="tabpanel" aria-labelledby="pills-popular-tab">
										<div class="post-entry-1 border-bottom">
											<h2>${ product.productName }</h2>
											<h4><fmt:formatNumber value="${ product.productPrice }" pattern="###,###"/></h4>
										</div>
										<div class="post-entry-1 border-bottom">
											<!-- Color -->
											<div class="post-meta"><span class="date">Color</span></div>
											<c:forEach var="color" items="${ optionInfo.optionColorList }">
												<button type="button" id="color_${ color }" class="btn btn-outline-primary colorOption optionBtn" value="${ color }" onclick="select(this)">${ color }</button>&nbsp;
											</c:forEach>
											<input type="hidden" name="colorValue">
											<br><br>
											<!-- Size -->
											<div class="post-meta"><span class="date">Size</span></div>
											<c:forEach var="size" items="${ optionInfo.optionSizeList }">
												<button type="button" id="size_${ size }"  class="btn btn-outline-primary sizeOption optionBtn" value="${ size }" onclick="select(this)">${ size }</button>&nbsp;
											</c:forEach>
											<input type="hidden" name="sizeValue">
											<br><br>
										</div>
										<div class="post-entry-1 border-bottom optionbox" id="optionbox">
											
										</div>
										<div class="post-entry-1 border-bottom total_div">
											<span><b>Total</b></span>
											<span class="totalPrice"></span>
											<br><br>
										</div>
										<div class="post-entry-1 border-bottom">
											<button type="button" class="btn btn-outline-primary" onclick="addWishList()">WishList</button>
											<button type="button" class="btn btn-outline-primary" onclick="addCart()">Add Cart</button>
											<button type="button" class="btn btn-primary" onclick="">Buy Now</button>
											<br><br>
										</div>
									</div>
									<!-- End Popular -->
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

<script src="resources/client/js/product.js"></script>
<script src="resources/util/js/manageWishList.js"></script>
</body>
</html>