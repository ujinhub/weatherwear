package com.w2.board.service;

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


}
