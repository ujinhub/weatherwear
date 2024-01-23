package com.w2.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class QnaDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<QnaVO> getQnaList(Search search) {
		return sqlSessionTemplate.selectList("QnaDAO.getQnaList", search);
	}
	
	public int getQnaListCnt(Search search) {
		return sqlSessionTemplate.selectOne("QnaDAO.getQnaListCnt", search);
	}
	
	public QnaVO getQna(QnaVO vo) {
		return sqlSessionTemplate.selectOne("QnaDAO.getQna", vo);
	}
	
	public int updateQnaAnswer(QnaVO vo) {
		return sqlSessionTemplate.update("QnaDAO.updateQnaAnswer", vo);
	}
	
	public int deleteQnaAnswer(QnaVO vo) {
		return sqlSessionTemplate.update("QnaDAO.deleteQnaAnswer", vo);
	}
}
