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
							
			<c:if test="${info.qnaSecCheck == 'Y'}">
				<section id="checkSection" style="width: 50%; margin: 0 auto;">
					<div class="card">
						<div class="card-header" style="margin: 0 auto;">
							<h3 class="card-title"><b>비밀글보기</b></h3>
						</div>
						<div class="card-body text-center">
							<span>이 글은 비밀글입니다.</span><br>
							<span><b>비밀번호를 입력해주세요.</b></span>
							<input type="hidden" id="qnaId" name="qnaId" value="${info.qnaId}">
							<div class="form-group row col-lg-10" style="margin: 5% 15%;">
								<label for="qnaSecPwd" class="col-sm-3 col-form-label">비밀번호</label>
								<div class="col-sm-6">
									<input type="password" class="form-control" id="qnaSecPwd" name="qnaSecPwd">
								</div>
							</div>
							<div class="form-group row col-lg-8" style="margin-left: 27%;">
								<div class="col-sm-4">
									<button type="button" class="btn btn-flat btn-block btn-outline-dark" onclick="history.go(-1)">목록</button>
								</div>
								<div class="col-sm-4">
									<button type="button" id="btnCheckPwd" class="btn btn-flat btn-block btn-dark">확인</button>
								</div>
							</div>
						</div>
					</div>
				</section>
			</c:if>

			<section id="contentSection" style="margin: 0 auto; width: 80%; display: none;">
				<div>
					<div class="card-header">
						<b>${info.qnaTitle}</b>
						<div class="card-tools">
							작성자: ${fn:substring(info.clientId, 0, 2)}
									<c:forEach begin="2" end="${fn:length(info.clientId)}" step="1">*</c:forEach>&nbsp;&nbsp;
							작성일: <fmt:formatDate value="${info.qnaDate}" pattern="yyyy-MM-dd"/>
						</div>
					</div>
					<div class="card-header">
						문의유형: ${info.qnaType}
					</div>
					<div class="card-header">
						<div class="card-body mg-2" style="width: 60%; margin-left: 30px;">
							${info.qnaContent}
						</div>
					</div>
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
					<div class="card-footer float-right mg-2">
						<button type="button" class="btn btn-flat btn-outline-dark" onclick="history.go(-1)"><i class="fas fa-bars"></i>&nbsp;&nbsp;목록</button>
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
	
	<script>
		$(document).ready(function() {
			<c:if test="${info.qnaSecCheck == 'N'}">
				$('#checkSection').hide();
				$('#contentSection').show();
			</c:if>
			
			$('#btnCheckPwd').on('click', function() {
				$.ajax({
					url: 'checkQnaPwd.do',
					type: 'post',
					async: false,
					dataType: 'json',
					data: 
					{ 	
						qnaId: $('#qnaId').val(),
						qnaSecPwd: $('#qnaSecPwd').val(),
					},
					success: function(res){
						if(res.code == -1) {
							playToast(res.message, 'error');
							return;
						}
						
						if(res.code == 1) {
							$('#checkSection').hide();
							$('#contentSection').show();
						} 
						return;
					},
					error : function(error){
						playToast(error.message, 'error');
					}
						
				});
			});
		})
	</script>
</body>
</html>