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

<!-- Template Main CSS Files -->
<link href="resources/client/ZenBlog/assets/css/main.css" rel="stylesheet">
<link href="resources/client/ZenBlog/assets/css/variables.css" rel="stylesheet">

<style>
.mb-2 { width: 210px; height: 50px; overflow: hidden}
.col-lg-3 { height: 440px; padding-left: 5%;}
.productName { width: 100%; }
.productList { display: flex; flex-direction: row; flex-wrap: wrap;}
.productOne { height: 470px; display: flex; flex-direction: column; align-items: center;}
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		<main id="main">
			<section>
				<div class="container">
					<div class="row">
						<div class="col-md-10 aos-init aos-animate" data-aos="fade-up">
							<c:if test="${ param.searchType != null  && param.searchType != 'productName' && param.searchType != 'productRegDate'}">
								<h3 class="category-title">Category: ${ param.searchType }</h3>
							</c:if>
<!-- 							<select name="searchType" class="form-control" id="listSize" onchange="page(1)" style="width:90%;"> -->
<%-- 								<option id="productRegDate" value="productRegDate" <c:if test="${search.orderby == 'productRegDate'}">selected="selected"</c:if>>최신순</option> --%>
<%-- 								<option id="productName" value="productName" <c:if test="${search.orderby == 'productName'}">selected="selected"</c:if>>상품명순</option> --%>
<!-- 							</select><br> -->
							<!-- ProductList -->
							<div class="productList row">
								<c:forEach var="item" items="${productList}" varStatus="status">
								<!-- 상품 시작 -->
								<div class="col-md-3 lg productOne row" onclick="location.href='productInfo.do?productId=${ item.productId }'">
									<img src="${ item.mainImage }" alt="${ item.productId }_mainImage" class="img-fluid" style="width:100%; height:auto;">
									<div class="productOneInfo row"><br>
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
											<span>VIEW: ${ item.productView }</span></div>
										<h4 class="productName">${ item.productName }</h4>
										<h4><fmt:formatNumber value="${item.productPrice}" pattern="###,###"/></h4>
									</div>
								</div>
								<!-- 상품 끝 -->
								</c:forEach>
							</div>
							<!-- End ProductList -->
							<!-- Paging -->
							<div class="text-start py-4">
								<div class="custom-pagination">
									<c:if test="${pagination.prev}">
										<a href="#" class="prev" onclick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">Prevous</a>
									</c:if>
									<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="pageId">
										<a class="page-link <c:out value="${pagination.page == pageId ? 'active':''}"/>" href="#" onclick="fn_pagination('${pageId}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">${pageId}</a>
									</c:forEach>	
									<c:if test="${pagination.next}">
										<a href="#" class="next" onclick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">Next</a>
									</c:if>
								</div>
							</div>
<!-- 								<div class="card-footer">		 -->
<!-- 									<div class="card-title input-group-sm"> -->
<!-- 										<select name="searchType" class="form-control" id="listSize" onchange="page(1)"> -->
<%-- 											<option value="20" <c:if test="${pagination.getListSize() == 20}">selected="selected"</c:if>>20개</option> --%>
<%-- 											<option value="40" <c:if test="${pagination.getListSize() == 40}">selected="selected"</c:if>>40개</option> --%>
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div> -->
						</div>
						
						<!-- Category -->
						<div class="col-md-2">
							<div class="aside-block">
								<h3 class="aside-title">Categories</h3>
								<ul class="aside-links list-unstyled">
									<li><a href="productList.do"><i class="bi bi-chevron-right"></i> NEW</a></li>
									<li><a href="productList.do?searchType=outer"><i class="bi bi-chevron-right"></i> OUTER</a></li>
									<li><a href="productList.do?searchType=tops"><i class="bi bi-chevron-right"></i> TOPS</a></li>
									<li><a href="productList.do?searchType=pants"><i class="bi bi-chevron-right"></i> PANTS</a></li>
									<li><a href="productList.do?searchType=skirts"><i class="bi bi-chevron-right"></i> SKIRTS</a></li>
									<li><a href="productList.do?searchType=dress"><i class="bi bi-chevron-right"></i> DRESS</a></li>
								</ul>
							</div>
							<!-- End Categories -->
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

<script src="resources/util/js/paging.js"></script>

</body>
</html>