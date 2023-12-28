<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/w2/resources/client/client_js/client_community.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_community_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="community_body">
				<ul class="community_menu">
					<li id="notice_btn">Notice</li>
					<li id="review_btn">Review</li>
					<li id="qna_btn">QnA</li>
				</ul>				
	<!-- NOTICE -->
				<div class="noticeList">
					<h2>NOTICE</h2>
					<div class="notice_table">
						<ul class="notice_header">
							<li class="notice_head no">NO</li>
							<li class="notice_head title">TITLE</li>
							<li class="notice_head writer">WRITER</li>
							<li class="notice_head date">DATE</li>
							<li class="notice_head view">VIEW</li>
						</ul>
						<ul class="notice_content">
							<li class="notice_con no">1</li>
							<li class="notice_con title">
	<!-- 공지 상세로 이동 -->							<a href="#" class="community_a">배송 안내</a>
							</li>
							<li class="notice_con writer">관리자</li>
							<li class="notice_con date">2023-12-01</li>
							<li class="notice_con view">53</li>
						</ul>
					</div>
				</div>	
	<!-- Review -->
				<div class="reviewList">
					<h2>REVIEW</h2>
					<div class="review_table">
						<ul class="review_header">
							<li class="review_head no">NO</li>
							<li class="review_head product">PRODUCT</li>
							<li class="review_head con">CONTENT</li>
							<li class="review_head writer">WRITER</li>
							<li class="review_head date">DATE</li>
							<li class="review_head view">VIEW</li>
						</ul>
						<ul class="review_content">
							<li class="review_con no">1</li>
							<li class="review_con product">
								<a href="#" class="community_a">
	<!-- 상품상세로 이동 -->								상품123(이미지)
								</a>
							</li>
							<li class="review_con content">
								<a href="#" class="community_a">
								<span>
	<!-- 해당 리뷰로 이동 -->								상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 상품 질이 매우 좋네요 
								</span>
								</a>
							</li>
							<li class="review_con writer">client</li>
							<li class="review_con date">2023-12-01</li>
							<li class="review_con view">53</li>
						</ul>
					</div>
				</div>	
	<!-- QnA -->
				<div class="qnaList">
					<h2>QnA</h2>
					<div class="qna_table">
						<ul class="qna_header">
							<li class="qna_head no">NO</li>
							<li class="qna_head title">TITLE</li>
							<li class="qna_head writer">WRITER</li>
							<li class="qna_head date">DATE</li>
							<li class="qna_head view">VIEW</li>
						</ul>
						<ul class="qna_content">
							<li class="qna_con no">1</li>
							<li class="qna_con title">
								<a href="#" class="community_a">
	<!-- 문의 상세로 이동 -->								재고 문의
								</a>
							</li>
							<li class="qna_con writer">client</li>
							<li class="qna_con date">2023-12-01</li>
							<li class="qna_con view">2</li>
						</ul>
					</div>
				</div>
			</div>

		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>