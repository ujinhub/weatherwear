package com.w2.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class AdminDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/** 관리자 목록 가져오기 */
	public List<AdminVO> getAdminList(Search search) {	
		return sqlSessionTemplate.selectList("AdminDAO.getAdminList", search);
	}

	/** 관리자 목록 개수 가져오기 */
	public int getAdminListCnt(Search search) {
		return sqlSessionTemplate.selectOne("AdminDAO.getAdminListCnt", search);
	}

	/** 관리자 정보 가져오기 */
	public AdminVO getAdmin(AdminVO vo) {
		return sqlSessionTemplate.selectOne("AdminDAO.getAdmin", vo);
	}

	/** 관리자 정보 등록 */
	public int insertAdmin(AdminVO vo) {
		return sqlSessionTemplate.insert("AdminDAO.insertAdmin", vo);
	}

	/** 관리자 정보 수정 */
	public int updateAdmin(AdminVO vo) {
		return sqlSessionTemplate.update("AdminDAO.updateAdmin", vo);
	}

	/** 관리자 정보 삭제 */
	public int deleteAdmin(AdminVO vo) {
		return sqlSessionTemplate.delete("AdminDAO.deleteAdmin", vo);
	}

	/** 최종 로그인 일자 변경 */
	public void setLogDate(AdminVO vo) {
		sqlSessionTemplate.update("AdminDAO.setLogDate", vo);
	}
}
