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
	
	@Override
	public List<QnaVO> getQnaList(Search search) {
		return qnaDao.getQnaList(search);
	}

	@Override
	public int getQnaListCnt(Search search) {
		return qnaDao.getQnaListCnt(search);
	}

	@Override
	public QnaVO getQna(QnaVO vo) {
		return qnaDao.getQna(vo);
	}

	@Override
	public int updateQnaAnswer(QnaVO vo) {
		return qnaDao.updateQnaAnswer(vo);
	}

	@Override
	public int deleteQnaAnswer(QnaVO vo) {
		return qnaDao.deleteQnaAnswer(vo);
	}

	@Override
	public List<QnaVO> getMyQnaList(HashMap<String, Object> param) {
		return qnaDao.getMyQnaList(param);
	}

	@Override
	public int getMyQnaListCnt(String clientId) {
		return qnaDao.getMyQnaListCnt(clientId);
	}

	@Override
	public int insertQna(QnaVO vo) {
		return qnaDao.insertQna(vo);
	}

	@Override
	public int deleteQna(QnaVO vo) {
		return qnaDao.deleteQna(vo);
	}

	@Override
	public int updateQna(QnaVO vo) {
		return qnaDao.updateQna(vo);
	}
}
