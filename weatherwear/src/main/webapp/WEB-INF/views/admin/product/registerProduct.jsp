<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeatherWear 관리자</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!-- Font Awesome -->
<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
<!-- Theme style -->
<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
<!-- dropZone -->
<link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css"/>
<link rel="stylesheet" href="resources/util/css/dropzone.css"/>
<script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<style>
	#option-div { display: none;}
</style>
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="../header.jsp" %>
	
		<div class="content-header">
			<section class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>상품 등록</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="main.mdo">메인</a></li>
								<li class="breadcrumb-item active">상품 등록</li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="container">
					<div class="row">
						<div class="card card-primary" style="width:48%;">
							<div class="card-header">
								<h3 class="card-title">Product</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<h4>Information</h4>
								<div class="form-group" id="dropzone">
									<form action="https://httpbin.org/post" class="dropzone needsclick" id="demo-upload">
										<div class="dz-message needsclick">
											<span class="text">
												<img src="resources/util/image/dropzone_camera.png" alt="Camera" />
												메인 이미지를 등록하세요 <br><code>DROPZONE 사용하고 싶다</code>
											</span>
											<span class="plus">+</span>
										</div>
									</form>
								</div>
								<div class="form-group">
									<label for="exampleSelectBorder">카테고리</label>
									<select class="custom-select form-control-border" id="exampleSelectBorder">
										<option value="11">OUTER</option>
										<option value="12">TOP</option>
										<option value="13">PANTS</option>
										<option value="14">SKIRTS</option>
										<option value="15">DRESS</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputBorderWidth2"> 상품명<code></code></label>
									<input type="text" name="productName" class="form-control form-control-border border-width-2" id="exampleInputBorderWidth2" placeholder="상품명">
								</div>
								<div class="form-group">
									<label for="exampleInputBorderWidth2"> 공급가<code> 과세율 0.1, 마진율 1.5 >> 판매가 : <span id="cost"></span></code></label>
									<input type="text" name="productPrimeCost" class="form-control form-control-border border-width-2" id="exampleInputBorderWidth2" placeholder="공급가" onchange="checkCost()">
								</div>
								<h4>Option Setting</h4>
								<div class="form-group">
									<label for="exampleInputRounded0">색상<code></code></label>
									<input type="text" class="form-control rounded-0" id="exampleInputRounded0" name="optionColor" placeholder="블랙, 화이트">
									<input type="hidden" name="optionColorList">
								</div>
								<div class="form-group">
									<label for="exampleInputRounded0">사이즈<code></code></label>
									<input type="text" class="form-control rounded-0" id="exampleInputRounded0" name="optionSize" placeholder="S, M, L">
									<input type="hidden" name="optionSizeList">
								</div>
								<button type="button" class="btn btn-block btn-outline-primary" id="apply-option">옵션적용</button>
							</div>
							<!-- /.card-body -->
						</div>
						<div style="width:20px;"></div>
						<div class="card card-secondary" style="width:48%;" id="option-div">
							<div class="card-header">
								<h3 class="card-title">Option/Stock</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<h4>Stock</h4>
								<div class="card-body p-0" style="min-height: 600px; height:auto;">
									<table class="table table-sm">
										<colgroup>
											<col width="100px"/>
											<col width="100px"/>
											<col width="100px"/>
										</colgroup>
										<thead>
											<tr>
												<th>색상</th>
												<th>사이즈</th>
												<th>수량</th>
											</tr>
										</thead>
										<tbody class="option-stock">
											
										</tbody>
									</table>
								</div>
								<div class="input-group mb-3">
									<!-- /btn-group -->
									<input type="number" class="form-control" value="10" name="allStock">
									<input type="hidden" class="form-control" name="stockList">
									<div class="input-group-prepend">
										<button type="button" class="btn btn-primary" id="applyStockAll">재고 일괄 적용</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
					</div>
					<div class="card card-outline card-primary">
						<div class="card-header">
							<h3 class="card-title">상세 내용</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<div id="summernote"></div>
						</div>
						<!-- /.card-body -->
			        </div>
					<div class="input-group-prepend">
						<button type="button" class="btn btn-primary" onclick="submit('register')">등록하기</button>
					</div>
				</div>
			</section>
		</div>		
		<%@ include file="../footer.jsp" %>
	</div>
<!-- jQuery -->
<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="resources/admin/AdminLTE/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Summernote -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<script src="resources/admin/js/manageProduct.js"></script>
<script src="resources/admin/js/uploadImage_registerProduct.js"></script>
</body>
</html>