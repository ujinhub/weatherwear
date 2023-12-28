/**
 * 
 */
$(document).ready(function(){
	// 클릭 시 페이지로 이동
	const notice_btn = document.querySelector("#notice_btn");
	const review_btn = document.querySelector("#review_btn");
	const qna_btn = document.querySelector("#qna_btn");
	
	const noticeList = document.querySelector(".noticeList");
	const reviewList = document.querySelector(".reviewList");
	const qnaList = document.querySelector(".qnaList");
	
	// 다른 카테고리 숨기기
	const showForm = (target) => {
		noticeList.style.display = 'none';	
		reviewList.style.display = 'none';	
		qnaList.style.display = 'none';
		
		target.style.display="flex";
	}
	
	// 선택된 메뉴 강조
	const select = (target) => {
		notice_btn.style.color = "silver";
		review_btn.style.color = "silver";
		qna_btn.style.color = "silver";
		
		target.style.color="dimgrey";
	}
	
	notice_btn.addEventListener('click', () => {
		showForm(noticeList);
		select(notice_btn);
	});
	
	review_btn.addEventListener('click', () => {
		showForm(reviewList);
		select(review_btn);
	});
	
	qna_btn.addEventListener('click', () => {
		showForm(qnaList);
		select(qna_btn);
	});
})	