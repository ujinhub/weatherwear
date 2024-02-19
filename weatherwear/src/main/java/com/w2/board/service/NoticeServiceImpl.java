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

	@Override
	public List<NoticeVO> getNoticeList(Search search) {
		return noticeDao.getNoticeList(search);
	}

	@Override
	public int getNoticeListCnt(Search search) {
		return noticeDao.getNoticeListCnt(search);
	}

	@Override
	public NoticeVO getNotice(NoticeVO vo) {
		return noticeDao.getNotice(vo);
	}

	@Override
	public int insertNotice(NoticeVO vo) {
		return noticeDao.insertNotice(vo);
	}

	@Override
	public int updateNotice(NoticeVO vo) {
		return noticeDao.updateNotice(vo);
	}

	@Override
	public int deleteNotice(NoticeVO vo) {
		return noticeDao.deleteNotice(vo);
	}

	@Override
	public int updateViewCnt(NoticeVO vo) {
		return noticeDao.updateViewCnt(vo);
	}
}
