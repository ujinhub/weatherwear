package com.w2.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.board.ReviewDAO;
import com.w2.board.ReviewVO;
import com.w2.util.Search;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO reviewDao;
	
	@Override	// 리뷰 목록 조회
	public List<ReviewVO> getReviewList(Search search) {
		return reviewDao.getReviewList(search);
	}

	@Override	// 리뷰 목록 개수 조회
	public int getReviewListCnt(Search search) {
		return reviewDao.getReviewListCnt(search);
	}
	
	@Override	// 리뷰 등록
	public int insertReview(ReviewVO vo) {
		return reviewDao.insertReview(vo);
	}

	@Override	// 리뷰 상세 조회
	public ReviewVO getReviewInfo(String reviewId) {
		return reviewDao.getReviewInfo(reviewId);
	}

	@Override	// 마이페이지 리뷰 조회
	public List<ReviewVO> getMyReviewList(HashMap<String, Object> param) {
		return reviewDao.getMyReviewList(param);
	}

	@Override	// 마이페이지 리뷰 개수 조회
	public int getMyReviewListCnt(String clientId) {
		return reviewDao.getMyReviewListCnt(clientId);
	}

	@Override	// 리뷰 이미지 조회
	public List<String> getReviewImage(ReviewVO review) {
		return reviewDao.getReviewImage(review);
	}
}
