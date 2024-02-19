package com.w2.client;

import java.util.HashMap;
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
	
	public void changeCookieSetId(String cookieId, String clientId) {
		HashMap<String, String> client = new HashMap<String, String>();
		client.put("cookieId", cookieId);
		client.put("clientId", clientId);
		
		sqlSessionTemplate.update("ClientDAO.changeCookieSetId", client);
	}

	public void setLogDate(String clientId) {
		sqlSessionTemplate.update("ClientDAO.setLogDate", clientId);
	}
	
	// 회원가입 / 탈퇴
	public int insertClient(ClientVO vo) {
		return sqlSessionTemplate.insert("ClientDAO.insertClient", vo);
	}
	
	public int insertWithdraw(ClientVO vo) {
		return sqlSessionTemplate.insert("ClientDAO.insertWithdraw", vo);
	}
	
	public ClientVO checkClient(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.checkClient", vo);
	}
	
	public int deleteWithdrawQna(ClientVO vo) {
		return sqlSessionTemplate.delete("ClientDAO.deleteWithdrawQna", vo);
	}
}