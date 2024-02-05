<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeatherWear 관리자</title>
<style>
	a { color:black !important; }
	.click:hover { cursor: pointer;}
	.tdContent {display:flex; justify-content: center; height: 70px; align-items: center;}
	.check {width:20px; height:20px;}
	.productPrice_select{display: none;}
</style>
<!-- Font Awesome -->
<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
	
		<div class="content-header">
			<section class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>상품 목록</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">상품 목록</li>
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
											<button id="productRegDate" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'productRegDate'}">active</c:if>">최신등록순</button>
											<button id="productName" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'productName'}">active</c:if>">상품명순</button>
											<button id="productId" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'productId'}">active</c:if>">상품번호순</button>
											<button type="button" class="btn btn-sm btn-outline-primary" onclick="location.href='productRegister.mdo'">+등록</button>
										</div>
									</div>
									<div class="card-tools">
										<!-- Search -->
										<div class="input-group input-group-sm">
											<select id="searchType" class="form-control">
												<option value="productId">상품번호</option>
												<option value="productName">상품이름</option>
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
								<div class="card-header">
									<div class="card-title">
										<div class="orderby_btn">
											<div class="input-group input-group-sm">
												<select class="form-control option_select" onchange="change(this)" id="modifyType">
													<option value="productSell">판매상태</option>
													<option value="productPrice">판매가</option>
												</select>&nbsp;&nbsp;&nbsp;
												<div class="input-group-sm productSell_select">
													<select class="form-control productSell_select" id="productSell_value">
														<option value="Y">판매중</option>
														<option value="N">판매중지</option>
													</select>
												</div>
												<div class="input-group-sm productPrice_select">
													<input type="text" name="productPrice_value" class="form-control float-right" placeholder="판매가">
												</div>
												<div class="input-group-append">
													<button id="add-new-event" type="button" class="btn btn-sm btn-outline-primary" onclick="update()">수정하기</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<form id="listForm" name="clientForm" method="post">
										<table class="table table-hover text-nowrap" style="table-layout: fixed;">
											<colgroup>
												<col width="10%" /><!-- checkbox -->
												<col width="15%" /><!-- 상품번호 -->
												<col width="10%" /><!-- 이미지 -->
												<col width="30%" /><!-- 상품이름 -->
												<col width="10%" /><!-- 판매상태 -->
												<col width="10%" /><!-- 판매가 -->
												<col width="10%" /><!-- 조회수 -->
											</colgroup>
											<thead>
												<tr>
													<th><button type="button" class="btn btn-sm btn-outline-primary checkAll" id="checkAll">전체선택</button></th>
													<th>상품번호</th>
													<th colspan="2">상품이름</th>
													<th>판매상태</th>
													<th>판매가</th>
													<th>조회수</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${productList}" varStatus="status">
													<tr class="click">
														<td class="checklist"><span class="tdContent"><input type="checkbox" id="${ item.productId }" value="${ item.productId }" class="check"></span></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><span class="tdContent">${item.productId}</span></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><img src='${item.mainImage}' style="width:70px; height:70px;"></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><span class="tdContent">${item.productName}</span></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><span class="tdContent">${item.productSell}</span></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><span class="tdContent">${item.productPrice}</span></td>
														<td onclick="location.href='productInfo.mdo?productId=${ item.productId }'"><span class="tdContent">${item.productView}</span></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</form>
								</div>
								<script>
								</script>
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
												<a class="page-link" href="#" onclick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">&laquo;</a>
											</li>
										</c:if>
										<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="pageId">
											<li class="page-item <c:out value="${pagination.page == pageId ? 'active':''}"/>">
												<a class="page-link" href="#" onclick="fn_pagination('${pageId}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">${pageId}</a>
											</li>
										</c:forEach>										
										<c:if test="${pagination.next}">
											<li class="page-item">
												<a class="page-link" href="#" onclick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}', '${pagination.listSize}', '${search.searchType}', '${search.keyword}', '${search.orderby}')">&raquo;</a>
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

<!-- jQuery -->
<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
<script	src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- sweetAlert (alert/confirm/toast) -->
<script src="resources/util/js/sweetalert.js"></script>

<script src="resources/util/js/orderbypaging.js"></script>
<script src="resources/util/js/checkbox.js"></script>
<script src="resources/admin/js/manageProduct.js"></script>
</body>
</html>