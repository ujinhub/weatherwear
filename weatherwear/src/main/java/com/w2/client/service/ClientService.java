package com.w2.client.service;

import java.util.List;
import java.util.Map;

import com.w2.client.ClientVO;
import com.w2.util.Search;

public interface ClientService {
	List<ClientVO> getClientList(Search search);	// 회원 목록 가져오기
	int getClientListCnt(Search search);			// 회원 목록 개수 가져오기
	ClientVO getClient(ClientVO vo);				// 회원 정보 가져오기
	ClientVO getClientEmail(ClientVO vo);			// 회원 이메일 정보 가져오기
	List<ClientVO> getClientEmailList();			// 회원 이메일 리스트 가져오기
	
	ClientVO getClientFindInfo(Map<String, Object> param);	// 아이디/비밀번호 찾기
}
