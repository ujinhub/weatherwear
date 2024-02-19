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
	.rowDivForm {height: 700px;}
	.payBtn { width:100%; height:70px; font-size: x-large; background-color: black; color: white; border-radius: 0;}
	.deliDiv { display: flex; justify-content: space-between;}
	.confirmDiv {border-bottom:1px solid silver; margin-bottom:10px;}
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
							<h1 class="page-title">ORDER</h1>
						</div>
					</div>
					<div class="row gy-4">
						<div class="col-md-6">
							<div class="form mt-5">
								<div class="php-email-form rowDivForm">
									<div class="form-group">
										<h3>주문자</h3>
									</div>
									<div class="form-group">
										<input type="text" name="clientName" class="form-control" id="clientName" placeholder="이름" required <c:if test="${ userInfo != null }">value="${ userInfo.clientName }"</c:if>>
										<input type="hidden" id="clientId" <c:if test="${ userInfo != null }">value="${ userInfo.clientId }"</c:if>>
									</div>
									<div class="form-group">
										<input type="email" class="form-control" name="clientEmail" id="clientEmail" placeholder="이메일" required <c:if test="${ userInfo != null }">value="${ userInfo.clientEmail }"</c:if>>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="clientNum" id="clientNum" required placeholder="연락처" maxlength="13" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, '$1-$2-$3')" <c:if test="${ userInfo != null }">value="${fn:substring(userInfo.clientNum,0,3)}-${fn:substring(userInfo.clientNum,3,7)}-${fn:substring(userInfo.clientNum,7,12)}"</c:if>>
									</div>
									<c:if test="${ userInfo == null }">
										<div class="form-group"><br>
											<hr>
											<h3>비회원 주문조회 비밀번호</h3><br>
										</div>
										<div class="form-group">
											<input type="password" class="form-control" name="cookiePwd" id="cookiePwd" placeholder="비회원 비밀번호" >
										</div>
										<div class="form-group" id="checkPwd">
											<input type="password" class="form-control" name="cookiePwdCheck" id="cookiePwdCheck" placeholder="비회원 비밀번호 확인" onchange="checkPwd(this)">
										</div>
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form mt-5">
								<div class="php-email-form rowDivForm">
									<div class="form-group">
										<h3>배송지</h3>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="form-group col-md-8">
												<div class="custom-control custom-radio">
													<input class="custom-control-input custom-control-input-danger" type="radio" id="sameInfo" name="addressInfo">
													<label for="sameInfo" class="custom-control-label">주문자 정보와 동일</label>&nbsp;&nbsp;&nbsp;
											<c:if test="${ userInfo != null }">
													<input class="custom-control-input custom-control-input-danger" type="radio" id="newInfo" name="addressInfo">
													<label for="newInfo" class="custom-control-label">새로운 배송지</label>
											</c:if>
												</div>
											</div>
											<c:if test="${ userInfo != null }">
											<div class="form-group col-md-4">
												<input type="button" class="form-control" value="배송지목록" id="addressListBtn" onclick="getAddressList()">
											</div>
											</c:if>
										</div>
									</div>
							<c:if test="${ userInfo != null }">
									<div class="form-group">
										<input type="text" name="addressTitle" class="form-control" id="addressTitle" placeholder="배송지 이름" required <c:if test="${ userInfo != null }">value="${ baseAddress.addressTitle }"</c:if>>
									</div>
							</c:if>	
									<div class="form-group">
										<input type="text" name="addressName" class="form-control" id="addressName" placeholder="받는 사람" required <c:if test="${ userInfo != null }">value="${ baseAddress.addressName }"</c:if>>
										<input type="hidden" name="addressId" class="form-control" id="addressId" <c:if test="${ userInfo != null }">value="${ baseAddress.addressId }"</c:if>>
									</div>
									<div class="row">
										<div class="form-group col-md-6">
											<input type="text" name="addressPostNum" class="form-control" id="addressPostNum" placeholder="우편번호" required disabled <c:if test="${ userInfo != null }">value="${ baseAddress.addressPostNum }"</c:if>>
										</div>
										<div class="form-group col-md-6">
											<input type="button" class="form-control" value="주소검색" id="findAddressBtn" onclick="daumPost()">
										</div>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="address1" id="address1" placeholder="기본주소" required disabled <c:if test="${ userInfo != null }">value="${ baseAddress.address1 }"</c:if>>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="address2" id="address2" required placeholder="상세주소" <c:if test="${ userInfo != null }">value="${ baseAddress.address2 }"</c:if>>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="addressNum" id="addressNum" required placeholder="연락처" maxlength="13" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, '$1-$2-$3')" <c:if test="${ userInfo != null }">value="${fn:substring(userInfo.clientNum,0,3)}-${fn:substring(userInfo.clientNum,3,7)}-${fn:substring(userInfo.clientNum,7,12)}"</c:if>>
									</div>
									<div class="form-group" id="deliMsgDiv">
										<select class="form-control selectOption" id="deliveryMessage" name="deliveryMessage" onchange="setMessage()">
											<option value=""  <c:if test="${ userInfo == null }">selected="selected"</c:if>>---------- 배송메시지 선택 (선택사항) ----------</option>
											<option value="배송 전에 미리 연락바랍니다." <c:if test="${ userInfo != null && baseAddress.addressMemo eq '배송 전에 미리 연락바랍니다.'}">selected="selected"</c:if>>배송 전에 미리 연락바랍니다.</option>
											<option value="부재 시 경비실에 맡겨주세요." <c:if test="${ userInfo != null && baseAddress.addressMemo eq '부재 시 경비실에 맡겨주세요.'}">selected="selected"</c:if>>부재 시 경비실에 맡겨주세요.</option>
											<option value="부재 시 문 앞에 놓아주세요." <c:if test="${ userInfo != null && baseAddress.addressMemo eq '부재 시 문 앞에 놓아주세요.'}">selected="selected"</c:if>>부재 시 문 앞에 놓아주세요.</option>
											<option value="빠른 배송 부탁드립니다." <c:if test="${ userInfo != null && baseAddress.addressMemo eq '빠른 배송 부탁드립니다.'}">selected="selected"</c:if>>빠른 배송 부탁드립니다.</option>
											<option value="택배함에 보관해 주세요." <c:if test="${ userInfo != null && baseAddress.addressMemo eq '택배함에 보관해 주세요.'}">selected="selected"</c:if>>택배함에 보관해 주세요.</option>
											<option value="inputmessage" <c:if test="${ userInfo!=null&&baseAddress.addressMemo!=null&&baseAddress.addressMemo!='배송 전에 미리 연락바랍니다.'&&baseAddress.addressMemo!='부재 시 경비실에 맡겨주세요.'&& baseAddress.addressMemo!='부재 시 문 앞에 놓아주세요.'&&baseAddress.addressMemo!='빠른 배송 부탁드립니다.'&&baseAddress.addressMemo!='택배함에 보관해 주세요.'}">selected="selected"</c:if>>직접 입력</option>
										</select>
										<div class="form-group" id="deliDiv">
											<c:if test="${ userInfo!=null&&baseAddress.addressMemo!=null&&baseAddress.addressMemo!='배송 전에 미리 연락바랍니다.'&&baseAddress.addressMemo!='부재 시 경비실에 맡겨주세요.'&& baseAddress.addressMemo!='부재 시 문 앞에 놓아주세요.'&&baseAddress.addressMemo!='빠른 배송 부탁드립니다.'&&baseAddress.addressMemo!='택배함에 보관해 주세요.'}">
												<input type='text' class='form-control' name='deliMsg' id='deliMsg' required value="${ baseAddress.addressMemo }">
											</c:if>
										</div>
									</div>
							<c:if test="${ userInfo != null }">
									<div class="form-group">
										<input type="checkbox" <c:if test="${ userInfo != null }">value="Y"</c:if><c:if test="${ userInfo == null }">value="N"</c:if> class="check" name="baseAddress" id="baseAddress">
										<label for="baseAddress" class="custom-control-label">기본 배송지로 등록</label>
									</div>
							</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form mt-5">
							<div class="php-email-form">
								<div class="form-group">
									<h3>주문 상품</h3>
								</div>
								<c:forEach items="${ orderList }" var="pro">
			<!-- 상품마다 반복 시작 -->
								<div class="form-group" class="productDiv">
									<div class="row gy-4">
										<div class="col-md-2">
											<img class="product_image" src="${ pro.product.mainImage }" style="height:150px; width:150px;">
										</div>
										<div class="col-md-9">
											<h4>${ pro.product.productName }</h4>
											<h5>${ pro.option.optionColor } / ${ pro.option.optionSize }</h5>
											<h5>수량 : ${ pro.cartCnt }</h5><br>
											<h4 class="productPrice"><fmt:formatNumber pattern="#,###,###" value="${ pro.product.productPrice * pro.cartCnt }" /></h4>
											<input type="hidden" name="productPrice" class="productPriceInput" value="${ pro.product.productPrice * pro.cartCnt }">
											<input type="hidden" name="cartCnt" class="cartCnt" value="${ pro.cartCnt }">
											<input type="hidden" name="cartId" class="cartId" value="${ pro.cartId }">
											<input type="hidden" name="optionId" class="optionId" value="${ pro.optionId }">
										</div>
									</div>
								</div>
			<!-- 상품마다 반복 끝 -->
								</c:forEach>
								<div class="row resultDiv" style="background-color:#F6F6F6;">
									<div class="form-group col-md-1 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv">
										배송비<input type="hidden" name="deliveryPrice" id="deliveryPrice">	
									</div>
									<div class="form-group col-md-7 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv" id="deliveryPriceDiv"></div>
								</div><!-- End DeliveryPrice -->
							</div>
						</div>
					</div>
			<c:if test="${ userInfo != null }">
				<!-- 할인(회원) -->
					<div class="row">
						<div class="form mt-5">
							<div class="php-email-form">
								<div class="form-group">
									<h3>할인</h3>
								</div>
								<div class="row">
									<div class="form-group col-md-2 inputLabel">
										포인트 [ <fmt:formatNumber pattern="#,###,###" value="${ userInfo.clientPoint }"/> ]
									</div>
									<div class="form-group col-md-8">
										<input type="number" class="form-control" name="usedPoint" id="usedPoint" value="0">
										<input type="hidden" class="form-control" name="clientPoint" id="clientPoint" value=${ userInfo.clientPoint }>
									</div>
									<div class="form-group col-md-2">
										<input type="button" class="form-control" value="전체사용" id="applyAllPoint">
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-2 inputLabel">
										쿠폰
									</div>
									<div class="form-group col-md-10">
										<select class="form-control selectOption" id="couponId" name="couponId">
											<option value="0" selected="selected">------------ 쿠폰 선택 ------------</option>
											<c:forEach items="${ couponList }" var="coupon">
												<option value="${ coupon.couponPrice }_${ coupon.minPrice }_${ coupon.couponId }" class="couponOption">
													${ coupon.couponName }&nbsp;&nbsp;&nbsp;
													[ 적용금액: <fmt:formatNumber pattern="#,###,###" value="${ coupon.couponPrice }"/> | 
													최소금액: <fmt:formatNumber pattern="#,###,###" value="${ coupon.minPrice }"/> ]
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row resultDiv" style="background-color:#F6F6F6;">
									<div class="form-group col-md-1 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv">
										적용금액
									</div>
									<div class="form-group col-md-7 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv" id="discountPrice"></div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
					<div class="row">
						<div class="form mt-5">
							<div class="php-email-form">
								<div class="form-group">
									<h3>결제 정보</h3>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										주문상품
									</div>
									<div class="form-group col-md-7"></div>
									<div class="form-group col-md-1 resultInput" id="totalOrderPrice"></div>
									<div class="form-group col-md-1"></div>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										배송비
									</div>
									<div class="form-group col-md-7"></div>
									<div class="form-group col-md-1 resultInput" id="totalDeliveryPrice"></div>
									<div class="form-group col-md-1"></div>
								</div>
								<div class="row">
									<div class="form-group col-md-1"></div>
									<div class="form-group col-md-2 inputLabel">
										할인
									</div>
									<div class="form-group col-md-7"></div>
									<div class="form-group col-md-1 resultInput" id="totalDiscountPrice"></div>
									<div class="form-group col-md-1"></div>
								</div>
								<div class="row resultDiv" style="background-color:#F4F7FF;">
									<div class="form-group col-md-1 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv">
										최종 결제 금액<input type="hidden" name="orderPrice" id="orderPrice">
									</div>
									<div class="form-group col-md-7 resultDiv"></div>
									<div class="form-group col-md-2 resultDiv" id="totalPayPrice"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form mt-5">
							<div class="php-email-form">
								<div class="form-group">
									<h3>결제 방식</h3>
								</div>
								<div class="row">
									<div class="form-group col-md-3">
										<input type="button" class="form-control" value="일반결제" id="html5_inicis">
									</div>
									<div class="form-group col-md-3">
										<input type="button" class="form-control" value="카카오페이" id="kakaopay">
									</div>
									<div class="form-group col-md-3">
										<input type="button" class="form-control" value="토스페이" id="tosspay">
									</div>
									<div class="form-group col-md-3">
										<input type="button" class="form-control" value="가상결제" id="vbank">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form mt-5">
							<input type="button" class="payBtn" value="결제하기" onclick="checkSubmit()">
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
<!-- PortOne SDK -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script src="resources/client/js/post.js"></script>
<script src="resources/client/js/orderRegister.js"></script>
<script src="resources/client/js/orderReady.js"></script>
</body>
</html>