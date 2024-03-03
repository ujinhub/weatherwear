package com.w2.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.board.QnaDAO;
import com.w2.board.QnaVO;
import com.w2.util.Search;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaDAO qnaDao;
	
	@Override	// 문의 목록 가져오기 (관리자)
	public List<QnaVO> getQnaList(Search search) {
		return qnaDao.getQnaList(search);
	}

	@Override	// 문의 목록 개수 가져오기 (관리자)
	public int getQnaListCnt(Search search) {
		return qnaDao.getQnaListCnt(search);
	}

	@Override	// 문의 정보 가져오기
	public QnaVO getQna(QnaVO vo) {
		return qnaDao.getQna(vo);
	}

	@Override	// 문의 답글 등록/수정하기
	public int updateQnaAnswer(QnaVO vo) {
		return qnaDao.updateQnaAnswer(vo);
	}

	@Override	// 문의 답글 삭제하기
	public int deleteQnaAnswer(QnaVO vo) {
		return qnaDao.deleteQnaAnswer(vo);
	}

	@Override	// 마이페이지 문의내역 조회
	public List<QnaVO> getMyQnaList(HashMap<String, Object> param) {
		return qnaDao.getMyQnaList(param);
	}

	@Override	// 마이페이지 문의내역 개수 조회
	public int getMyQnaListCnt(String clientId) {
		return qnaDao.getMyQnaListCnt(clientId);
	}

	@Override	// 문의 등록
	public int insertQna(QnaVO vo) {
		return qnaDao.insertQna(vo);
	}

	@Override	// 문의 삭제
	public int deleteQna(QnaVO vo) {
		return qnaDao.deleteQna(vo);
	}

	@Override	// 문의 수정
	public int updateQna(QnaVO vo) {
		return qnaDao.updateQna(vo);
	}
}
