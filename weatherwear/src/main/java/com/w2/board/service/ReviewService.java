package com.w2.board.service;

import java.util.HashMap;
import java.util.List;

import com.w2.board.ReviewVO;
import com.w2.util.Search;

public interface ReviewService {
	List<ReviewVO> getReviewList(Search search);	// 리뷰 목록 조회
	int getReviewListCnt(Search search);			// 리뷰 목록 개수 조회
	int insertReview(ReviewVO vo);					// 리뷰 등록
	ReviewVO getReviewInfo(String reviewId);		// 리뷰 상세 조회
	
	/**
	 * 나의 리뷰 목록 (페이징) - 권유진 추가
	 */
	List<ReviewVO> getMyReviewList(HashMap<String, Object> param);	// 마이페이지 리뷰 조회
	int getMyReviewListCnt(String clientId);						// 마이페이지 리뷰 개수 조회
	List<String> getReviewImage(ReviewVO review);					// 리뷰 이미지 조회
}
