package com.w2.client;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ClientDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 중복 확인
	public int clientCheck(HashMap<String, String> check) {
		System.out.println("3. [ Client DAO ] clientCheck : " + check.toString());
		
		return sqlSessionTemplate.selectOne("ClientDAO.checkService", check);
	}

	// 회원 가입
	public int clientSignup(ClientVO client) {
		System.out.println("3. [ Client DAO ] clientSignup : " + client.toString());
		
		return sqlSessionTemplate.insert("ClientDAO.clientSignup", client);
	}

	// 회원 정보 가져오기
	public ClientVO getClient(String clientId) {
		System.out.println("3. [ Client DAO ] getClient : " + clientId);
		
		return sqlSessionTemplate.selectOne("ClientDAO.getClient", clientId);
	}

	// 최근 로그인 일자 저장
	public void setLogDate(String clientId) {
		System.out.println("8. [ Client DAO ] setLogDate");
		sqlSessionTemplate.update("ClientDAO.setLogDate", clientId);
	}

	// 아이디 찾기
	public String clientFindId(HashMap<String, String> info) {
		System.out.println("3. [ Client DAO ] clientFindId : " + info.toString());
		
		return sqlSessionTemplate.selectOne("ClientDAO.clientFindId", info);
	}

	// 비밀번호 찾기
	public int clientFindPwd(HashMap<String, String> info) {
		System.out.println("3. [ Client DAO ] clientFindPwd : " + info.toString());
		
		return sqlSessionTemplate.selectOne("ClientDAO.clientFindPwd", info);
	}

	// 비밀번호 변경
	public int clientSetPwd(ClientVO client) {
		System.out.println("3. [ Client DAO ] clientSetPwd : " + client.toString());
		
		return sqlSessionTemplate.update("ClientDAO.clientSetPwd", client);
	}

	// 마이페이지 기본 정보
	public HashMap<String, Object> clientSetMypage(String clientId) {
		System.out.println("3. [ Client DAO ] clientSetMypage : " + clientId);
		
		HashMap<String, Object> result = sqlSessionTemplate.selectOne("ClientDAO.clientSetMypage", clientId);
		System.out.println("4. [ Client DAO ] 결과 : " + result.toString());
		
		return result;
	}
}
