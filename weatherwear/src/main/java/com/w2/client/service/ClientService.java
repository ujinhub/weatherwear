package com.w2.client.service;

import java.util.List;

import com.w2.client.ClientVO;
import com.w2.util.Search;

public interface ClientService {
	List<ClientVO> getClientList(Search search);	// 회원 목록 가져오기
	int getClientListCnt(Search search);			// 회원 목록 개수 가져오기
	ClientVO getClient(ClientVO vo);				// 회원 정보 가져오기
}
