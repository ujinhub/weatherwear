<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		    font-size: 17px;
		}
		.required .col-form-label:after {
			content: "*";
			color: red;
		}
		.division::after {
		
		}
		.table td {
			vertical-align: middle;
		}
	</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">MYPAGE</h1>
						</div>
					</div>
				</div>
				
				<div class="card-body">
					<div class="row">
						<div class="col-12 col-md-12 col-lg-12 order-2 order-md-1">
							<div class="row">
								<div class="col-12 col-sm-10" style="margin: 0 auto;">
									<div class="info-box bg-light">
										<div class="info-box-content">
											<div>
												<div class="info-box-text text-muted float-left">안녕하세요,&nbsp;&nbsp;</div>
												<div class="info-box-text text-muted float-left" style="font-weight: 700;">${userInfo.clientName}</div>
												<div class="info-box-text text-muted float-left">님!&nbsp;&nbsp;</div>
												<div class="info-box-text text-muted float-left">
													<c:if test="${userInfo.gradeId == 'S'}">[Silver 회원]</c:if>
													<c:if test="${userInfo.gradeId == 'G'}">[Gold 회원]</c:if>
													<c:if test="${userInfo.gradeId == 'B'}">[Black 회원]</c:if>
												</div>
											</div>
											<div class="info-box-text text-muted mg-3">전화: ${fn:substring(userInfo.clientNum,0,3)}-${fn:substring(userInfo.clientNum,3,7)}-${fn:substring(userInfo.clientNum,7,12)}</div>
											<div class="info-box-text text-muted">이메일: ${userInfo.clientEmail}</div>
											<button class="btn btn-block btn-flat btn-sm btn-outline-secondary mg-2" onclick="location.href='editInfo.do'">정보수정</button>
										</div>
										<div class="info-box-content division">
											<div class="info-box-text text-center text-muted mg-2" style="font-size: 20px;">총 주문금액</div>
											<div class="info-box-number text-center text-muted mg-3" style="font-size: 20px;">
												<a href="myorderList.do"><fmt:formatNumber value="${totalPrice != null ? totalPrice : 0}" pattern="###,###"/></a>원
											</div>
										</div>
										<div class="info-box-content division">
											<div class="info-box-text text-center text-muted mg-2" style="font-size: 20px;">장바구니</div>
											<div class="info-box-number text-center text-muted mg-3" style="font-size: 20px;">
												<a href="cart.do"><c:out value="${cart != null ? cart : 0}" /></a>개
											</div>
										</div>
										<div class="info-box-content division">
											<div class="info-box-text text-center text-muted mg-2" style="font-size: 20px;">적립금</div>
											<div class="info-box-number text-center text-muted mg-3" style="font-size: 20px;">
												<a><fmt:formatNumber value="${point != null ? point : 0}" pattern="###,###"/></a>원
											</div>
										</div>
										<div class="info-box-content division">
											<div class="info-box-text text-center text-muted mg-2" style="font-size: 20px;">쿠폰</div>
											<div class="info-box-number text-center text-muted mg-3" style="font-size: 20px;">
												<a href="mycouponList.do"><c:out value="${coupon != null ? coupon : 0}" /></a>개
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="text-center">
					<div class="btn-group btn-group-toggle col-sm-10" data-toggle="buttons">
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="order" autocomplete="off" onclick="location.href='myorderList.do'">주문조회
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="coupon" autocomplete="off" onclick="location.href='mycouponList.do'">쿠폰
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="wish" autocomplete="off" onclick="location.href='mywishList.do'">관심상품
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="recent" autocomplete="off" onclick="location.href='myrecentList.do'">최근본상품
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="qna" autocomplete="off" onclick="location.href='myqnaList.do'">1:1문의
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="review" autocomplete="off" onclick="location.href='myreviewList.do'">리뷰
						</label>
					</div>
				</div>
			</section>
			
			<!-- 최근 주문 정보 3개월 최대 3개 -->
			<section class="contact" style="width: 80%; margin: 0 auto;">
				<div>
					<div class="card-header">
						<h3 class="card-title"><b>최근 주문 정보</b></h3>
						<div class="card-tools">
							<button type="button" class="btn btn-flat btn-sm btn-outline-dark" onclick="location.href='myorderList.do'">전체보기</button>
						</div>
					</div>
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="15%" />
								<col width="15%" />
								<col width="40%" />
								<col width="15%" />
								<col width="15%" />
							</colgroup>
							<thead>
								<tr>
									<th>주문일자</th>
									<th>사진</th>
									<th>상품정보</th>
									<th>결제금액</th>
									<th>주문처리상태</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(orderList) == 0}">
									<tr>
										<td colspan="5">주문 내역이 없습니다.</td>
									</tr>  
								</c:if>
								<c:if test="${fn:length(orderList) > 0}">
									<c:set var="endIndex" value="${fn:length(orderList) > 2 ? 1 : fn:length(orderList)}" />
									<c:forEach var="item" items="${orderList}" end="${endIndex}">
										<tr>
											<td>
												<fmt:formatDate value='${item.orderDate}' pattern='yyyy-MM-dd'/><br>
												<a href="orderInfo.do?orderId=${item.orderId}">${item.orderId}</a>
											</td>
											<td><a href="productInfo.do?productId=${item.productId}"><img src="${item.image}" style="height: 100px;"></a></td>
											<td>
												${item.productName}<br>
												[옵션: ${item.optionColor} / ${item.optionSize}]
											</td>
											<td><fmt:formatNumber value="${item.orderTotal}" pattern="###,###"/></td>
											<td>${item.orderStatus}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</section>
			
			<section class="contact"  style="width: 80%; margin: 0 auto;">
				<!-- 관심상품 최대 4개 -->
				<div class="float-left" style="width: 47%;">
					<div class="card-header">
						<h3 class="card-title"><b>관심상품</b></h3>
						<div class="card-tools">
							<button type="button" class="btn btn-flat btn-sm btn-outline-dark" onclick="location.href='mywishList.do'">전체보기</button>
						</div>
					</div>
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="25%" />
								<col width="75%" />
							</colgroup>
							<tbody>
								<c:if test="${fn:length(wishList) > 0}">
									<c:set var="endIndex" value="${fn:length(wishList) > 4 ? 3 : fn:length(wishList)}" />
									<c:forEach var="item" items="${wishList}" end="${endIndex}">
										<tr class="text-left">
											<td><a href="productInfo.do?productId=${ item.productId }"><img src="${item.image}" style="height: 100px;"></a></td>
											<td>${item.productName}<br><b><fmt:formatNumber value="${item.productPrice}" pattern="###,###"/>원</b>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			
				<!-- 최근본상품 최대 4개 -->
				<div class="float-left" style="width: 47%; margin-left: 6%">
					<div class="card-header">
						<h3 class="card-title"><b>최근본상품</b></h3>
						<div class="card-tools">
							<button type="button" class="btn btn-flat btn-sm btn-outline-dark" onclick="location.href='myrecentList.do'">전체보기</button>
						</div>
					</div>
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="25%" />
								<col width="75%" />
							</colgroup>
							<tbody>
								<c:if test="${fn:length(recentList) > 0}">
									<c:set var="endIndex" value="${fn:length(recentList) > 4 ? 3 : fn:length(recentList)}" />
									<c:forEach var="item" items="${recentList}" end="${endIndex}">
										<tr class="text-left">
											<td><a href="productInfo.do?productId=${ item.productId }"><img src="${item.image}" style="height: 100px;"></a></td>
											<td>${item.productName}<br><b><fmt:formatNumber value="${item.productPrice}" pattern="###,###"/>원</b>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
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
	
	<script src="resources/util/js/paging.js"></script>
</body>
</html>