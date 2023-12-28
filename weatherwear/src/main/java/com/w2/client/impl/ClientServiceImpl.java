package com.w2.client.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.client.ClientDAO;
import com.w2.client.ClientService;
import com.w2.client.ClientVO;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientdao;

	// 중복 체크(아이디, 닉네임)
	@Override
	public int clientCheckService(String comp, String with) {
		System.out.println("2. [ Impl ] : clientCheckService : " + comp + " with : " + with);
		
		HashMap<String, String> check = new HashMap<String, String>();
		check.put("comp", comp);
		check.put("with", with);
	
		int result = clientdao.clientCheck(check);
		
		if (result == 1) {
			System.out.println("4. >> clientCheck 중복 : " + result);
		} else {
			System.out.println("4. >> clientCheck 가능 : " + result);
		}
		return result;
	}

	// 회원 가입
	@Override
	public int clientSignup(ClientVO client) {
		System.out.println("2. [ Impl ] : clientSignup : " + client.toString());
		
		return clientdao.clientSignup(client);
		
		// 약관 동의
		
	}

	// 회원 정보 가져오기
	@Override
	public ClientVO getClient(String id) {
		System.out.println("2. [ Impl ] : getClient : id : " + id);

		return clientdao.getClient(id);
	}

	// 최근 로그인 일자 저장
	@Override
	public void setLogDate(String id) {
		System.out.println("7. [ Impl ] : setLogDate");
		clientdao.setLogDate(id);
	}

	// 아이디 찾기
	@Override
	public String clientFindId(String type, String clientName, String keyword) {
		System.out.println("2. [ Impl ] : clientFindId");
		
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("type", type);
		info.put("clientName", clientName);
		info.put("keyword", keyword);
		
		return clientdao.clientFindId(info);
	}

	// 비밀번호 찾기
	@Override
	public int clientFindPwd(String type, String clientName, String clientId, String keyword) {
		System.out.println("2. [ Impl ] : clientFindPwd");
		
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("type", type);
		info.put("clientName", clientName);
		info.put("clientId", clientId);
		info.put("keyword", keyword);
		
		return clientdao.clientFindPwd(info);
	}

	// 비밀번호 변경
	@Override
	public int clientSetPwd(ClientVO client) {
		System.out.println("2. [ Impl ] : clientSetPwd");
		
		return clientdao.clientSetPwd(client);
	}

	// 마이페이지 기본 정보
	@Override
	public HashMap<String, Object> clientSetMypage(String id) {
		System.out.println("2. [ Impl ] : clientSetMypage");
		
		return clientdao.clientSetMypage(id);
	}
}
