package com.w2.client;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientVO {
	private String clientId;			// 아이디
	private String clientPwd;			// 비밀번호
	
	private String clientName;			// 이름
	private String clientNum;			// 전화번호
	private String clientEmail;			// 이메일 주소
	private String clientEmailCheck;	// 이메일 동의 여부
	
	private Date clientRegDate;		// 가입일자
	private String clientBirth;		// 생년월일
	private String clientNickName;	// 닉네임
	private Date chPwdDate;			// 비밀번호 변경 일자
	
	private String gradeId;		// 등급
	private int clientPoint;	// 포인트
	private String cpList;		// 쿠폰 리스트
	private String crList;		
	private String cmarkList;	// 즐겨찾기 리스트
	private int clientBuyCnt;	// 구매 누적 횟수
	private Date clientLogDate; // 최근 로그인 일시
}
