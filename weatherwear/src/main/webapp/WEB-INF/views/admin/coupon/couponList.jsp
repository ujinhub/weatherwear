<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WeatherWear 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<!-- Font Awesome -->
	<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
	<!-- Theme style -->
	<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
	<!-- Swiper -->
	<link href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" rel="stylesheet"/>
	<style>
		.checkbox{ width:20px; height: 20px;}
		.checkboxDiv{ display: flex; align-items: center; justify-content: space-around;}
		.checkDiv{ display:flex; align-items: stretch;}
	</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
	
		<div class="content-header">
			<section class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>쿠폰 관리</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">쿠폰 관리</li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			
			<section class="content">
				<div class="container">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<div class="orderby_btn">
											<button type="button" class="btn btn-sm btn-outline-primary" onclick="newCoupon()">+추가</button>
										</div>
									</div>
									<div class="card-tools">
										<!-- Search -->
										<div class="input-group input-group-sm">
											<select id="searchType" class="form-control">
												<option value="couponId">번호</option>
												<option value="couponName">이름</option>
											</select>
											<input type="text" id="keyword" class="form-control float-right" placeholder="Search">
											<div class="input-group-append">
												<button type="button" class="btn btn-default" id="btnSearch">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
										<!-- End Search -->
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<form id="listForm" name="clientForm" method="post">
										<table class="table table-hover text-nowrap" style="table-layout: fixed;">
											<colgroup>
												<col width="50px"/><!-- # -->
												<col width="200px"/><!-- 쿠폰번호 -->
												<col width="270px"/><!-- 쿠폰이름 -->
												<col width="90px"/><!-- 쿠폰금액 -->
												<col width="90px"/><!-- 쿠폰 적용 최소 금액 -->
												<col width="310px"/><!-- 사용기간 -->
												<col width="100px"/><!-- 버튼 -->
											</colgroup>
											<thead>
												<tr>
													<th>#</th>
													<th>쿠폰번호</th>
													<th>쿠폰이름</th>
													<th>쿠폰금액</th>
													<th>최소 금액</th>
													<th>사용기간</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="coupon" items="${couponList}" varStatus="status">
													<tr class="${coupon.couponId}">
														<td>${ status.index+1 }</td>
														<td id="couponId_${coupon.couponId}">${coupon.couponId}</td>
														<td id="couponName_${coupon.couponId}">${coupon.couponName}</td>
														<td id="couponPrice_${coupon.couponId}"><fmt:formatNumber pattern="#,###,###" value="${coupon.couponPrice}"/></td>
														<td id="couponMinPrice_${coupon.couponId}"><fmt:formatNumber pattern="#,###,###" value="${coupon.minPrice}"/></td>
														<td id="couponDate_${coupon.couponId}">
															<fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${coupon.couponStDate}"/> / <fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${coupon.couponEndDate}"/>
														</td>
														<td id="couponBtn_${coupon.couponId}"><input type="button" value="삭제" class="btn btn-sm btn-outline-danger" onclick="deleteCoupon(this)"></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</form>
								</div>
								<!-- Modal -->
								<div class="modal fade show" id="modal-default" style="display: none; padding-right: 17px;" aria-modal="true" role="dialog">
								</div>
								<!-- pagination -->
								<div class="card-footer">		
									<div class="card-title input-group-sm">
										<select name="searchType" class="form-control" id="listSize" onchange="page(1)">
											<option value="5" <c:if test="${pagination.getListSize() == 5}">selected="selected"</c:if>>5개</option>
											<option value="10" <c:if test="${pagination.getListSize() == 10}">selected="selected"</c:if>>10개</option>
											<option value="15" <c:if test="${pagination.getListSize() == 15}">selected="selected"</c:if>>15개</option>
											<option value="30" <c:if test="${pagination.getListSize() == 30}">selected="selected"</c:if>>30개</option>
										</select>
									</div>
									
									<ul class="pagination pagination-sm m-0 float-right">
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
						</div>
					</div>
					
				</div>
			</section>
		</div>	
			
		<%@ include file="../footer.jsp" %>
	</div>
	<div class="modal-backdrop fade" style="display:none"></div>
	
	<!-- jQuery -->
	<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script	src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	<!-- Bootstrap 4 -->
	<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Modal -->
	<script src="resources/admin/AdminLTE/plugins/sweetalert2/sweetalert2.min.js"></script>
	<script src="resources/admin/AdminLTE/dist/js/adminlte.min.js?v=3.2.0"></script>
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	
	<script src="resources/util/js/pagingNoOrderBy.js"></script>
	<script src="resources/util/js/modal.js"></script>
	<script src="resources/util/js/checkbox.js"></script>
	<script src="resources/admin/js/manageCoupon.js"></script>
	<script src="resources/admin/js/common.js"></script>
</body>
</html>