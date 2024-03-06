package com.w2.board.service;

import java.util.List;

import com.w2.board.TermsVO;
import com.w2.util.Search;

public interface TermsService {
	List<TermsVO> getTermsList(Search search);	// 약관 목록 가져오기
	int getTermsListCnt(Search search);			// 약관 목록 개수 가져오기
	TermsVO getTerms(TermsVO vo);				// 약관 정보 가져오기
	int insertTerms(TermsVO vo);				// 약관 등록
	int updateTerms(TermsVO vo);				// 약관 수정
	int deleteTerms(TermsVO vo);				// 약관 삭제
	
	int insertTermsAgree(List<TermsVO> termsList);	// 약관 동의 여부 등록
	List<TermsVO> getTermsAgree(String clientId);	// 약관 동의 여부 조회
}
