package com.w2.board;

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
	
}
