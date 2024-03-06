package com.w2.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class QnaDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/** 문의 목록 가져오기 (관리자) */
	public List<QnaVO> getQnaList(Search search) {
		return sqlSessionTemplate.selectList("QnaDAO.getQnaList", search);
	}

	/** 문의 목록 개수 가져오기 (관리자) */
	public int getQnaListCnt(Search search) {
		return sqlSessionTemplate.selectOne("QnaDAO.getQnaListCnt", search);
	}

	/** 문의 정보 가져오기 */
	public QnaVO getQna(QnaVO vo) {
		return sqlSessionTemplate.selectOne("QnaDAO.getQna", vo);
	}

	/** 문의 답글 등록/수정하기 */
	public int updateQnaAnswer(QnaVO vo) {
		return sqlSessionTemplate.update("QnaDAO.updateQnaAnswer", vo);
	}

	/** 문의 답글 삭제하기 */
	public int deleteQnaAnswer(QnaVO vo) {
		return sqlSessionTemplate.update("QnaDAO.deleteQnaAnswer", vo);
	}

	/** 마이페이지 문의내역 조회 */
	public List<QnaVO> getMyQnaList(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("QnaDAO.getMyQnaList", param);
	}

	/** 마이페이지 문의내역 개수 조회 */
	public int getMyQnaListCnt(String clientId) {
		return sqlSessionTemplate.selectOne("QnaDAO.getMyQnaListCnt", clientId);
	}

	/** 문의 등록 */
	public int insertQna(QnaVO vo) {
		return sqlSessionTemplate.insert("QnaDAO.insertQna", vo);
	}

	/** 문의 삭제 */
	public int deleteQna(QnaVO vo) {
		return sqlSessionTemplate.delete("QnaDAO.deleteQna", vo);
	}

	/** 문의 수정 */
	public int updateQna(QnaVO vo) {
		return sqlSessionTemplate.update("QnaDAO.updateQna", vo);
	}
}
