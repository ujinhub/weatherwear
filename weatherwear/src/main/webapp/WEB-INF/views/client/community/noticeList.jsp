<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		.description {
		    color: #6c757d;
		    font-size: 13px;
		    vertical-align: middle;
		}
		.required .col-form-label:after {
			content: "*";
			color: red;
		}
	</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
		
		<main id="main">
			<section id="contact" class="contact mb-5">
				<div class="container aos-init aos-animate" data-aos="fade-up">
					<div class="row">
						<div class="col-lg-12 text-center mb-5">
							<h1 class="page-title">COMMUNITY</h1>
						</div>
					</div>
				</div>
				
				<div class="text-center">
					<div class="btn-group btn-group-toggle col-sm-10" data-toggle="buttons">
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="notice" autocomplete="off" onclick="location.href='noticeList.do'" checked>Notice
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="qna" autocomplete="off" onclick="location.href='qnaList.do'">1:1 문의
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="review" autocomplete="off" onclick="location.href='reviewList.do'">Review
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div style="width: 80%; margin: 0 auto;">
					<div class="card-header">
						<div class="card-tools">
							<div class="input-group input-group-sm">
								<select id="searchType" class="form-control">
									<option value="noticeTitle">제목</option>
									<option value="noticeContent">내용</option>
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
								<col width="60%" />
								<col width="20%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>등록일시</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${noticeList}" varStatus="status">
									<tr onclick="location.href='noticeInfo.do?noticeId=${item.noticeId}'">
										<td>${item.noticeIdx}</td>
										<td style="text-align: left;">${item.noticeTitle}</td>
										<td><fmt:formatDate value="${item.noticeDate}" pattern="yyyy-MM-dd"/></td>
										<td>${item.noticeView}</td>
									</tr>
								</c:forEach>
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
</body>
</html>