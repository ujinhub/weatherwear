package com.w2.client;

import java.util.HashMap;
import java.util.List;

public interface ClientService {
	
	// 중복 체크(아이디, 닉네임)
	public int clientCheckService(String comp, String with);

	// 회원 가입
	public int clientSignup(ClientVO client);

	// 회원정보 가져오기
	public ClientVO getClient(String id);

	// 최근 로그인 일자 저장
	public void setLogDate(String id);

	// 아이디 찾기
	public String clientFindId(String type, String clientName, String keyword);

	// 비밀번호 찾기
	public int clientFindPwd(String type, String clientName, String clientId, String keyword);

	// 비밀번호 변경
	public int clientSetPwd(ClientVO client);

	// 마이페이지 기본 정보
	public HashMap<String, Object> clientSetMypage(String id);

	// 회원정보 수정
	
	// 탈퇴 요청
	
	
	// 이메일 인증
	
	// 회원 리스트 조회
	
}
