<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 	<script src="/w2/resources/client/client_js/client_community.js"></script> -->
	<script src="/w2/resources/client/client_js/client_cart.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_cart_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="cartList">
				<h2>CART</h2>
				<div class="cart_table">
					<c:if test="${cartList eq null || fn:length(cartList) == 0}">
						<h4>장바구니가 비어 있습니다.</h4>
					</c:if>
					<c:if test="${cartList ne null && fn:length(cartList) > 0}">
						<ul class="cart_header">
							<li class="cart_head no">
								<input type="checkbox" id="checkAll" name="checkAll">
								<label for="checkAll"></label>
							</li>
							<li class="cart_head con">PRODUCT</li>
							<li class="cart_head count">COUNT</li>
							<li class="cart_head price">PRICE</li>
						</ul>
	<!-- 상품마다 반복 시작 -->
						<c:forEach var="item" items="${cartList}" varStatus="status">
							<ul class="cart_content checklist">
								<li class="cart_con no">
									<input type="hidden" id="caId_${item.opId}" value="${item.caId}" class="caId">
									<input type="checkbox" name="checkbox" id="${status.count}" class="check" value="${item.opId}">
									<label for="${status.count}"></label>
								</li>
								<li class="cart_con product">
<!-- 상품상세로 이동 -->			<img class="product_image" src="${item.imageDir}${item.imageName}" onclick="location.href='clientProductInfo.do?proId=${item.proId}'">
								</li>
								<li class="cart_con content">
									<span class="order_product_name">
										<a href="clientProductInfo.do?proId=${item.proId}" class="community_a">${item.proName}</a>
									</span>
									<span class="order_product_option">
										${item.opColor} / ${item.opSize}
										<input type="hidden" name="opQty_${item.opId}" value="${item.stCnt}">
									</span>
								</li>
								<c:if test="${item.stCnt == 0}">
									<li class="cart_con count">
										<h4>품절</h4>	
									</li>
									<li class="cart_con count">
										<div name="price_${item.opId}" class="proAmount">
											<span>0</span>
										</div>
									</li>
								</c:if>
								<c:if test="${item.stCnt > 0}">
									<li class="cart_con count">
										<div name="cnt_${item.opId}" class="cartValue">
											<button type="button" class="pro_btn" onclick="count(2,'${item.opId}')">-</button>
											<input class="pro_btn" id="odTotal" name="odTotal" value="${item.caCnt}">
											<button type="button" class="pro_btn" onclick="count(1,'${item.opId}')">+</button>
											<input type="hidden" name="opId" value="${item.opId}">
										</div>
									</li>
									<li class="cart_con price">
										<div name="price_${item.opId}" class="proAmount">
											<span class="pro_totalPrice"></span>
											<input type="hidden" id="proPrice" name="proPrice" value="${item.proPrice}">
										</div>
									</li>
								</c:if>
							</ul>
		<!-- 상품마다 반복 끝 -->
						</c:forEach>
		<!-- 삭제 버튼 -->
						<div class="cart_btn_div delete_btn">
							<input type="button" id="addWishList" class="cart_btn" value="관심상품으로 이동">
							<input type="button" id="deleteSelected" class="cart_btn" value="선택 삭제" onclick="deleteSelect()">
							<input type="button" id="deleteAll" class="cart_btn" value="전체 삭제" onclick="deleteAll()">
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
						</div>
					</c:if>
					<!-- 주문 버튼 -->
					<div class="cart_btn_div order_btn">
						<input type="button" id="orderSelected" class="cart_btn" value="선택 상품 주문">
						<input type="button" id="orderAll" class="cart_btn" value="전체 상품 주문">
					</div>
				</div>
			</div>				
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>
