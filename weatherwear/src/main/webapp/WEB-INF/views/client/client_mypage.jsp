<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/w2/resources/util/util_js/util_checkbox.js"></script>
	<script src="/w2/resources/client/client_js/client_mypage.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_mypage_style.css">
</head>
<body>
	<c:if test="${ session == null }">
		<script>
			alert("잘못된 접근입니다. 메인페이지로 이동합니다.");
			location.href='clientMain.do';
		</script>
	</c:if>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="mypage_body">		
	<!-- 개인정보 변경 -->
				<div class="submenu">
					<ul class="submenu_ul">
						<li id="modify_btn" class="submenu_li">개인 정보 관리</li>
					</ul>
				</div>	
				<ul class="mypage_menu">
					<li id="order_btn" class="mypage_menu_head">
						ORDER<br>
						<font size="2">상세보기</font>
					</li>
					<li id="wish_btn" class="mypage_menu_head">
						WISHLIST<br>
						<font size="2">${ myinfo['wish'] }</font>
					</li>
					<li id="coupon_btn" class="mypage_menu_head">
						COUPON<br>
						<font size="2">${ myinfo['coupon'] }</font>
					</li>
					<li id="point_btn" class="mypage_menu_head">
						POINT<br>
						<font size="2"><fmt:formatNumber value="${ myinfo['point'] }" pattern="###,###" /></font>
					</li>
					<li id="review_btn" class="mypage_menu_head">
						REVIEW<br>
						<font size="2">${ myinfo['review'] }</font>
					</li>
				</ul>
	<!-- order -->
				<div class="orderList">
					<h2>order</h2>
					<div class="order_table">
		<!-- 주문번호마다 반복 시작 -->
						<div class="order_idlist">
							<div class="order_cate">
								<section class="order_date">2023-12-01</section>
								<section class="order_id">OD202312011212</section>
								<section class="order_detail">
									<a class="orderdetail_btn">주문 상세</a>
								</section>
								<section class="order_status">배송완료</section>
							</div>
							<div class="clear_con"></div>
			<!-- 주문 상품따라 반복 시작 -->
							<ul class="order_product">
								<li class="order_con product_img">
									상품 이미지
								</li>
								<li class="order_con order_product_info">
									<span class="mypage_proName">상품 이름</span>
									<span class="mypage_proOption">상품 옵션</span>
								</li>
							</ul>
							<ul class="order_product_mystatus">
								<li class="product_con"><input type="button" value="리뷰쓰기"></li>
								<li class="product_con"><input type="button" value="교환요청"></li>
								<li class="product_con"><input type="button" value="환불요청"></li>
								<li class="product_con"><input type="button" value="재구매"></li>
							</ul>
			<!-- 주문 상품따라 반복 끝 -->
			<!-- 주문 상세 시작 -->
							<div class="orderDetail_div">
								<div class="orderDetail">
									<!-- 배송정보 -->
									<div class="deliver_info">
										<b>배송 정보</b>
										<div class="deliver_table">
											<ul class="deliver_header">
												<li class="deliver_head person">수령인</li>
												<li class="deliver_head number">연락처</li>
												<li class="deliver_head address">배송지</li>
												<li class="deliver_head usedeliver">배송 메모</li>
											</ul>
											<ul class="deliver_content">
												<li class="deliver_con person">김아무개</li>
												<li class="deliver_con number">010-1234-1234</li>
												<li class="deliver_con address">서울시 종로3가 단성사</li>
												<li class="deliver_con usedeliver">문 앞에 보관바랍니다.</li>
											</ul>
										</div>
									</div><br>
									<!-- 주문정보 -->
									<div class="order_info">
										<b>주문 정보</b>
										<div class="order_info_table">
											<ul class="order_info_header">
												<li class="order_info_head oriPrice">최초 주문 금액</li>
												<li class="order_info_head proPrice">상품 금액</li>
												<li class="order_info_head deliPrice">배송비</li>
												<li class="order_info_head cpPrice">쿠폰 할인</li>
											</ul>
											<ul class="order_info_content">
												<li class="order_info_con oriPrice">총 금액</li>
												<li class="order_info_con proPrice">상품 금액</li>
												<li class="order_info_con deliPrice">배송비</li>
												<li class="order_info_con cpPrice">쿠폰할인액</li>
											</ul>
										</div>
									</div><br>
									<!-- 결제정보 -->
									<div class="pay_info_info">
										<b>결제 정보</b>
										<div class="pay_info_table">
											<ul class="pay_info_header">
												<li class="pay_info_head usedPoint">포인트 사용</li>
												<li class="pay_info_head payMtd">결제 방식</li>
												<li class="pay_info_head points">적립 예정 포인트</li>
											</ul>
											<ul class="pay_info_content">
												<li class="pay_info_con usedPoint">3,000 원</li>
												<li class="pay_info_con payMtd">카드</li>
												<li class="pay_info_con points">1,500 원</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
			<!-- 주문 상세 끝 -->
						</div>
		<!-- 주문번호마다 반복 끝 -->
					</div>
				</div>	
	<!-- wish -->
				<div class="wishList">
					<h2>WISH LIST</h2>
					<div class="wish_table checklist">
						<ul class="wish_header">
							<li class="wish_head checkbox">
								<input type="checkbox" id="checkAll" name="checkAll">
								<label for="checkAll"></label>
							</li>
							<li class="wish_head wishProduct">
								<span>PRODUCT</span>
								<input type="button" value="선택 삭제">
							</li>
						</ul>
						<ul class="wish_content">
							<li class="wish_con checkbox">
								<input type="checkbox" value="상품id" id="check01" class="check">
								<label for="check01"></label>
							</li>
							<li class="wish_con product_img">
								상품 이미지
							</li>
							<li class="wish_con product_name">
								상품 이름
							</li>
						</ul>
					</div>
				</div>	
	<!-- coupon -->
				<div class="couponList">
					<h2>COUPON LIST</h2>
					<div class="coupon_table">
						<ul class="coupon_header">
							<li class="coupon_head no">NO</li>
							<li class="coupon_head coupon">COUPON</li>
							<li class="coupon_head status">STATUS</li>
							<li class="coupon_head dudate">DATE</li>
						</ul>
						<ul class="coupon_content">
							<li class="coupon_con no">1</li>
							<li class="coupon_con coupon">신규 가입 쿠폰</li>
							<li class="coupon_con status">사용가능</li>
							<li class="coupon_con dudate">2023-12-01 - 2023-12-10</li>
						</ul>
					</div>
				</div>
	<!-- point -->
				<div class="pointList">
					<h2>POINT</h2>
					<div class="point_table">
						<ul class="point_header">
							<li class="point_head date">DATE</li>
							<li class="point_head history">HISTORY</li>
							<li class="point_head getPoint">GET</li>
							<li class="point_head usePoint">USE</li>
							<li class="point_head total">TOTAL</li>
						</ul>
						<ul class="point_content">
							<li class="point_con date">2023-12-01</li>
							<li class="point_con history">구매 사용</li>
							<li class="point_con getPoint">0</li>
							<li class="point_con usePoint">- 1,000</li>
							<li class="point_con total">3,000</li>
						</ul>
					</div>
				</div>
	<!-- review -->
				<div class="reviewList">
					<h2>REVIEW</h2>
					<div class="review_table">
		<!-- 주문번호마다 반복 시작 -->
						<div class="review_idlist">
							<div class="review_cate">
								<section class="review_id">OD202312011212</section>
							</div>
							<div class="clear_con"></div>
			<!-- 주문 상품따라 반복 시작 -->
							<ul class="review_product">
								<li class="review_con product_img">
									상품 이미지
								</li>
								<li class="review_con review_product_info">
									<span class="mypage_proName">상품 이름</span>
									<span class="mypage_proOption">상품 옵션</span>
									<span class="review_content">리뷰내용입니다.</span>
								</li>
							</ul>
							<ul class="review_product_mystatus">
								<li class="product_con"><input type="button" value="리뷰 삭제" onclick="location.href='#'"></li>
							</ul>
			<!-- 주문 상품따라 반복 끝 -->
						</div>
		<!-- 주문번호마다 반복 끝 -->
					</div>
				</div>
	<!-- 개인 정보 관리 비밀번호 확인 -->
				<div class="chechPwd">
					
				</div>	
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>