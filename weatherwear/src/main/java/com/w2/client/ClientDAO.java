package com.w2.client;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.w2.util.Search;

@Repository
public class ClientDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<ClientVO> getClientList(Search search) {
		return sqlSessionTemplate.selectList("ClientDAO.getClientList", search);
	}
	
	public int getClientListCnt(Search search) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientListCnt", search);
	}
	
	public ClientVO getClient(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClient", vo);
	}
	
	public ClientVO getClientEmail(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientEmail", vo);
	}
	
	public List<ClientVO> getClientEmailList() {
		return sqlSessionTemplate.selectList("ClientDAO.getClientEmailList");
	}
	
	public ClientVO getClientFindInfo(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientFindInfo", param);
	}
	
	public int updateClientPwd(ClientVO vo) {
		return sqlSessionTemplate.update("ClientDAO.updateClientPwd", vo);
	}
	
	public int updateClient(ClientVO vo) {
		return sqlSessionTemplate.update("ClientDAO.updateClient", vo);
	}
	
	public String getWishList(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getWishList", vo);
	}
}
