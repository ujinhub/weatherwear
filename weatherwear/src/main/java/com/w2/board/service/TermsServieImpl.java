package com.w2.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.board.TermsDAO;
import com.w2.board.TermsVO;
import com.w2.util.Search;

@Service
public class TermsServieImpl implements TermsService {
	@Autowired
	private TermsDAO termsDao;

	@Override	// 약관 목록 가져오기
	public List<TermsVO> getTermsList(Search search) {
		return termsDao.getTermsList(search);
	}

	@Override	// 약관 목록 개수 가져오기
	public int getTermsListCnt(Search search) {
		return termsDao.getTermsListCnt(search);
	}

	@Override	// 약관 정보 조회
	public TermsVO getTerms(TermsVO vo) {
		return termsDao.getTerms(vo);
	}

	@Override	// 약관 등록
	public int insertTerms(TermsVO vo) {
		return termsDao.insertTerms(vo);
	}

	@Override	// 약관 수정
	public int updateTerms(TermsVO vo) {
		return termsDao.updateTerms(vo);
	}

	@Override	// 약관 삭제
	public int deleteTerms(TermsVO vo) {
		return termsDao.deleteTerms(vo);
	}

	@Override	// 약관 동의 등록
	public int insertTermsAgree(List<TermsVO> termsList) {
		return termsDao.insertTermsAgree(termsList);
	}

	@Override	// 약관 동의 여부 조회
	public List<TermsVO> getTermsAgree(String clientId) {
		return termsDao.getTermsAgree(clientId);
	}

}
