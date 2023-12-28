<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_login_style.css">
</head>
<body>		
	<c:if test="${result == 1}">
		<script>
			alert("가입이 완료되었습니다. 로그인페이지로 이동합니다.");
			location.href="clientLogin.do";
		</script>
	</c:if>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div class="client">
				<h2>Login</h2>
				<form action="clientLogin.do" method="post">
					<div class="client_table">
						<ul class="client_table_ul">
							<li class="client_table_th">아이디 : </li>
							<li class="client_table_td"><input type="text" name="clientId" placeholder="아이디"></li>
						</ul>
						<ul class="client_table_ul">
							<li class="client_table_th">비밀번호 : </li>
							<li class="client_table_td"><input type="password" name="clientPwd" placeholder="비밀번호"></li>
						</ul>
						<ul class="client_table_ul">
							<li class="client_table_li login_btn"><input type="submit" class="btn_form" value="로 그 인"></li>
						</ul>
						<ul class="client_table_ul">
							<li class="client_table_li"><input type="button" value="아이디/비밀번호 찾기" onclick="location.href='clientFindInfo.do'"></li>
							<li class="client_table_li"><input type="button" value="회 원 가 입" onclick="location.href='clientSignup.do'"></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>