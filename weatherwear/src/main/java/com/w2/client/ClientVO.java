package com.w2.client;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientVO {
	private int clientIdx;				// 회원 리스트 번호 (rownum)
	private String clientId;			// 회원 아이디
	private String clientPwd;			// 회원 비밀번호
	private Timestamp changePwdDate;	// 회원 비밀번호 변경 날짜
	private String clientName;			// 회원 이름
	private String clientNum;			// 회원 전화번호
	private String clientEmail;			// 회원 이메일
	private String clientEmailCheck;	// 이메일 발송 체크 여부
	private Timestamp clientRegDate;	// 회원 등록일자
	private Timestamp clientBirth;		// 생년월일
	private String gradeId;				// 회원 등급 (S, G, B)
	private int clientPoint;			// 포인트
	private String recentList;			// 최근 본 상품 리스트
	private String clientMarkList;		// 관심 상품 리스트
	private Timestamp clientLogDate;	// 최근 로그인한 날짜
	private int clientBuyCnt;			// 누적 구매 금액
}
