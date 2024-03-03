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
							<h1 class="page-title">ORDER</h1>
						</div>
					</div>
				</div>
				
				<div class="text-center">
					<div class="btn-group btn-group-toggle col-sm-10" data-toggle="buttons">
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="order" autocomplete="off" onclick="location.href='myorderList.do'" checked>주문조회
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
			
			<section class="contact mb-2">
				<div style="width: 80%; margin: 0 auto;">
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap" style="table-layout: fixed;">
							<colgroup>
								<col width="15%" />
								<col width="15%" />
								<col width="35%" />
								<col width="5%" />
								<col width="15%" />
								<col width="15%" />
							</colgroup>
							<thead>
								<tr>
									<th>주문일자[주문번호]</th>
									<th>사진</th>
									<th>상품정보</th>
									<th>수량</th>
									<th>상품구매금액</th>
									<th>주문처리상태</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${fn:length(orderList) == 0}">
									<tr>
										<td colspan="7">주문 내역이 없습니다.</td>
									</tr>
								</c:if>
								<c:if test="${fn:length(orderList) > 0}">
									<c:forEach var="item" items="${orderList}" varStatus="status">
										<tr>
											<td>
												<fmt:formatDate value='${item.orderDate}' pattern='yyyy-MM-dd'/><br>
												<a href="orderInfo.do?orderId=${item.orderId}">[${item.orderId}]</a>
											</td>
											<td><a href="productInfo.do?productId=${item.productId}"><img src="${item.image}" style="height: 100px;"></a></td>
											<td>
												${item.productName}<br>
												[옵션: ${item.optionColor} / ${item.optionSize}]
											</td>
											<td>${item.orderProCnt}</td>
											<td><fmt:formatNumber value="${item.orderTotal}" pattern="###,###"/></td>
											<td>${item.orderStatus}</td>
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
	
	<script src="resources/util/js/paging.js"></script>
	<script>
		$(document).ready(function() {
			$('.addCart').on('click', function() {
				if(confirm('상품 상세 페이지에서 옵션을 선택하신 후 장바구니에 담아주세요.')) {
					location.href = "productInfo.do?productId=" + $(this).prev().val();
				}
			});
			
			$('.delWish').on('click', function() {
				var checkList = [];
				checkList.push($(this).siblings('input').val());
		
				deleteWish(checkList);
			});
			
			$('#selDel').on('click', function() {
				var checkList = [];
				
				$("input:checkbox[name='chk']").each(function() {
					if($(this).is(":checked") == true) {
						checkList.push($(this).parents().siblings().children('input').val());
					}			
				});
		
				if(checkList.length > 0) {
					deleteWish(checkList);
				} else {
					playToast("삭제 할 상품을 선택해주세요", 'warning');
				}
			});
		
			function deleteWish(checkList) {
				$.ajax({
					url: "/w2/wishListDelete.do",
					type: "POST",
					async: true,
					data: JSON.stringify(checkList),
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
			
			$('#checkAll').on('click', function() {
				if($("#checkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
				else $("input[name=chk]").prop("checked", false);
			});
			
			$("input[name=chk]").click(function() {
				var total = $("input[name=chk]").length;
				var checked = $("input[name=chk]:checked").length;
		
				if(total != checked) $("#checkAll").prop("checked", false);
				else $("#checkAll").prop("checked", true); 
			});
		})
	</script>
</body>
</html>