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
	
	public List<TermsVO> getTermsList(Search search) {
		return sqlSessionTemplate.selectList("TermsDAO.getTermsList", search);
	}
	
	public int getTermsListCnt(Search search) {
		return sqlSessionTemplate.selectOne("TermsDAO.getTermsListCnt", search);
	}
	
	public TermsVO getTerms(TermsVO vo) {
		return sqlSessionTemplate.selectOne("TermsDAO.getTerms", vo);
	}
	
	public int insertTerms(TermsVO vo) {
		return sqlSessionTemplate.insert("TermsDAO.insertTerms", vo);
	}
	
	public int updateTerms(TermsVO vo) {
		return sqlSessionTemplate.update("TermsDAO.updateTerms", vo);
	}
	
	public int deleteTerms(TermsVO vo) {
		return sqlSessionTemplate.delete("TermsDAO.deleteTerms", vo);
	}
}
