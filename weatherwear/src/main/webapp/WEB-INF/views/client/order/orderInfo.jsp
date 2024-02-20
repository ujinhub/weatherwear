<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<!-- dropZone -->
<link rel="stylesheet" href="resources/util/plugins/dropzone/dist/dropzone.min.css"/>
<link rel="stylesheet" href="resources/util/plugins/dropzone/custom.css">
<link rel="stylesheet" href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css" type="text/css" />

<!-- Swiper -->
<link href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" rel="stylesheet"/>

<!-- Template Main CSS Files -->
<link href="resources/client/ZenBlog/assets/css/main.css" rel="stylesheet">
<link href="resources/client/ZenBlog/assets/css/variables.css" rel="stylesheet">

<!-- 주소 검색 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
	.resultDiv{ height: 55px; font-size: larger !important; display: flex; align-items: center;}
	.inputLabel{ font-size: larger !important; display: flex; align-items: center; justify-content: center;}
	.resultInput{ font-size: larger !important; display: flex; align-items: center; justify-content: flex-end;}
	.selectOption{ border-radius:0; text-align: center; font-size: large;}
	.check {width:15px; height:15px;}
	.payBtn { width:100%; height:70px; font-size: x-large; background-color: black; color: white; border-radius: 0;}
	.deliDiv { display: flex; justify-content: space-between;}
	.confirmDiv {border-bottom:1px solid silver; margin-bottom:10px;}
	.info {font-size: large;}
	.imagePreviewDiv {
		padding: 5px 5px 5px 5px;
		button {display:none; position:relative;}
		&:hover {button{display:block;}}
	}
	#dropzone { display: flex; flex-direction: row; flex-wrap: wrap; justify-content: center;}
	.previewImg { display: flex; flex-direction: column; align-content: center; justify-content: center;}
	.custom-radio { display:flex; justify-content: space-evenly;}
