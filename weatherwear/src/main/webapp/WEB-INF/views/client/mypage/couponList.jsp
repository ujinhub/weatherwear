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
	<style>
		.mg-2 { margin: 20px 0; }
		.mg-3 { margin: 30px 0 0; }
		.pd-1 { padding: 10px; }
		.table td {
			vertical-align: middle;
		}
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
							<h1 class="page-title">COUPON</h1>
						</div>
					</div>
				</div>
				<div class="text-center">
					<div class="btn-group btn-group-toggle col-sm-10" data-toggle="buttons">
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="order" autocomplete="off" onclick="location.href='myorderList.do'">주문조회
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="coupon" autocomplete="off" onclick="location.href='mycouponList.do'" checked>쿠폰
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
			<section class="contact mb-2">
				<div class="card" style="width: 80%; margin: 0 auto;">
					<div class="card-body" style="width: 50%; margin: 0 auto;">
						<div class="row col-lg-12">
							<div class="col-sm-7">
								<input type="text" class="form-control text-center" id="couponId" name="couponId">
							</div>
							<div class="col-sm-5">
								<button type="button" id="couponReg" class="btn btn-flat btn-primary">쿠폰등록</button>
							</div>
						</div>
					</div>
				</div>
			</section>
						
			<section class="contact mb-2">
				<div style="width: 80%; margin: 0 auto;">
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="20%" />
								<col width="30%" />
								<col width="15%" />
								<col width="15%" />
								<col width="20%" />
							</colgroup>
							<thead>
								<tr>
									<th>쿠폰번호</th>
									<th>쿠폰이름</th>
									<th>사용가능금액</th>
									<th>할인금액</th>
									<th>사용기간</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(couponList) == 0}">
									<tr>
										<td colspan="5">쿠폰 내역이 없습니다.</td>
									</tr>  
								</c:if>
								<c:if test="${fn:length(couponList) > 0}">
									<c:forEach var="item" items="${couponList}" varStatus="status">
										<tr>
											<td>${item.couponId}</td>
											<td>${item.couponName}</td>
											<td><fmt:formatNumber value="${item.minPrice}" pattern="###,###"/></td>
											<td><fmt:formatNumber value="${item.couponPrice}" pattern="###,###"/></td>
											<td>${item.couponStDate} <br>~ ${item.couponEndDate}</td>
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

	<script src="resources/client/ZenBlog/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script  src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	
	<!-- Template Main JS File -->
	<script src="resources/client/ZenBlog/assets/js/main.js"></script>
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
	<!-- jQuery-validation -->
	<script src="resources/admin/AdminLTE/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script src="resources/admin/AdminLTE/plugins/jquery-validation/additional-methods.min.js"></script>
	
	<script src="resources/util/js/paging.js"></script>
	<script>
		$(document).ready(function() {
			$('#couponReg').on('click', function() {
				if($('#couponId').val() == '') {
					playToast("쿠폰번호를 입력해주세요.", 'warning');
				} else {
					$.ajax({
						url: "couponRegProc.do",
						type: "POST",
						async: true,
						data: $('#couponId').val(),
						dataType: "json",
						contentType: "application/json",
						success: function(res){
							if(res.code == -1) {
								playToast(res.message, 'error');
								return;
							}else if (res.code == -2){
								playConfirm(res.message, res.data, 'question', '로그인하기', '취소하기', "location.href='login.do'", null);
							} 
							if(res.code == 1) {
								playToast(res.message, 'success');
								location.reload();
							} 
							return;
						},
						error : function(error){
							playToast(error.message, 'error');
						}
					});
				}
			});			
		})
	</script>
</body>
</html>