<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/w2/resources/client/client_js/client_findInfo.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_findInfo_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="client">
				<h2>정보 찾기</h2>
				<ul class="findInfo_ul">
					<li><a id="findId">아이디 찾기</a></li>
					<li>|</li>
					<li><a id="findPwd">비밀번호 찾기</a></li>
				</ul>
	<!-- 전화번호로 아이디 찾기 -->
				<div id="findInfo_id_num">
					<form id="findIdNum" method="post" autocapitalize="none">
						<div class="client_table findIdNum">
							<ul class="client_table_ul">
								<li class="client_table_th">이름 : </li>
								<li class="client_table_td"><input type="text" name="clientName" placeholder="이름"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">전화번호 : </li>
								<li class="client_table_td"><input type="text" name="clientNum" placeholder="전화번호"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><input type="submit" class="btn_form" value="아이디 찾기"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><a id="findId_email">이메일로 아이디 찾기</a></li>
							</ul>
						</div>
					</form>
				</div>
	<!-- 이메일로 아이디 찾기 -->
				<div id="findInfo_id_email">
					<form id="findIdEmail" method="post" autocapitalize="none">
						<div class="client_table">
							<ul class="client_table_ul">
								<li class="client_table_th">이름 : </li>
								<li class="client_table_td"><input type="text" name="clientName" placeholder="이름"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">이메일 : </li>
								<li class="client_table_td"><input type="text" name="clientEmail" placeholder="이메일"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><input type="submit" class="btn_form" value="아이디 찾기"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><a id="findId_num">전화번호로 아이디 찾기</a></li>
							</ul>
						</div>
					</form>
				</div>
	<!-- 전화번호로 비밀번호 찾기 -->
				<div id="findInfo_pwd_num">
					<form id="findPwdNum" method="post" autocapitalize="none">
						<div class="client_table">
							<ul class="client_table_ul">
								<li class="client_table_th">이름 : </li>
								<li class="client_table_td"><input type="text" name="clientName" placeholder="이름"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">아이디 : </li>
								<li class="client_table_td"><input type="text" name="clientId" placeholder="아이디"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">전화번호 : </li>
								<li class="client_table_td"><input type="text" name="clientNum" placeholder="전화번호"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><input type="submit" class="btn_form" value="비밀번호 찾기"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><a id="findPwd_email">이메일로 비밀번호 찾기</a></li>
							</ul>
						</div>
					</form>
				</div>
	<!-- 이메일로 비밀번호 찾기 -->
				<div id="findInfo_pwd_email">
					<form id="findPwdEmail" method="post" autocapitalize="none">
						<div class="client_table">
							<ul class="client_table_ul">
								<li class="client_table_th">이름 : </li>
								<li class="client_table_td"><input type="text" name="clientName" placeholder="이름"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">아이디 : </li>
								<li class="client_table_td"><input type="text" name="clientId" placeholder="아이디"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">이메일 : </li>
								<li class="client_table_td"><input type="text" name="clientEmail" placeholder="이메일"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><input type="submit" class="btn_form" value="비밀번호 찾기"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_li col"><a id="findPwd_num">전화번호로 비밀번호 찾기</a></li>
							</ul>
						</div>
					</form>
				</div>
				<div id="result_div"></div>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>