</style>
<link href="resources/client/css/review.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		<main id="main">
			<section id="contact" class="contact mb-5">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<c:choose>
						<c:when test="${ userInfo.clientId != orderInfo.clientId || cookieId != orderInfo.cookieId }">
						<div style="height:800px;"></div>
						<script>
							$(document).ready(function(){
								playToast("잘못된 접근입니다. 메인 페이지로 이동합니다.", "error");
								setTimeout(function(){
									location.href="main.do";
								}, 1000);
							})
						</script>
						</c:when>
					<c:otherwise>
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">Order Info</h1>
						</div>
					</div>
					<!-- Modal -->
					<div class="modal fade show" id="modal-default" style="display: none; padding-right: 17px;" aria-modal="true" role="dialog">
					</div>
					<div class="row gy-4">
						<div class="col-md-6">
							<div class="form mt-5">
								<div class="php-email-form">
									<div class="form-group">
										<h3>주문자</h3>
									</div>
									<div class="form-group">
										<span class="info">주문자 : ${ orderInfo.clientName }</span>
									</div>
									<div class="form-group">
										<span class="info">이메일 : ${ orderInfo.orderEmail }</span>
									</div>
									<div class="form-group">
										<span class="info">연락처 : ${fn:substring(orderInfo.clientNum,0,3)}-${fn:substring(orderInfo.clientNum,3,7)}-${fn:substring(orderInfo.clientNum,7,12)}</span>
									</div>
								</div><br>
								<div class="php-email-form">
									<div class="form-group">
										<h3>배송지</h3>
									</div>
									<div class="form-group">
										<span class="info">받는 사람 : ${ orderInfo.addressName }</span>
									</div>
									<div class="form-group">
										<span class="info">우편번호 : ${ orderInfo.addressPostNum }</span>
									</div>
									<div class="form-group">
										<span class="info">기본주소 : ${ orderInfo.address1 }</span>
									</div>
									<div class="form-group">
										<span class="info">상세주소 : ${ orderInfo.address2 }</span>
									</div>
									<div class="form-group">
										<span class="info">연락처 : ${fn:substring(orderInfo.clientNum,0,3)}-${fn:substring(orderInfo.clientNum,3,7)}-${fn:substring(orderInfo.clientNum,7,12)}</span>
									</div>
									<div class="form-group" id="deliMsgDiv">
										<span class="info">배송메모 : ${ orderInfo.addressMemo }</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form mt-5">
								<div class="php-email-form">
									<div class="form-group">
										<h3>주문 상품</h3>
									</div>
									<c:forEach items="${ orderProductList }" var="pro">
				<!-- 상품마다 반복 시작 -->
									<div class="form-group" class="productDiv" id="${ pro.orderId }_${ pro.optionId }">
										<div class="row">
											<div class="col-md-4">
												<img class="product_image" src="${ pro.mainImage }" style="height:150px; width:150px;">
											</div>
											<div class="col-md-8">
												<h5>${ pro.productName }</h5>
												<span class="info">
													${ pro.optionColor } / ${ pro.optionSize } [수량: ${ pro.orderProCnt }]<br>
													<fmt:formatNumber pattern="#,###,###" value="${ pro.orderTotal }" /><br>
													주문상태 : ${ pro.orderStatus }
													<input type="hidden" name="orderStatus_${ pro.optionId }" value="${ pro.orderStatus }">
												</span><br>
												<c:choose>
													<c:when test="${ pro.orderStatus=='상품준비중' || pro.orderStatus=='배송준비중' || pro.orderStatus=='배송보류' || pro.orderStatus=='배송대기'}">
														<div class="row">
															<div class="col-md-4">
																<input type="button" value="취소요청" class="form-control" class="cancleBtn" id="cancleBtn" onclick="cancleOrder('${ pro.orderId }_${ pro.optionId }')">
															</div>
														</div>
													</c:when>
													<c:when test="${ pro.orderStatus=='배송중' || pro.orderStatus=='배송완료'}">
														<div class="row">
															<c:choose>
																<c:when test="${ pro.reviewId == null }">
																	<div class="col-md-4">
																		<input type="button" value="교환요청" class="form-control" class="swapBtn" id="swapBtn" onclick="swapOrder('${ pro.orderId }_${ pro.optionId }')">
																	</div>
																	<div class="col-md-4">
																		<input type="button" value="환불요청" class="form-control" class="refundBtn" id="refundBtn" onclick="refundOrder('${ pro.orderId }_${ pro.optionId }')">
																	</div>
																	<div class="col-md-4">
																		<input type="button" value="리뷰작성" class="form-control" class="reviewWriteBtn" id="reviewWriteBtn" onclick="reviewWrite('${pro.productId}', '${pro.orderId}', '${pro.optionId}', '${pro.productName}', '${pro.mainImage}', '${pro.orderProCnt}', '${pro.optionColor}', '${pro.optionSize}' )">
																	</div>
																</c:when>
																<c:when test="${ pro.reviewId != null && pro.reviewStatus != '삭제'}">
																	<div class="col-md-4">
																		<input type="button" value="리뷰보기" class="form-control" class="reviewWriteBtn" id="reviewViewBtn" onclick="reviewView('${ pro.reviewId }', '${pro.productName}', '${pro.mainImage}', '${pro.orderProCnt}', '${pro.optionColor}', '${pro.optionSize}' )">
																	</div>
																</c:when>
															</c:choose>
														</div>
													</c:when>
													<c:when test="${ pro.orderStatus=='교환중' || pro.orderStatus=='교환완료'}"></c:when>
													<c:when test="${ pro.orderStatus=='환불중' || pro.orderStatus=='환불완료'}"></c:when>
													<c:when test="${ pro.orderStatus=='취소요청' || pro.orderStatus=='취소완료'}"></c:when>
												</c:choose>
											</div>
										</div>
									</div>
				<!-- 상품마다 반복 끝 -->
									</c:forEach>
								</div><br>
			<c:if test="${ userInfo != null }">
								<div class="php-email-form">
									<div class="form-group">
										<h3>할인</h3>
									</div>
									<div class="row">
										<div class="form-group">
											<span class="info">포인트 : <fmt:formatNumber pattern="#,###,###" value="${ orderInfo.usedPoint }"/></span>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<span class="info">쿠폰 : 
											<c:if test="${ orderInfo.couponId != null }">
											${ orderInfo.couponName } [ <fmt:formatNumber pattern="#,###,###" value="${ orderInfo.couponPrice }"/> ]
											</c:if></span>
										</div>
									</div>
									<div class="row resultDiv" style="background-color:#F6F6F6;">
										<div class="form-group col-md-4 resultDiv">
											적용금액
										</div>
										<div class="form-group col-md-6 resultDiv"></div>
										<div class="form-group col-md-2 resultDiv" id="discountPrice"><fmt:formatNumber pattern="#,###,###" value="${ orderInfo.usedPoint + orderInfo.couponPrice }"/> </div>
									</div>
								</div>
				</c:if>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form mt-5">
							<div class="php-email-form">
								<div class="form-group">
									<h3>결제 정보</h3>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										결제 일자
									</div>
									<div class="form-group col-md-5"></div>
									<div class="form-group col-md-3 resultInput" id="orderDate"><fmt:formatDate pattern="YYYY-mm-DD HH:MM:ss" value="${ orderInfo.orderDate }"/></div>
									<div class="form-group col-md-1"></div>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										결제 방법
									</div>
									<div class="form-group col-md-5"></div>
									<div class="form-group col-md-3 resultInput" id="paymentMethod">${ orderInfo.paymentMethod }</div>
									<div class="form-group col-md-1"><input type="hidden" value="${ orderInfo.paymentMethod }" id="paymentMethodInput"></div>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										결제 상태
									</div>
									<div class="form-group col-md-5"></div>
									<div class="form-group col-md-3 resultInput" id="paymentStatus">${ orderInfo.paymentStatus }</div>
									<div class="form-group col-md-1"></div>
								</div>
								<div class="row resultDiv" style="background-color:#F4F7FF;">
									<div class="form-group col-md-1 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv">
										결제 금액
									</div>
									<div class="form-group col-md-7 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv" id="totalPayPrice"><fmt:formatNumber pattern="#,###,###" value="${ orderInfo.orderPrice }"/></div>
								</div>
							</div>
						</div>
					</div>
					</c:otherwise>
				</c:choose>
				</div>
			</section>
		</main>
		
		<%@ include file="../footer.jsp" %>
	</div>
	<div class="modal-backdrop fade" style="display:none"></div>

<script src="resources/client/ZenBlog/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script  src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>

<!-- Template Main JS File -->
<script src="resources/client/ZenBlog/assets/js/main.js"></script>
<!-- sweetAlert (alert/confirm/toast) -->
<script src="resources/util/js/sweetalert.js"></script>
<!-- PortOne SDK -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script src="resources/util/js/modal.js"></script>
<!-- dropzone -->
<script src="resources/util/plugins/dropzone/dist/dropzone.min.js"></script>

<script src="resources/client/js/post.js"></script>
<script src="resources/client/js/orderInfo.js"></script>

<!-- 포스팅 - 이미지/동영상 dropzone 영역 -->
<div id="dropzone-preview" class="row" style="display:flex;">
	<div class="row imagePreviewDiv"style="width: 50%;">
		<div class="h-auto rounded-3 previewImg">
			<img data-dz-thumbnail="data-dz-thumbnail" class="w-full h-auto rounded-3 block" src="#" alt="Dropzone-Image"/>
			<button data-dz-remove="data-dz-remove" class="btn btn-sm btn-danger deleteBtn">삭제</button>
		</div>
	</div>
</div>
</body>
</html>