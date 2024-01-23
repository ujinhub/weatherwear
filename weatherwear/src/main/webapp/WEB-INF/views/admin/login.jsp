<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeatherWear 관리자</title>

<!-- Font Awesome -->
<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
<!-- icheck bootstrap -->
<link href="resources/admin/AdminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<a href="login.mdo" class="h1">WeatherWear</a>
			</div>
			<div class="card-body login-card-body">
				<p class="login-box-msg">관리자 로그인</p>
				<form action="loginProc.mdo" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="id" name="adminId" placeholder="ID">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" name="adminPwd" placeholder="Password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>						
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="remember">
								<label for="remember">아이디 저장</label>
							</div>
						</div>
					</div>
					<div class="text-center mb-3">
						<button type="submit" class="btn btn-primary btn-block">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
<!-- jQuery -->
<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>

<script src="resources/util/js/login.js"></script>
<script>
	$(document).ready(function() {
		<c:if test="${msg != '' && msg != null}">
			alert('${msg}');
		</c:if>	
	});
</script>
</body>
</html>