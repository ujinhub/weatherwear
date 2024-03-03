<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WeatherWear 관리자</title>
	<!-- Font Awesome -->
	<link href="resources/admin/AdminLTE/plugins/fontawesome-free/css/all.min.css" rel="stylesheet">
	<!-- Ionicons -->
	<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
	<!-- Theme style -->
	<link href="resources/admin/AdminLTE/dist/css/adminlte.min.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-collapse layout-top-nav">
	<div class="wrapper">
		<%@ include file="header.jsp" %>
		
		<!-- Content Wrapper -->
		<div class="content-wrapper">
			<!-- Content Header -->
			<div class="content-header">
				<div class="container">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">WeatherWear Main</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item active">Main</li>
							</ol>
						</div>
					</div>
				</div>
			</div><!-- content-header -->
			
			<!-- Main content -->
			<section class="content">
				<div class="container">
					<div class="row">
						<div class="col-lg-4 col-6">
							<div class="small-box bg-info">
								<div class="inner">
									<h3>${ statisticInfo.newOrderCnt }</h3>
									<p>신규 주문</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="orderList.mdo?orderby=newOrder" class="small-box-footer">바로가기 <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						
						<div class="col-lg-4 col-6">
							<div class="small-box bg-success">
								<div class="inner">
									<h3>${ statisticInfo.usingCartCnt.totalUser }
										<sub style="font-size: 15px; font-weight: normal;">
											회원 : ${ statisticInfo.usingCartCnt.user } | 비회원 : ${ statisticInfo.usingCartCnt.unKnownUser }
										</sub></h3>
									<p>
										장바구니 사용자 수
									</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a class="small-box-footer"><i class="fas"></i></a>
							</div>
						</div>
						
						<div class="col-lg-4 col-6">
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>${ statisticInfo.newUserCnt }</h3>
									<p>신규 회원(일주일)</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a class="small-box-footer"><i class="fas"></i></a>
							</div>
						</div>
					</div>
					<!-- Main row -->
					<div class="row">
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">주간 결제 건 수</h3>
									</div>
								</div>
								<div class="card-body">
									<div class="position-relative mb-4">
										<canvas id="paymentChart" height="200" width="400"></canvas>
									</div>
									<c:forEach var="day" items="${ statisticInfo.paymentCnt }">
										<input type="hidden" id="payment_${ day.DATE }" value="${ day.SUM }">
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">주간 주문 금액</h3>
									</div>
								</div>
								<div class="card-body">
									<div class="position-relative mb-4">
										<canvas id="orderPriceChart" height="200" width="400"></canvas>
									</div>
									<c:forEach var="day" items="${ statisticInfo.DayOrderPrice }">
										<input type="hidden" id="orderPrice_${ day.date }" value="${ day.price }">
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">판매순 TOP10</h3>
									</div>
								</div>
								<div class="card-body">
									<c:forEach var="product" items="${ statisticInfo.bestSell }" varStatus="num">
										${ num.index+1 } &nbsp; <a href="productInfo.mdo?productId=${ product.productId }">${ product.productName } [${ product.productCnt }]</a><hr>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">조회순 TOP10</h3>
									</div>
								</div>
								<div class="card-body">
									<c:forEach var="product" items="${ statisticInfo.bestView }" varStatus="num">
										${ num.index+1 } &nbsp; <a href="productInfo.mdo?productId=${ product.productId }">${ product.productName } [${ product.productView }]</a><hr>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		
		<%@ include file="footer.jsp" %>
	</div>

	<!-- jQuery -->
	<script src="resources/admin/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script	src="resources/util/plugins/sweetalert/jquery-lates.min.js"></script>
	<script src="resources/util/plugins/sweetalert/sweetalert2.js"></script>
	<!-- Bootstrap 4 -->
	<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>
	<!-- chart.js -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>
	<!-- dashboard demo -->
	<script src="resources/admin/AdminLTE/dist/js/dashboard3.js"></script>
	<script src="resources/admin/js/common.js"></script>
	
	<script>
		const paymentChart = document.getElementById("paymentChart");
		let paymentData = [];
		let paymentLabels = [];
		
		
		let labels = [];
		
		let today = new Date();
		paymentLabels.push(today.toISOString().split("T")[0]);
		labels.push(today.toISOString().split("T")[0]);
		
		for(let i=0; i<6; i++){
			let date = new Date(today.setDate(today.getDate()-1));
			paymentLabels.push(date.toISOString().split("T")[0]);
			labels.push(date.toISOString().split("T")[0]);
		}
		
		setChartInfo("line", "payment", "결제 건수(일)");
		setChartInfo("bar", "orderPrice", "결제 금액(일)");
		
		function setChartInfo(type, payment, title){
			let chart = document.getElementById(payment + "Chart");
			let value = [];
		
			for(let i=0; i<labels.length; i++){
				let count = $("#" + payment + "_" + labels[i]).val();
				if(count != null){
					value.push(count);
				} else {
					value.push(0);
				}
			}
			
			setNewChart(type, chart, labels, title, value, "red");
			
		}
		
		// 차트 생성
		function setNewChart(chartType, chartCanvas, labels, label, value, borderColor){
			let data = {
				labels: labels,
				datasets: [{
					axis: 'y',
					label: label,
					data: value,
					backgroundColor: "rgba(0,0,0,0)",
					borderColor: borderColor,
					borderWidth: 1
				}]
			};
			let config = {
				type: chartType,
				data: data,
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				},
			}; 
			new Chart(chartCanvas, config);
		}
	</script>
</body>
</html>