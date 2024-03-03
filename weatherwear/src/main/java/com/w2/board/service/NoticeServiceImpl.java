package com.w2.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.board.NoticeDAO;
import com.w2.board.NoticeVO;
import com.w2.util.Search;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDao;

	@Override	// 공지사항 목록 가져오기
	public List<NoticeVO> getNoticeList(Search search) {
		return noticeDao.getNoticeList(search);
	}

	@Override	// 공지사항 목록 개수 가져오기
	public int getNoticeListCnt(Search search) {
		return noticeDao.getNoticeListCnt(search);
	}

	@Override	// 공지사항 정보 가져오기
	public NoticeVO getNotice(NoticeVO vo) {
		return noticeDao.getNotice(vo);
	}

	@Override	// 공지사항 정보 등록
	public int insertNotice(NoticeVO vo) {
		return noticeDao.insertNotice(vo);
	}

	@Override	// 공지사항 정보 수정
	public int updateNotice(NoticeVO vo) {
		return noticeDao.updateNotice(vo);
	}

	@Override	// 공지사항 정보 삭제
	public int deleteNotice(NoticeVO vo) {
		return noticeDao.deleteNotice(vo);
	}

	@Override	// 공지사항 조회수 증가
	public int updateViewCnt(NoticeVO vo) {
		return noticeDao.updateViewCnt(vo);
	}
}
