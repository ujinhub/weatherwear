package com.w2.board.service;

import java.util.HashMap;
import java.util.List;

import com.w2.board.QnaVO;
import com.w2.util.Search;

public interface QnaService {
	List<QnaVO> getQnaList(Search search);			// 문의 목록 가져오기 (관리자)
	int getQnaListCnt(Search search);				// 문의 목록 개수 가져오기 (관리자)
	QnaVO getQna(QnaVO vo);							// 문의 정보 가져오기
	int updateQnaAnswer(QnaVO vo);					// 문의 답글 등록/수정하기
	int deleteQnaAnswer(QnaVO vo);					// 문의 답글 삭제하기
	
	List<QnaVO> getMyQnaList(HashMap<String, Object> param);	// 마이페이지 문의내역 조회
	int getMyQnaListCnt(String clientId);			// 마이페이지 문의내역 개수 조회
	int insertQna(QnaVO vo);						// 문의 등록
	int deleteQna(QnaVO vo);						// 문의 삭제
	int updateQna(QnaVO vo);						// 문의 수정
}
