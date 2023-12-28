$(document).ready(function(){
	const order_btn = document.querySelector("#order_btn");
	const wish_btn = document.querySelector("#wish_btn");
	const coupon_btn = document.querySelector("#coupon_btn");
	const point_btn = document.querySelector("#point_btn");
	const review_btn = document.querySelector("#review_btn");
	const modify_btn = document.querySelector("#modify_btn");
	
	const orderList = document.querySelector(".orderList");
	const wishList = document.querySelector(".wishList");
	const couponList = document.querySelector(".couponList");
	const pointList = document.querySelector(".pointList");
	const reviewList = document.querySelector(".reviewList");
	
	const showForm = (target) => {
		orderList.style.display = 'none';	
		wishList.style.display = 'none';	
		couponList.style.display = 'none';
		pointList.style.display = 'none';
		reviewList.style.display = 'none';
		
		target.style.display="flex";
	}
	
	const select = (target) => {
		order_btn.style.color = "silver";
		wish_btn.style.color = "silver";
		coupon_btn.style.color = "silver";
		point_btn.style.color = "silver";
		review_btn.style.color = "silver";
		
		target.style.color="dimgrey";
	}
	
	// 주문 내역 메뉴
	order_btn.addEventListener('click', () => {
		showForm(orderList);
		select(order_btn);
	});
	
	// 즐겨찾기 메뉴
	wish_btn.addEventListener('click', () => {
		showForm(wishList);
		select(wish_btn);
	});
	
	// 쿠폰 리스트 메뉴
	coupon_btn.addEventListener('click', () => {
		showForm(couponList);
		select(coupon_btn);
	});
	
	// 포인트 내역 메뉴
	point_btn.addEventListener('click', () => {
		showForm(pointList); 
		select(point_btn);
	});

	// 리뷰 메뉴
	review_btn.addEventListener('click', () => {
		showForm(reviewList);
		select(review_btn);
	});
	
	// 주문 상세 페이지 확인
	$(".orderdetail_btn").click(function(){
		$(".orderDetail").toggle();
	});
	
	// 개인 정보 관리 비밀번호 확인
	modify_btn.addEventListener('click', () => {
		showForm(checkPwd);
		select(modify_btn);
	});
});