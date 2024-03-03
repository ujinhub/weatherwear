package com.w2.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class TermsDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/** 약관 목록 가져오기 */
	public List<TermsVO> getTermsList(Search search) {
		return sqlSessionTemplate.selectList("TermsDAO.getTermsList", search);
	}

	/** 약관 목록 개수 가져오기 */
	public int getTermsListCnt(Search search) {
		return sqlSessionTemplate.selectOne("TermsDAO.getTermsListCnt", search);
	}

	/** 약관 정보 조회 */
	public TermsVO getTerms(TermsVO vo) {
		return sqlSessionTemplate.selectOne("TermsDAO.getTerms", vo);
	}

	/** 약관 등록 */
	public int insertTerms(TermsVO vo) {
		return sqlSessionTemplate.insert("TermsDAO.insertTerms", vo);
	}

	/** 약관 수정 */
	public int updateTerms(TermsVO vo) {
		return sqlSessionTemplate.update("TermsDAO.updateTerms", vo);
	}

	/** 약관 삭제 */
	public int deleteTerms(TermsVO vo) {
		return sqlSessionTemplate.delete("TermsDAO.deleteTerms", vo);
	}
	
	/** 약관 동의 등록 */
	public int insertTermsAgree(List<TermsVO> termsList) {
		return sqlSessionTemplate.insert("TermsDAO.insertTermsAgree", termsList);
	}

	/** 약관 동의 여부 가져오기 */	
	public List<TermsVO> getTermsAgree(String clientId) {
		return sqlSessionTemplate.selectList("TermsDAO.getTermsAgree", clientId);
	}
}
