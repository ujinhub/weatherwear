<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEATHERWEAR</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="resources/client/client_js/client_main.js"></script>
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_base_style.css">
	<link rel="stylesheet" href="/w2/resources/client/client_css/client_main_style.css">
</head>
<script>
$(document).ready(function() {
	let wId = 800;//${ weather3_id };
	image = '';
	
	console.log(wId);
	selectWeather(wId);
});
</script>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/client/base/client_header.jspf" %>
		<div class="body">
			<div id="weather">
				<article id="province">
					<ul class="w_province">
					<li><a id="prov" onClick="setProvince('seoul')">서울</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('chuncheon')">춘천</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('suwon')">수원</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('incheon')">인천</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('daegu')">대구</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('busan')">부산</a></li>
					<li> | </li>
					<li><a id="prov" onClick="setProvince('jeju')">제주</a></li>
					</ul>
					<br><br><br><br>
				</article>
				<article id="today">
					<span> </span><span> </span>
					<span id="today_day">
						<span id="provSelect">
							<c:if test="${ param.province == 'seoul' || param.province == null }">서울</c:if> 
							<c:if test="${ param.province == 'chuncheon' }">춘천</c:if> 
							<c:if test="${ param.province == 'suwon' }">수원</c:if> 
							<c:if test="${ param.province == 'incheon' }">인천</c:if> 
							<c:if test="${ param.province == 'daegu' }">대구</c:if> 
							<c:if test="${ param.province == 'busan' }">부산</c:if> 
							<c:if test="${ param.province == 'jeju' }">제주</c:if> 
							&nbsp;&nbsp;&nbsp;
						</span>
						${ today.weatherday }요일
					</span><br>
					<span id="today_date"> ${ today.wdate } </span>
				</article>
				<span id="today_temp"><strong> ${ today.temp_min } °C / ${ today.temp_max } °C</strong></span>
				<article id="week">
					<table id="weekTable">
						<tr>
							<th>${ weather1.weatherday }요일</th>
							<th>${ weather2.weatherday }요일</th>
							<th>${ today.weatherday }요일</th>
							<th>${ weather4.weatherday }요일</th>
							<th>${ weather5.weatherday }요일</th>
						</tr>
						<tr>
							<td> ${ weather1.temp_min } / ${ weather1.temp_max }</td>
							<td> ${ weather2.temp_min } / ${ weather2.temp_max }</td>
							<td> ${ today.temp_min } / ${ today.temp_max }</td>
							<td> ${ weather4.temp_min } / ${ weather4.temp_max }</td>
							<td> ${ weather5.temp_min } / ${ weather5.temp_max }</td>
						</tr>
					</table>
				</article>
			</div>
			<div class="clear_con"></div>
				
			<section class="best">
				<section id="contents">
					<article>
						<h2 class="client_main_title">Best View</h2>
						<c:forEach var="product" items="${ proBestView }">
							<ul class="product">
								<li><a href="productInfo.do?proId=${product.proId}"><img class="pro_img" src="${ product.imageDir }${ product.imageName }" onmouseover="this.style.opacity=0.5;" onmouseout="this.style.opacity=1;"></a></li>
								<li class="name"><a href="productInfo.do?proId=${product.proId}">${product.proName}</a></li>
								<li class="price">&#8361; <fmt:formatNumber value="${product.proPrice}" pattern="###,###" /></li>
							</ul>
						</c:forEach>           	
					</article>
				</section>
				<section id="contents">
					<article>
						<h2 class="client_main_title">Best Sell</h2>
						<c:forEach var="product" items="${ proBestSell }">
							<ul class="product">
								<li><a href="productInfo.do?proId=${product.proId}"><img class="pro_img" src="${ product.imageDir }${ product.imageName }" onmouseover="this.style.opacity=0.5;" onmouseout="this.style.opacity=1;"></a></li>
								<li class="name"><a href="productInfo.do?proId=${product.proId}">${product.proName}</a></li>
								<li class="price">&#8361; <fmt:formatNumber value="${product.proPrice}" pattern="###,###" /></li>
							</ul>
						</c:forEach>           	
					</article>
				</section>
				<section id="contents">
					<article>
						<h2 class="client_main_title">New Items</h2>
						<c:forEach var="product" items="${ proNew }">
							<ul class="product">
								<li><a href="productInfo.do?proId=${product.proId}"><img class="pro_img" src="${ product.imageDir }${ product.imageName }" onmouseover="this.style.opacity=0.5;" onmouseout="this.style.opacity=1;"></a></li>
								<li class="name"><a href="productInfo.do?proId=${product.proId}">${product.proName}</a></li>
								<li class="price">&#8361; <fmt:formatNumber value="${product.proPrice}" pattern="###,###" /></li>
							</ul>
						</c:forEach>           	
					</article>
				</section>
			</section>
		</div>
		<%@ include file="/WEB-INF/views/client/base/client_footer.jspf" %>
	</div>
</body>
</html>