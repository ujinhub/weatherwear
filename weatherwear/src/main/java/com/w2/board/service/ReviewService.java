package com.w2.board.service;

import java.util.List;

import com.w2.board.ReviewVO;
import com.w2.util.Search;

public interface ReviewService {
	List<ReviewVO> getReviewList(Search search);
	int getReviewListCnt(Search search);
	ReviewVO getReview(ReviewVO vo);
	int insertReview(ReviewVO vo);
}
