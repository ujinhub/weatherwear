package com.w2.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class ReviewDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<ReviewVO> getReviewList(Search search) {
		return sqlSessionTemplate.selectList("ReviewDAO.getReviewList", search);
	}
	
	public int getReviewListCnt(Search search) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getReviewListCnt", search);
	}
	
	public ReviewVO getReview(ReviewVO vo) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getReview", vo);
	}
	
	public int insertReview(ReviewVO vo) {
		return sqlSessionTemplate.insert("ReviewDAO.insertReview", vo);
	}
	
	/**
	 * 나의 리뷰 목록 (페이징) - 권유진 추가
	 */
	public List<ReviewVO> getMyReviewList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("ReviewDAO.getMyReviewList", param);
	}
	public int getMyReviewListCnt(String clientId) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getMyReviewListCnt", clientId);
	}

	public ReviewVO getReviewInfo(String reviewId) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getReviewInfo", reviewId);
	}

	public List<String> getReviewImage(ReviewVO review) {
		return sqlSessionTemplate.selectList("ReviewDAO.getReviewImage", review);
	}

	public int deleteReview(String reviewId) {
		return sqlSessionTemplate.delete("ReviewDAO.deleteReview", reviewId);
	}
}
