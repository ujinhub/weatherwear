<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/w2/resources/client/client_js/client_signup.js"></script>
	<script src="/w2/resources/util/util_js/util_checkbox.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_signup_style.css">
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="client_body">
				<h2>회원 가입</h2>
				<form action="clientSignup.do" method="post" class="client_form">
	<!-- 약관 동의 -->
					<div class="client_term_box checklist">
						<p><b>약관 동의</b> > 개인 정보 입력</p>
						<input type="checkbox" value="term01" id="checkAll" name="checkAll">
						<label for="checkAll"> 모든 항목에 동의합니다.</label><br>
						<div class="client_term_list">
							<input type="checkbox" value="term01" id="check01" name="check01" class="check">
							<label for="check01"> 서비스 이용 약관 <font color="red">[ 필수 ]</font></label><br>
							<span class="term_content">
								내용
							</span>
						</div>
						<div class="client_term_list">
							<input type="checkbox" value="clientEmailCheck" id="check02" name="clientEmailCheck" class="check">
							<label for="check02"> 이메일 동의</label><br>
							<span class="term_content">
								내용
							</span>
						</div>
						<input type="button" class="term_next" value="다음" style="width:100%; height:60px;">
					</div>
	<!-- 개인 정보 -->
					<div class="client_info_box">
						<p><span class="back_term">약관 동의</span> > <b>개인 정보 입력</b></p>
						<div class="client_table">
							<ul class="client_table_ul">
								<li class="client_table_th">아이디 : </li>
								<li class="client_table_td">
									<input type="text" name="clientId" id="clientId" required="required" placeholder="아이디" onchange="check('cId')">
								</li>
								<li class="client_table_info">
									<span id="checkId"></span>
								</li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">비밀번호 : </li>
								<li class="client_table_td">
									<input type="password" name="clientPwd" id="clientPwd" required="required" placeholder="비밀번호">
								</li>
								<li class="client_table_info"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">비밀번호 확인 : </li>
								<li class="client_table_td">
									<input type="password" name="clientCheckPwd" id="clientCheckPwd" required="required" placeholder="비밀번호 확인" onchange="checkPwd(this)">
								</li>
								<li class="client_table_info">
									<span id="checkPw"></span>
								</li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">이름 : </li>
								<li class="client_table_td">
									<input type="text" name="clientName" id="clientName" required="required" placeholder="이 름">
								</li>
								<li class="client_table_info"></li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">전화번호 : </li>
								<li class="client_table_td">
									<input type="text" name="clientNum" id="clientNum" required="required" placeholder="01012341234" maxlength="11">
								</li>
								<li class="client_table_info">
									<input type="button" value="전화번호 인증">
								</li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">이메일 : </li>
								<li class="client_table_td">
									<input type="email" name="clientEmail" id="clientEmail" required="required" placeholder="이메일" onchange="check('cEmail')">
								</li>
								<li class="client_table_info">
									<span id="checkEmail"></span>
								</li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">닉네임 : </li>
								<li class="client_table_td">
									<input type="text" name="clientNickName" id="clientNickName" required="required" placeholder="닉네임" onchange="check('cNickName')">
								</li>
								<li class="client_table_info">
									<span id="checkNickName"></span>
								</li>
							</ul>
							<ul class="client_table_ul">
								<li class="client_table_th">생년월일 : </li>
								<li class="client_table_td">
									<input type="date" name="clientBirth" id="clientBirth" required="required">
								</li>
								<li class="client_table_info">
									<font size="1">생일 쿠폰 발급을 위한 정보수집입니다.</font>
								</li>
							</ul>
						</div>
						<input type="submit" value="회원가입" style="width:100%; height:60px;">
					</div>

				</form>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>