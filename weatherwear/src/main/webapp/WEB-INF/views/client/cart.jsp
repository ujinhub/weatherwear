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

<link rel="stylesheet" href="/w2/resources/client/css/cart.css">
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
			<section id="contact" class="contact mb-5">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">CART</h1>
						</div>
					</div>
					<div class="row">
						<div class="cartList">
							<div class="cart_table">
								<ul class="cart_header">
									<li class="cart_head no">
										<button type="button" class="btn btn-sm btn-outline-primary checkAll" id="checkAll">전체선택</button>
									</li>
									<li class="cart_head con">PRODUCT</li>
									<li class="cart_head count">COUNT</li>
									<li class="cart_head price">PRICE</li>
								</ul>
								<c:forEach var="pro" items="${ cartList }">
			<!-- 상품마다 반복 시작 -->
								<ul class="cart_content checklist">
									<li class="cart_con no">
										<input type="checkbox" id="checkbox_${ pro.cartId }" value="${ pro.cartId }" class="check">
										<input type="hidden" name="productId" value="${ pro.productId }" id="cart_${ pro.cartId }">
									</li>
									<li class="cart_con con">
										<div class="product_info_div">
										<section class="product_image_section">
											<img class="product_image" src="${ pro.product.mainImage }" onclick="location.href='productInfo.do?productId=${ pro.productId }'">
										</section>
										<section class="product_content_section">
											<span class="order_product_name">
												<a href="productInfo.do?productId=${ pro.productId }" class="community_a">
													${ pro.product.productName }
												</a>
											</span>
											<span class="order_product_option">
												${ pro.option.optionColor } / ${ pro.option.optionSize }
											</span>
										</section>
										</div>
									</li>
									<li class="cart_con count">
										<div id="div_${ pro.cartId }" class="cartValue">
											<button type="button" class="pro_btn" onclick="count(-2,'${ pro.cartId }')">-</button>
											<input class="pro_btn" name="${ pro.cartId }" value="${ pro.cartCnt }" onChange="count(this.value, '${ pro.cartId }')">
											<button type="button" class="pro_btn" onclick="count(-1,'${ pro.cartId }')">+</button>
											<input type="hidden" name="oriPrice_${ pro.cartId }" value="${ pro.product.productPrice }">
										</div>
									</li>
									<li class="cart_con price">
										<div id="price_${ pro.cartId }">
							<!-- 상품가격*수량 합계 출력 -->
											<span id="totalPrice_${ pro.cartId }" class="pro_totalPrice"></span>
							<!-- 상품가격*수량 합계 -->
											<input type="hidden" name="totalPrice_${ pro.cartId }">
										</div>
									</li>
								</ul>
			<!-- 상품마다 반복 끝 -->
								</c:forEach>
		
			<!-- 삭제 버튼 -->
								<div class="cart_btn_div delete_btn">
									<input type="button" id="addWishList" class="cart_btn" value="관심상품으로 이동" onclick="moveWishList()">
									<input type="button" id="deleteSelected" class="cart_btn" value="선택 삭제" onclick="deleteSelect('delete')">
									<input type="button" id="deleteAll" class="cart_btn" value="전체 삭제" onclick="deleteAll()">
								</div>
							</div>
							<div class="cart_table_option">
			<!-- 금액 -->
								<div class="cart_total">
									<ul class="cart_total_ul">
										<li class="cart_total_li"><span>총 상품금액</span></li>
										<li class="cart_total_li val">
											<span id="totalPrice"></span>
											<input type="hidden" name="totalPrice">
										</li>
									</ul>
									<ul class="cart_total_ul buho">
										<li class="cart_total_buho">+</li>
									</ul>
									<ul class="cart_total_ul">
										<li class="cart_total_li"><span>총 배송비</span></li>
										<li class="cart_total_li val">
											<span id="deliPrice"></span>
											<input type="hidden" name="deliPrice">
										</li>
									</ul>
									<ul class="cart_total_ul buho">
										<li class="cart_total_buho">=</li>
									</ul>
									<ul class="cart_total_ul">
										<li class="cart_total_li"><span>결제 예정 금액</span></li>
										<li class="cart_total_li val">
											<span id="endPrice"></span>
											<input type="hidden" name="endPrice">
										</li>
									</ul>
								</div>
			<!-- 주문 버튼 -->
								<div class="cart_btn_div order_btn">
									<input type="button" id="orderSelected" class="cart_btn" value="선택 상품 주문" onclick="orderSelect()">
									<input type="button" id="orderAll" class="cart_btn" value="전체 상품 주문" onclick="orderAll()">
								</div>
							</div>
						</div>	
					</div>
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

<script src="resources/client/js/cart.js"></script>
<script src="resources/util/js/manageWishList.js"></script>
</body>
</html>