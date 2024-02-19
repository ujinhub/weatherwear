<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
							<h1>문의 목록</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">문의 목록</li>
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
										<div class="input-group input-group-sm">
											<select id="searchType" class="form-control">
												<option value="qnaTitle">제목</option>
												<option value="qnaContent">내용</option>
												<option value="qnaStatus">답변상태</option>
												<option value="qnaType">문의타입</option>
											</select>
											
											<input type="text" id="keyword" class="form-control float-right" placeholder="Search">
											<div class="input-group-append">
												<button type="button" class="btn btn-default" id="btnSearch">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
								
								<div class="card-body table-responsive p-0">
									<table class="table table-hover text-nowrap" style="table-layout: fixed;">
										<colgroup>
											<col width="10%" />
											<col width="15%" />
											<col width="30%" />
											<col width="15%" />
											<col width="20%" />
											<col width="10%" />
										</colgroup>
										<thead>
											<tr>
												<th>#</th>
												<th>문의타입</th>
												<th>제목</th>
												<th>작성자</th>
												<th>등록일시</th>
												<th>답변상태</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${qnaList}" varStatus="status">
												<tr onclick="location.href='qnaInfo.mdo?qnaId=${item.qnaId}'">
													<td>${item.qnaIdx}</td>
													<td>${item.qnaType}</td>
													<td style="text-align: left;">${item.qnaTitle}</td>
													<td>${item.clientId}</td>
													<td>${item.qnaDate}</td>
													<td>${item.qnaStatus}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="resources/util/js/pagingNoOrderBy.js"></script>
<script src="resources/admin/js/common.js"></script>
<script>
	$(function() {
		$('#searchType').change(function() {
			$('#keyword').remove();
			
			var el;
			if($(this).val() == 'qnaStatus') {
				el = '<select id="keyword" class="form-control float-right">'
						+ '<option value="답변대기">답변대기</option>'
						+ '<option value="답변완료">답변완료</option>'
						+ '</select>';
			} else if($(this).val() == 'qnaType') {
				el = '<select id="keyword" class="form-control float-right">'
					+ '<option value="회원정보">회원정보</option>'
					+ '<option value="상품확인">상품확인</option>'
					+ '<option value="주문/결제">주문/결제</option>'
					+ '<option value="배송">배송</option>'
					+ '<option value="교환/취소(반품)">교환/취소(반품)</option>'
					+ '<option value="서비스">서비스</option>'
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