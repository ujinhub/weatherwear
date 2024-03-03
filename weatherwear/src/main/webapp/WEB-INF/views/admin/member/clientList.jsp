<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WeatherWear 관리자</title>
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
							<h1>회원 목록</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">회원 목록</li>
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
									<div class="card-tools">
										<!-- Search -->
										<div class="input-group input-group-sm">
											<select id="searchType" class="form-control">
												<option value="clientId">아이디</option>
												<option value="clientName">이름</option>
												<option value="gradeId">등급</option>
											</select>
											<input type="text" id="keyword" class="form-control float-right" placeholder="Search">
											<div class="input-group-append">
												<button type="button" class="btn btn-default" id="btnSearch">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div><!-- End Search -->
									</div>
								</div>
								
								<div class="card-body table-responsive p-0">
									<form id="listForm" name="clientForm" method="post">
										<table class="table table-hover text-nowrap" style="table-layout: fixed;">
											<colgroup>
												<col width="10%" />
												<col width="15%" />
												<col width="15%" />
												<col width="15%" />
												<col width="25%" />
												<col width="5%" />
												<col width="15" />
											</colgroup>
											<thead>
												<tr>
													<th>#</th>
													<th>아이디</th>
													<th>이름</th>
													<th>전화번호</th>
													<th>이메일</th>
													<th>등급</th>
													<th>상태</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${clientList}" varStatus="status">
													<tr onclick="location.href='clientInfo.mdo?clientId=${item.clientId}'">
														<td>${item.clientIdx}</td>
														<td>${item.clientId}</td>
														<td>${item.clientName}</td>
														<c:set var="clientNum" value="${item.clientNum}"/>
														<td>${fn:substring(clientNum,0,3)}-${fn:substring(clientNum,3,7)}-${fn:substring(clientNum,7,12)}</td>
														<td>${item.clientEmail}</td>
														<td>${item.gradeId}</td>
														<td>
															<c:if test="${item.withdrawId != null && item.withdrawId != ''}">
																탈퇴
															</c:if>
														</td>
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

	<!-- jQuery -->
	<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script	src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	<!-- Bootstrap 4 -->
	<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- sweetAlert (alert/confirm/toast) -->
	<script src="resources/util/js/sweetalert.js"></script>
	
	<script src="resources/admin/js/common.js"></script>
	<script src="resources/util/js/pagingNoOrderBy.js"></script>
	<script>
		$(document).ready(function() {
			// 한번 생각해보기
			<c:if test="${msg != null && msg != ''}">
				playalert("${msg}");
			</c:if>
			
			$('#searchType').change(function() {
				$('#keyword').remove();
				
				var el;
				if($(this).val() == 'gradeId') {
					el = '<select id="keyword" class="form-control float-right">'
							+ '<option value="S">S</option>'
							+ '<option value="G">G</option>'
							+ '<option value="B">B</option>'
							+ '</select>';
				} else {
					el = '<input type="text" id="keyword" class="form-control float-right" placeholder="Search">';
				}
	
				$('#searchType').after(el);
			});
		});
	</script>
</body>
</html>