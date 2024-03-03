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
	
	/** 회원 리스트 조회 */
	public List<ClientVO> getClientList(Search search) {
		return sqlSessionTemplate.selectList("ClientDAO.getClientList", search);
	}

	/** 회원 리스트 개수 조회 */
	public int getClientListCnt(Search search) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientListCnt", search);
	}

	/** 회원 정보 조회 */
	public ClientVO getClient(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClient", vo);
	}

	/** 회원 이메일 조회 */
	public ClientVO getClientEmail(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientEmail", vo);
	}

	/** 회원 이메일 리스트 조회 */
	public List<ClientVO> getClientEmailList() {
		return sqlSessionTemplate.selectList("ClientDAO.getClientEmailList");
	}

	/** 회원 정보 찾기 */
	public ClientVO getClientFindInfo(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("ClientDAO.getClientFindInfo", param);
	}

	/** 회원 비밀번호 변경 */
	public int updateClientPwd(ClientVO vo) {
		return sqlSessionTemplate.update("ClientDAO.updateClientPwd", vo);
	}

	/** 회원 정보 수정 */
	public int updateClient(ClientVO vo) {
		return sqlSessionTemplate.update("ClientDAO.updateClient", vo);
	}

	/** 위시리스트 조회 */
	public String getWishList(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.getWishList", vo);
	}

	/** 로그인 시 쿠키 정보 삭제 */
	public void changeCookieSetId(String cookieId, String clientId) {
		HashMap<String, String> client = new HashMap<String, String>();
		client.put("cookieId", cookieId);
		client.put("clientId", clientId);
		
		sqlSessionTemplate.update("ClientDAO.changeCookieSetId", client);
	}

	/** 로그인 일자 업데이트 */
	public void setLogDate(String clientId) {
		sqlSessionTemplate.update("ClientDAO.setLogDate", clientId);
	}

	/** 회원 가입 */
	public int insertClient(ClientVO vo) {
		return sqlSessionTemplate.insert("ClientDAO.insertClient", vo);
	}

	/** 회원 탈퇴 */
	public int insertWithdraw(ClientVO vo) {
		return sqlSessionTemplate.insert("ClientDAO.insertWithdraw", vo);
	}

	/** 회원 조회 */
	public ClientVO checkClient(ClientVO vo) {
		return sqlSessionTemplate.selectOne("ClientDAO.checkClient", vo);
	}

	/** 탈퇴 회원 문의 내역 삭제 */	
	public int deleteWithdrawQna(ClientVO vo) {
		return sqlSessionTemplate.delete("ClientDAO.deleteWithdrawQna", vo);
	}
}