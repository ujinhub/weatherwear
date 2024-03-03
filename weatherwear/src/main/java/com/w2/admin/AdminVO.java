package com.w2.admin;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVO {
	private int adminIdx;			// 관리자 리스트 번호 (rownum)
	private String adminId;			// 관리자 아이디
	private String adminPwd;		// 관리자 비밀번호
	private Timestamp adminPwdDate;	// 관리자 비밀번호 변경 날짜
	private String adminName;		// 관리자 이름
	private String adminNum;		// 관리자 전화번호
	private Timestamp adminRegDate;	// 관리자 등록일시
	private String gradeId;			// 관리자 등급 (최고관리자: super, 매니저: manager)
	private Timestamp adminLogDate; // 관리자 최종 로그인 일자
}
