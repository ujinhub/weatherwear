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
	
	public List<AdminVO> getAdminList(Search search) {
		return sqlSessionTemplate.selectList("AdminDAO.getAdminList", search);
	}
	
	public int getAdminListCnt(Search search) {
		return sqlSessionTemplate.selectOne("AdminDAO.getAdminListCnt", search);
	}
	
	public AdminVO getAdmin(AdminVO vo) {
		return sqlSessionTemplate.selectOne("AdminDAO.getAdmin", vo);
	}
	
	public int insertAdmin(AdminVO vo) {
		return sqlSessionTemplate.insert("AdminDAO.insertAdmin", vo);
	}
	
	public int updateAdmin(AdminVO vo) {
		return sqlSessionTemplate.update("AdminDAO.updateAdmin", vo);
	}
	
	public int deleteAdmin(AdminVO vo) {
		return sqlSessionTemplate.delete("AdminDAO.deleteAdmin", vo);
	}
}
