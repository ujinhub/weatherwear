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
	<link href="resources/client/css/review.css" rel="stylesheet">
	<style>
		.mg-2 { margin: 20px 0; }
		.mg-3 { margin: 30px 0 0; }
		.pd-1 { padding: 10px; }
		.table td { vertical-align: middle; }
		.custom-radio { display:flex; justify-content: space-evenly;}	
	</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact mb-2">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">REVIEW</h1>
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
							<input type="radio" name="options" id="review" autocomplete="off" onclick="location.href='myreviewList.do'" checked>리뷰
						</label>
					</div>
				</div>
			</section>
			
			<section class="contact mb-2">
				<div style="width: 80%; margin: 0 auto;">
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="15%" />
								<col width="35%" />
								<col width="35%" />
								<col width="15%" />
							</colgroup>
							<thead>
								<tr>
									<th>사진</th>
									<th>상품정보</th>
									<th>리뷰내용</th>
									<th>등록일자</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(reviewList) == 0}">
									<tr>
										<td colspan="4">작성된 리뷰가 없습니다.</td>
									</tr>  
								</c:if>
								<c:if test="${fn:length(reviewList) > 0}">
									<c:forEach var="item" items="${reviewList}" varStatus="status">
										<tr onclick="reviewView('${ item.reviewId }', '${item.productName}', '${item.image}', '', '${item.optionColor}', '${item.optionSize}' )">
											<td><img src="${item.image}" style="height: 100px;"></td>
											<td class="text-left">
												${item.productName}<br>
												[옵션: ${item.optionColor} / ${item.optionSize}]
											</td>
											<td>${item.reviewContent}</td>
											<td>${item.reviewDate}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="card-footer mg-2">
						<input type="hidden" id="listSize" value="10">
						<ul class="pagination justify-content-center pagination-sm m-0">
							<c:if test="${pagination.prev}">
								<li class="page-item">
									<a class="page-link" href="#" onclick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}')">&laquo;</a>
								</li>
							</c:if>
							<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="pageId">
								<li class="page-item <c:out value="${pagination.page == pageId ? 'active':''}"/>">
									<a class="page-link" href="#" onclick="fn_pagination('${pageId}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}')">${pageId}</a>
								</li>
							</c:forEach>										
							<c:if test="${pagination.next}">
								<li class="page-item">
									<a class="page-link" href="#" onclick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}')">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</section>
		</main>
		
		<%@ include file="../footer.jsp" %>
	</div>
	<!-- Modal -->
	<div class="modal fade show" id="modal-default" style="display: none; padding-right: 17px;" aria-modal="true" role="dialog"></div>
	<div class="modal-backdrop fade" style="display:none"></div>
	
	<script src="resources/client/ZenBlog/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script  src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	
	<!-- Template Main JS File -->
	<script src="resources/client/ZenBlog/assets/js/main.js"></script>
	
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
	
	<script src="resources/util/js/modal.js"></script>
	<script src="resources/util/js/paging.js"></script>
	<script src="resources/client/js/orderInfo.js"></script>
</body>
</html>