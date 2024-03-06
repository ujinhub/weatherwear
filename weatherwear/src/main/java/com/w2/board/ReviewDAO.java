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
	
	/** 리뷰 목록 조회 */
	public List<ReviewVO> getReviewList(Search search) {
		return sqlSessionTemplate.selectList("ReviewDAO.getReviewList", search);
	}

	/** 리뷰 목록 개수 조회 */	
	public int getReviewListCnt(Search search) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getReviewListCnt", search);
	}
	
	/** 리뷰 등록 */	
	public int insertReview(ReviewVO vo) {
		return sqlSessionTemplate.insert("ReviewDAO.insertReview", vo);
	}
	
	/** 마이페이지 리뷰 조회 */
	public List<ReviewVO> getMyReviewList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("ReviewDAO.getMyReviewList", param);
	}

	/** 마이페이지 리뷰 개수 조회 */
	public int getMyReviewListCnt(String clientId) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getMyReviewListCnt", clientId);
	}

	/** 리뷰 상세 조회 */
	public ReviewVO getReviewInfo(String reviewId) {
		return sqlSessionTemplate.selectOne("ReviewDAO.getReviewInfo", reviewId);
	}

	/** 리뷰 이미지 조회 */
	public List<String> getReviewImage(ReviewVO review) {
		return sqlSessionTemplate.selectList("ReviewDAO.getReviewImage", review);
	}
}
