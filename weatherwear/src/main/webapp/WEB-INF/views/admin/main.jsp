<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<div class="col-lg-3 col-6">
							<div class="small-box bg-info">
								<div class="inner">
									<h3>150</h3>
									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						
						<div class="col-lg-3 col-6">
							<div class="small-box bg-success">
								<div class="inner">
									<h3>53<sup style="font-size: 20px">%</sup></h3>
									<p>Bounce Rate</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						
						<div class="col-lg-3 col-6">
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>32</h3>
									<p>Client Registrations</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						
						<div class="col-lg-3 col-6">
							<div class="small-box bg-danger">
								<div class="inner">
									<h3>65</h3>
									<p>Unique Visitors</p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					
					<!-- Main row -->
					<div class="row">
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Online Store Visitors</h3>
										<a href="javascript:void(0);">View Report</a>
									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span class="text-bold text-lg">820</span>
											<span>Visitors Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">
											<span class="text-success">
												<i class="fas fa-arrow-up"></i> 12.5%
											</span>
											<span class="text-muted">Since last week</span>
										</p>
									</div>
									<div class="position-relative mb-4">
										<canvas id="visitors-chart" height="200"></canvas>
									</div>
									<div class="d-flex flex-row justify-content-end">
										<span class='mr-2'>
											<i class="fas fa-square text-primary"></i> This Week
										</span>
										<span>
											<i class="fas fa-square text-gray"></i> Last Week
										</span>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-lg-6">
							<div class="card">	
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Sales</h3>
										<a href="javascript:void(0);">View Report</a>
									</div>
									<div class="card-body">
										<div class="d-flex">
											<p class="d-flex flex-column">
												<span class="text-bold text-lg">￦3,560,000</span>
												<span>Sales Over Time</span>
											</p>
											<p class="ml-auto d-flex flex-column text-right">
												<span class="text-success">
													<i class="fas fa-arrow-up"></i> 33.1%
												</span>
												<span class="text-muted">Since last month</span>
											</p>
										</div>
										<div class="position-relative mb-4">
											<canvas id="sales-chart" height="200"></canvas>
										</div>
										<div class="d-flex flex-row justify-content-end">
											<span class="mr-2">
												<i class="fas fa-square text-primary"></i> This year
											</span>
											<span>
												<i class="fas fa-square text-gray"></i> Last year
											</span>
										</div>
									</div>
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
<!-- Bootstrap 4 -->
<script src="resources/admin/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/admin/AdminLTE/dist/js/adminlte.js"></script>

<!-- chart.js -->
<script src="resources/admin/AdminLTE/plugins/chart.js/Chart.min.js"></script>
<!-- dashboard demo -->
<script src="resources/admin/AdminLTE/dist/js/dashboard3.js"></script>

</body>
</html>