<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<!-- summernote -->
	<link href="resources/util/plugins/summernote/summernote-lite.css" rel="stylesheet">
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
							<input type="radio" name="options" id="notice" autocomplete="off" onclick="location.href='noticeList.do'">Notice
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="qna" autocomplete="off" onclick="location.href='qnaList.do'" checked>1:1 문의
						</label>
						<label class="btn btn-flat btn-outline-dark">
							<input type="radio" name="options" id="review" autocomplete="off" onclick="location.href='reviewList.do'">Review
						</label>
					</div>
				</div>
			</section>
			
			<section style="margin: 0 auto; width: 80%;">
				<form id="qnaInfoForm" action="qnaUpdateProc.do" method="post" enctype="multipart/form-data">
					<input type="hidden" id="key" value="qna">
					<input type="hidden" id="qnaId" name="qnaId" value="${info.qnaId}">
					<div class="card-body">
						<div class="form-group row">
							<input type="hidden" name="clientId" value="${userInfo.clientId}">
							<label for="clientName" class="col-sm-2 col-form-label">이름</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" value="${userInfo.clientName}" readonly>
							</div>
						</div>
						<div class="form-group row">
							<label for="qnaDate" class="col-sm-2 col-form-label">작성일</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="qnaDate" value="${info.qnaDate}" readonly>
							</div>
						</div>
						<div class="form-group row">
							<label for="qnaType" class="col-sm-2 col-form-label">분류</label>
							<div class="col-sm-3">
								<select class="form-control" id="qnaType" name="qnaType" required>
		                        	<option value="">선&nbsp;&nbsp;택</option>
			                        <option value="회원정보" <c:if test="${info.qnaType == '회원정보'}">selected</c:if>>회원정보</option>
		                        	<option value="상품확인" <c:if test="${info.qnaType == '상품확인'}">selected</c:if>>상품확인</option>
			                        <option value="주문/결제" <c:if test="${info.qnaType == '주문/결제'}">selected</c:if>>주문/결제</option>
			                        <option value="배송" <c:if test="${info.qnaType == '배송'}">selected</c:if>>배송</option>
			                        <option value="교환/취소(반품)" <c:if test="${info.qnaType == '교환/취소(반품)'}">selected</c:if>>교환/취소(반품)</option>
			                        <option value="서비스" <c:if test="${info.qnaType == '서비스'}">selected</c:if>>서비스</option>
		                        </select>
							</div>
						</div>
						<div class="form-group row">
							<label for="qnaTitle" class="col-sm-2 col-form-label">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="qnaTitle" name="qnaTitle" value="${info.qnaTitle}">
							</div>
						</div>
						<div class="form-group row">
							<label for="qnaContent" class="col-sm-2 col-form-label">내용</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="summernote" name="qnaContent">${info.qnaContent}</textarea>
							</div>
						</div>
					</div>
					<div class="card mg-3">
						<div class="card-header">
							<c:if test="${info.qnaStatus == '답변완료'}">
								<div class="card-tools">
									답변등록일: <fmt:formatDate value="${info.qnaDate}" pattern="yyyy-MM-dd"/>
								</div>
								<div class="card-body mg-2" style="width: 60%; margin-left: 30px;">
									${info.qnaAnswer}
								</div>
							</c:if>
							<c:if test="${info.qnaStatus == '답변대기'}">
								<div class="card-body mg-2" style="width: 60%; margin-left: 30px;">
									<span>등록된 답변이 없습니다.</span>
								</div>
							</c:if>
						</div>
					</div>
					<div class="card-footer mg-2">
						<div class="row">
							<div class="float-right col-sm-1">
								<button type="button" class="btn btn-block btn-outline-dark" onclick="history.go(-1)"><i class="fas fa-bars"></i>&nbsp;&nbsp;목록</button>
							</div>
							<div class="float-left col-sm-1">
								<button type="submit" class="btn btn-block btn-dark">수정</button>
							</div>
							<div class="float-left col-sm-1">
								<button type="button" class="btn btn-block btn-outline-dark" id="deleteQna">삭제</button>
							</div>
						</div>
					</div>
				</form>
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
	
	<!-- summernote -->
	<script src="resources/util/plugins/summernote/summernote-lite.js"></script>
	
	<script src="resources/util/js/summernote.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#deleteQna').on('click', function() {
				if(confirm("문의를 삭제하시겠습니까?")) {
					$.ajax({
						url: "qnaDeleteProc.do",
						type: "post",
						dataType: 'json',
						data: $('#qnaInfoForm').serialize(),
						success: function(res){
							console.log(res);
							if(res.code == -1) {
								playToast(res.message, 'error');
								return;
							}else if (res.code == -2){
								playConfirm(res.message, res.data, 'question', '로그인하기', '취소하기', "location.href='login.do'", null);
							} 
							
							if(res.code == 1) {
								playToast(res.message, 'success');
								location.href = "myqnaList.do";
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