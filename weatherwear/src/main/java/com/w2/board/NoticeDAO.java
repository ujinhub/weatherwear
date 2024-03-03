package com.w2.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class NoticeDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/** 공지사항 목록 가져오기 */
	public List<NoticeVO> getNoticeList(Search search) {
		return sqlSessionTemplate.selectList("NoticeDAO.getNoticeList", search);
	}

	/** 공지사항 목록 개수 가져오기 */
	public int getNoticeListCnt(Search search) {
		return sqlSessionTemplate.selectOne("NoticeDAO.getNoticeListCnt", search);
	}

	/** 공지사항 정보 가져오기 */
	public NoticeVO getNotice(NoticeVO vo) {
		return sqlSessionTemplate.selectOne("NoticeDAO.getNotice", vo);
	}

	/** 공지사항 정보 등록 */
	public int insertNotice(NoticeVO vo) {
		return sqlSessionTemplate.insert("NoticeDAO.insertNotice", vo);
	}

	/** 공지사항 정보 수정 */
	public int updateNotice(NoticeVO vo) {
		return sqlSessionTemplate.update("NoticeDAO.updateNotice", vo);
	}

	/** 공지사항 정보 삭제 */
	public int deleteNotice(NoticeVO vo) {
		return sqlSessionTemplate.delete("NoticeDAO.deleteNotice", vo);
	}

	/** 공지사항 조회수 증가 */
	public int updateViewCnt(NoticeVO vo) {
		return sqlSessionTemplate.update("NoticeDAO.updateViewCnt", vo);
	}
}
