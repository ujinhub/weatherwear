package com.w2.board.service;

import java.util.HashMap;
import java.util.List;

import com.w2.board.ReviewVO;
import com.w2.util.Search;

public interface ReviewService {
	List<ReviewVO> getReviewList(Search search);
	int getReviewListCnt(Search search);
	ReviewVO getReview(ReviewVO vo);
	int insertReview(ReviewVO vo);
	
	/**
	 * 나의 리뷰 목록 (페이징) - 권유진 추가
	 */
	List<ReviewVO> getMyReviewList(HashMap<String, Object> param);
	int getMyReviewListCnt(String clientId);
}
