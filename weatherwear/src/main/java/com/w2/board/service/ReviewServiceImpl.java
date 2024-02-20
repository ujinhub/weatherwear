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
	
	@Override
	public List<ReviewVO> getReviewList(Search search) {
		return reviewDao.getReviewList(search);
	}

	@Override
	public int getReviewListCnt(Search search) {
		return reviewDao.getReviewListCnt(search);
	}

	@Override
	public ReviewVO getReview(ReviewVO vo) {
		return reviewDao.getReview(vo);
	}
	
	@Override
	public int insertReview(ReviewVO vo) {
		return reviewDao.insertReview(vo);
	}

	@Override
	public List<ReviewVO> getMyReviewList(HashMap<String, Object> param) {
		return reviewDao.getMyReviewList(param);
	}

	@Override
	public int getMyReviewListCnt(String clientId) {
		return reviewDao.getMyReviewListCnt(clientId);
	}

	@Override
	public ReviewVO getReviewInfo(String reviewId) {
		return reviewDao.getReviewInfo(reviewId);
	}

	@Override
	public List<String> getReviewImage(ReviewVO review) {
		return reviewDao.getReviewImage(review);
	}

	@Override
	public int deleteReview(String reviewId) {
		return reviewDao.deleteReview(reviewId);
	}

}
