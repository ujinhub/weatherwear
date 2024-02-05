<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	    <!-- Navbar -->
	    <nav class="main-header navbar navbar-expand-md navbar-light navbar-whilte">
	    	<div class="container">
	    		<a href="main.mdo" class="navbar-brand">
	    			<img src="resources/admin/images/logo.png" alt="Logo" class="brand-image">
	    			<span class="brand-text font-weight-light"><b>WeatherWear</b></span>
	    		</a>
	    		
	    		<button class="navbar-toggler order-1" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	    			<span class="navbar-toggler-icon"></span>
	    		</button>
	    		
	    		<div class="collapse navbar-collapse order-3" id="navbarCollapse">
	    			<!-- Left navbar links -->
	    			<ul class="navbar-nav">
	    				<li class="nav-item">
							<a href="#" class="nav-link">통계</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="productList.mdo" class="nav-link">상품</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="orderList.mdo" class="nav-link">주문</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="deliveryList.mdo" class="nav-link">배송</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="qnaList.mdo" class="nav-link">문의</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="noticeList.mdo" class="nav-link">공지</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="clientList.mdo" class="nav-link">회원</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="adminList.mdo" class="nav-link">관리자</a>    				
	    				</li>
	    				<li class="nav-item">
							<a href="termsList.mdo" class="nav-link">약관</a>    				
	    				</li>
	    			</ul>
	    			
	    			<!-- Search Form 
	    			<form class="form-inline ml-0 ml-md-3">
	    				<div class="input-group input-group-sm">
	    					<input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
	    					<div class="input-group-append">
	    						<button class="btn btn-navbar" type="submit">
	    							<i class="fas fa-search"></i>
	    						</button>	
	    					</div>
	    				</div>
	    			</form>
	    			-->
	    		</div>
	    		
	    		<!-- Right navbar links -->
	    		<ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
	    			<!-- Message Dropdown Menu -->
	    			<li class="nav-item dropdown">
	    				<a class="nav-link" data-toggle="dropdown" href="#">
	    					<i class="fas fa-comments"></i>
	    					<span class="badge badge-danger navbar-badge">3</span>
	    				</a>
	    				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
	    					<a href="#" class="dropdown-item">
	    						<!-- Message Start -->
	    						<div class="media">
	    							<div class="media-body">
	    								<h3 class="dropdown-item-title">
	    									ABC
	    									<span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
	    								</h3>
	    								<p class="text-sm">text-danger</p>
	    								<p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
	    							</div>
	    						</div><!-- Message End -->
	    					</a>
	    					<div class="dropdown-divider"></div>
	    					<a href="#" class="dropdown-item">
	    						<!-- Message Start -->
	    						<div class="media">
	    							<div class="media-body">
	    								<h3 class="dropdown-item-title">
	    									QWE ERT
	    									<span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
	    								</h3>
	    								<p class="text-sm">text-muted</p>
	    								<p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 6 Hours Ago</p>
	    							</div>
	    						</div><!-- Message End -->
	    					</a>
	    					<div class="dropdown-divider"></div>
	    					<a href="#" class="dropdown-item">
	    						<!-- Message Start -->
	    						<div class="media">
	    							<div class="media-body">
	    								<h3 class="dropdown-item-title">
	    									POUI DIS
	    									<span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
	    								</h3>
	    								<p class="text-sm">text-warning</p>
	    								<p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 12 Hours Ago</p>
	    							</div>
	    						</div><!-- Message End -->
	    					</a>
	    					<div class="dropdown-divider"></div>
	    					<a href="#" class="dropdown-item dropdown-footer">See All Message</a>
	    				</div>
	    			</li>
	    			
	    			<!-- Notifications Dropdown Menu -->
					<li class="nav-item dropdown">
						<a class="nav-link" data-toggle="dropdown" href="#">
							<i class="far fa-bell"></i>
							<span class="badge badge-warning navbar-badge">15</span>
						</a>
						<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
							<span class="dropdown-header">15 Notifications</span>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item">
								<i class="fas fa-envelope mr-2"></i> 4 new question
								<span class="float-right text-muted text-sm">3 mins</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item">
								<i class="fas fa-users mr-2"></i> 8 client join
								<span class="float-right text-muted text-sm">12 hours</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item">
								<i class="fas fa-file mr-2"></i> 3 new reports
								<span class="float-right text-muted text-sm">2 days</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
						</div>
					</li>
					
					<!-- User Config -->
					<li class="nav-item dropdown">
						<a class="nav-link" data-toggle="dropdown" href="#">
							<i class="far fa-user"></i>
						</a>
						<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
							<span class="dropdown-header"> <b>${userInfo.adminName}</b>님 (${userInfo.gradeId}) </span>
							<div class="dropdown-divider"></div>
							<a href="adminInfo.mdo?adminId=${userInfo.adminId}" class="dropdown-item">
								<i class="fas fa-address-card mr-2"></i> My Profile
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item">
								<i class="fas fa-info-circle mr-2"></i> Settings
							</a>
							<div class="dropdown-divider"></div>
							<a href="logoutProc.mdo" class="dropdown-item">
								<i class="fas fa-arrow-right mr-2"></i> Log Out
							</a>
						</div>
					</li>
	    		</ul>
	    		
	    	</div>
	    </nav>
	    