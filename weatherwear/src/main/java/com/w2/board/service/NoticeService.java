package com.w2.board.service;

import java.util.List;

import com.w2.board.NoticeVO;
import com.w2.util.Search;

public interface NoticeService {
	List<NoticeVO> getNoticeList(Search search);	// 공지사항 목록 가져오기
	int getNoticeListCnt(Search search);			// 공지사항 목록 개수 가져오기
	NoticeVO getNotice(NoticeVO vo);				// 공지사항 정보 가져오기
	int insertNotice(NoticeVO vo);					// 공지사항 정보 등록
	int updateNotice(NoticeVO vo);					// 공지사항 정보 수정
	int deleteNotice(NoticeVO vo);					// 공지사항 정보 삭제
	int updateViewCnt(NoticeVO vo);					// 공지사항 조회수 증가
}
