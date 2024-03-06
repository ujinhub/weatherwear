package com.w2.client.service;

import java.util.List;
import java.util.Map;

import com.w2.client.ClientVO;
import com.w2.util.Search;

public interface ClientService {
	List<ClientVO> getClientList(Search search);	// 회원 목록 조회
	int getClientListCnt(Search search);			// 회원 목록 개수 조회
	ClientVO getClient(ClientVO vo);				// 회원 정보 조회
	ClientVO getClientEmailNum(ClientVO vo);		// 회원 이메일, 전화번호 정보 조회
	List<ClientVO> getClientEmailList();			// 회원 이메일 리스트 조회
	ClientVO getClientFindInfo(Map<String, Object> param);	// 아이디/비밀번호 찾기
	int updateClientPwd(ClientVO vo);				// 비밀번호 변경
	int updateClient(ClientVO vo);					// 회원 정보 수정
	String getWishList(ClientVO vo);				// 위시리스트 조회
	public void changeCookieSetId(String cookieId, String clientId);	// 쿠키 해제(장바구니)
	void setLogDate(String clientId);				// 최근 로그인일자 변경
	int insertClient(ClientVO vo);					// 회원가입
	int insertWithdraw(ClientVO vo);				// 회원탈퇴
	ClientVO checkClient(ClientVO vo);				// 회원확인
	int deleteWithdrawQna(ClientVO vo);				// 탈퇴회원 문의내역 삭제
}
