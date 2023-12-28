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
	<script src="resources/client/client_js/client_productList.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_productList_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="productList">
				<div class="Title">
					<h2>${ param.gubun }</h2>
				</div>
				<div class="orderby">
					<select class="select" id="ordertype" onchange="orderBy()">
						<option ${ pararm.ordertype == 'proRegDate' ? 'selected' : '' || param.ordertype == null ? 'selected' : ''} value="proRegDate">최신순</option>
						<option ${ param.ordertype == 'proCnt' ? 'selected' : '' } value="proCnt">판매순</option>
						<option ${ param.ordertype == 'proPriceH' ? 'selected' : '' } value="proPriceH">높은 가격순</option>
						<option ${ param.ordertype == 'proPriceL' ? 'selected' : '' } value="proPriceL">낮은 가격순</option>
					</select>
				</div>
				<div class="clear_con"></div>
				<div class="product">
					<c:forEach var="product" items="${ productList }">
						<ul class="product_list">
							<li>
								<a href="clientProductInfo.do?proId=${ product.proId }">
									<img src="${ product.imageDir }${ product.imageName }" class="productList_img">
								</a>
							</li>
							<li class="name">
								<a href="clientProductInfo.do?proId=${ product.proId }">
									${ product.proName }
								</a>
							</li>
							<li class="price">&#8361; <fmt:formatNumber value="${ product.proPrice }" pattern="###,###" />
						</ul>
					</c:forEach>
				</div>
				<div class="clear_con"></div>
	<!-- 페이징 처리 -->
				<div class="paging">
					<div class="prev_btn">
						<ul class="paging_ul">
							<c:if test="${ paging.prev }">
								<li class="paging_li">
									<a href="productList.do?page=${startPage -1}&gubun=${ param.gubun }&ordertype=${param.ordertype}">처음페이지로</a>
								</li>
							</c:if>
							<c:if test="${ paging.currentPage>1 }">
								<li class="paging_li">
									<a href="productList.do?page=${ paging.currentPage -1}&gubun=${ param.gubun }&ordertype=${param.ordertype}">이전</a>
								</li>
							</c:if>
						</ul>
					</div>
					<div class="page_btn">
						<ul class="paging_ul">
							<c:forEach var="pageNum" begin="${paging.startPage}" end="${paging.endPage}">
								<li class="paging_li">
									<a href="productList.do?page=${ pageNum }&gubun=${ param.gubun }&ordertype=${param.ordertype}"
										style="${(pageNum == paging.currentPage) ? 'color:red; font-style:italic;' : 'color:dimgrey;'}">${ pageNum }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="next_btn">
						<ul class="paging_ul">
							<c:if test="${ paging.currentPage < paging.endPage }">
								<li class="paging_li">
									<a href="productList.do?page=${ paging.currentPage + 1 }&gubun=${ param.gubun }&ordertype=${param.ordertype}">다음</a>
								</li>
							</c:if>
							<!-- 다음 버튼 -->
							<c:if test="${ paging.next }">
								<li class="paging_li">
									<a href="productList.do?page=${ paging.endPage +1 }&gubun=${ param.gubun }&ordertype=${param.ordertype}">마지막페이지로</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>