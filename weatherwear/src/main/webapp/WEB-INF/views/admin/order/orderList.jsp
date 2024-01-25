<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WeatherWear 관리자</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- Font Awesome -->
<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
<style>
	.orderStatus_select {width: 130px !important;}
	.option_select {width: 100px !important;}
	.deliveryNum_select {display: none;}
	.check {width:20px; height:20px;}
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
							<h1>주문 목록</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">주문 목록</li>
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
											<button id="orderDate" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'orderDate'}">active</c:if>">최신순</button>
											<button id="clientId" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'clientId'}">active</c:if>">아이디순</button>
											<button id="orderStatus" type="button" class="orderbyBtn btn btn-sm btn-outline-light <c:if test="${search.orderby == 'orderStatus'}">active</c:if>">주문상태순</button>
											<button class="btn btn-sm btn-secondary buttons-pdf buttons-html5 download" tabindex="0" aria-controls="example1" type="button" id="excelDownload" onclick="exportExcel('주문내역')">
												<span>Excel 저장</span>
											</button>
										</div>
									</div>
									<div class="card-tools">
										<!-- Search -->
										<div class="input-group input-group-sm">
											<select id="searchType" class="form-control">
												<option value="orderId">주문번호</option>
												<option value="clientId">아이디</option>
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
													<option value="orderStatus">주문상태</option>
													<option value="deliverNum">송장번호</option>
												</select>&nbsp;&nbsp;&nbsp;
												<div class="input-group-sm orderStatus_select">
													<select class="form-control orderStatus_select" id="orderStatus_value">
														<option value="상품준비중">상품준비중</option>
														<option value="배송준비중">배송준비중</option>
														<option value="배송보류">배송보류</option>
														<option value="배송대기">배송대기</option>
														<option value="배송중">배송중</option>
														<option value="배송완료">배송완료</option>
														<option value="교환중">교환중</option>
														<option value="환불중">환불중</option>
														<option value="교환완료">교환완료</option>
														<option value="환불완료">환불완료</option>
													</select>
												</div>
												<div class="input-group-sm deliveryNum_select">
													<input type="text" name="deliverNum_value" class="form-control float-right" placeholder="송장번호">
												</div>
												<div class="input-group-append">
													<button id="add-new-event" type="button" class="btn btn-sm btn-outline-primary" onclick="modify()">수정하기</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<form id="listForm" name="clientForm" method="post">
										<table class="table table-hover text-nowrap" style="table-layout: fixed;" id="tableData">
											<colgroup>
												<col width="80px"/><!-- # -->
												<col width="280px"/><!-- 주문번호 -->
												<col width="150px"/><!-- 주문상태 -->
												<col width="200px"/><!-- 주문일자 -->
												<col width="100px"/><!-- 회원번호 -->
												<col width="100px"/><!-- 구매자이름 -->
												<col width="150px"/><!-- 구매자연락처 -->
												<col width="150px"/><!-- 주문상품 -->
												<col width="150px"/><!-- 주문옵션 -->
												<col width="80px"/><!-- 주문수량 -->
												<col width="100px"/><!-- 상품금액 -->
												<col width="150px"/><!-- 수신인 이름 -->
												<col width="150px"/><!-- 수신인 연락처 -->
												<col width="250px"/><!-- 송장번호 -->
												<col width="100px"/><!-- 우편번호 -->
												<col width="150px"/><!-- 기본주소 -->
												<col width="150px"/><!-- 상세주소 -->
												<col width="250px"/><!-- 배송메모 -->
												<col width="100px"/><!-- 결제금액 -->
												<col width="100px"/><!-- 결제수단 -->
												<col width="100px"/><!-- 결제상태 -->
												<col width="200px"/><!-- 결제일자 -->
											</colgroup>
											<thead>
												<tr>
													<th><button type="button" class="btn btn-sm btn-outline-primary checkAll" id="checkAll">전체선택</button></th>
													<th>주문번호</th>
													<th>주문상태</th>
													<th>주문일자</th>
													<th>회원번호</th>
													<th>구매자 이름</th>
													<th>구매자 연락처</th>
													<th>주문상품</th>
													<th>주문옵션</th>
													<th>주문수량</th>
													<th>상품금액</th>
													<th>수신인 이름</th>
													<th>수신인 연락처</th>
													<th>송장번호</th>
													<th>우편번호</th>
													<th>기본주소</th>
													<th>상세주소</th>
													<th>배송메모</th>
													<th>결제금액</th>
													<th>결제수단</th>
													<th>결제상태</th>
													<th>결제일자</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${orderList}" varStatus="status">
													<tr>
														<td class="checklist"><input type="checkbox" value="${item.orderId}_${item.productId}" class="check"></td>
														<td>${item.orderId}</td>
														<td>${item.orderStatus}</td>
														<td>${item.orderDate}</td>
														<td>${item.clientId}</td>
														<td>${item.clientName}</td>
														<td>${fn:substring(item.clientNum,0,3)}-${fn:substring(item.clientNum,3,7)}-${fn:substring(item.clientNum,7,11)}</td>
														<td>${item.productName}</td>
														<td>${item.optionName}</td>
														<td>${item.orderProCnt}</td>
														<td><fmt:formatNumber value="${item.orderTotal}" pattern="###,###"/></td>
														<td>${item.addressName}</td>
														<td>${fn:substring(item.addressNum,0,3)}-${fn:substring(item.addressNum,3,7)}-${fn:substring(item.addressNum,7,11)}</td>
														<td><input type="text" value="${item.deliverNum}"></td>
														<td>${item.addressPostNum}</td>
														<td>${item.address1}</td>
														<td>${item.address2}</td>
														<td>${item.addressMemo}</td>
														<td><fmt:formatNumber value="${item.orderPrice - item.usedPoint - ci.couponPrice}" pattern="###,###"/></td>
														<td>${item.paymentMethod}</td>
														<td>${item.paymentStatus}</td>
														<td>${item.paymentDate}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</form>
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
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Sheet JS (Excel)-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
<!--FileSaver savaAs 이용 (Excel)-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>

<script src="resources/util/js/orderbypaging.js"></script>
<script src="resources/util/js/checkbox.js"></script>
<script src="resources/util/js/saveExcel.js"></script>
<script src="resources/admin/js/manageOrderList.js"></script>
</body>
</html>