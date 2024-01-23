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
	
	public List<NoticeVO> getNoticeList(Search search) {
		return sqlSessionTemplate.selectList("NoticeDAO.getNoticeList", search);
	}
	
	public int getNoticeListCnt(Search search) {
		return sqlSessionTemplate.selectOne("NoticeDAO.getNoticeListCnt", search);
	}
	
	public NoticeVO getNotice(NoticeVO vo) {
		return sqlSessionTemplate.selectOne("NoticeDAO.getNotice", vo);
	}
	
	public int insertNotice(NoticeVO vo) {
		return sqlSessionTemplate.insert("NoticeDAO.insertNotice", vo);
	}
	
	public int updateNotice(NoticeVO vo) {
		return sqlSessionTemplate.update("NoticeDAO.updateNotice", vo);
	}
	
	public int deleteNotice(NoticeVO vo) {
		return sqlSessionTemplate.delete("NoticeDAO.deleteNotice", vo);
	}
}
