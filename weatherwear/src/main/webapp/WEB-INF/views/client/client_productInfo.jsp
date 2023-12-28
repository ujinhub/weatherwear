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
	<script src="/w2/resources/client/client_js/client_productInfo.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_productInfo_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<input type="hidden" name="proPrice" value="${ product.proPrice }">
		<input type="hidden" name="proName" value="${ product.proName }">
		<input type="hidden" name="proId" id="proId" value="${ product.proId }">
		<div class="body">
			<div class="product_body">
				<div class="product_info">
					<div class="product_main_image">
						<img class="main_image" src="${ product.imageDir }${ product.imageName }"><br>
					</div>
					<div class="product_info_detail">
						<ul class="product_info_ul">
							<li class="product_info_li">
								${ product.proName }
							</li>
							<li class="product_info_li">
								<fmt:formatNumber value="${ product.proPrice }" pattern="###,### 원"/>
							</li>
							<li class="product_info_li">
								<label for="opColor" class="product_info_label">색상</label>
								<select class="select" id="opColor" onchange="select(this)">
									<option value="SELECT">선택</option>
									<c:forEach var="color" items="${ product.colorList }">
										<!-- <option value="${ color }">${ color }</option> -->
 										<option value="${ color }">${ color }</option>
									</c:forEach>
								</select>
							</li>
							<li class="product_info_li">
								<label for="opSize" class="product_info_label">사이즈</label>
								<select class="select" id="opSize" onchange="select(this)">
									<option value="SELECT">선택</option>
									<c:forEach var="size" items="${ product.sizeList }">
										<option value="${ size }">${ size }</option>
									</c:forEach>
								</select>
							</li>
							<li class="product_info_optionSelect">
								<section class="total_price">
									<ul class="product_form_ul">
										<li class="product_form_th">
											총 상품 금액 : 
										</li>
										<li class="product_form_td">
											<input type="number" name="odTotal" value="0" id="odTotal" disabled="disabled"> 원
										</li>
									</ul>
								</section>
								<section id="selectOption">
								
								</section>
							</li>
							<li class="product_info_li">
								<form id="product_add_form">
									<ul class="product_order_ul">
										<li class="product_order_li">
											<input class="order_btn" type="button" value="장바구니에 담기" onclick="insertCart()">
										</li>
										<li class="product_order_li">
											<input class="order_btn" type="button" value="바로 주문하기">
										</li>
										<li class="product_order_li">
											<input class="order_btn" type="button" value="찜하기">
										</li>
									</ul>
								</form>
							</li>
						</ul>
					</div>
				</div>
				<div class="product_info_menu">
					<ul class="product_info_menu_ul">
						<li class="product_info_menu_li">
							<a id="proDetail_btn">상세 정보</a>
						</li>
						<li class="product_info_menu_li">
							<a id="proReview_btn">리뷰</a>
						</li>
						<li class="product_info_menu_li">
							<a id="proQna_btn">문의</a>
						</li>
					</ul>
				</div>
				<div class="product_detail">
					<div class="product_detailImage">
						<c:forEach var="im" items="${ product.detailImage }">
							<img class="detailImage" src="${ product.imageDir }${ im }"><br>
						</c:forEach>
					</div>
					<div class="product_content">
						${ product.proContent }
					</div>
				</div>			
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>