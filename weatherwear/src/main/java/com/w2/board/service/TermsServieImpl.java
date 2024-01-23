package com.w2.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.board.TermsDAO;
import com.w2.board.TermsVO;
import com.w2.util.Search;

@Service
public class TermsServieImpl implements TermsService {
	@Autowired
	private TermsDAO termsDao;

	@Override
	public List<TermsVO> getTermsList(Search search) {
		return termsDao.getTermsList(search);
	}

	@Override
	public int getTermsListCnt(Search search) {
		return termsDao.getTermsListCnt(search);
	}

	@Override
	public TermsVO getTerms(TermsVO vo) {
		return termsDao.getTerms(vo);
	}

	@Override
	public int insertTerms(TermsVO vo) {
		return termsDao.insertTerms(vo);
	}

	@Override
	public int updateTerms(TermsVO vo) {
		return termsDao.updateTerms(vo);
	}

	@Override
	public int deleteTerms(TermsVO vo) {
		return termsDao.deleteTerms(vo);
	}

}
