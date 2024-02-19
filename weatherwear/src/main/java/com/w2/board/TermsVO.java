package com.w2.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TermsVO {
	private int termIdx;				// 약관 리스트 번호 (rownum)
	private String termId;				// 약관 아이디
	private String termTitle;			// 약관 제목
	private String termContent;			// 약관 내용
	private String termNecessary;		// 약관 필수/선택
	private Timestamp termRegDate;		// 약관 등록일
	private Timestamp termEndDate;		// 약관 만료일
	private Timestamp termChangeDate;	// 약관 수정일
	private Timestamp termApplyDate;	// 약관 적용일
	
	// 약관 동의
	private String clientId;			// 사용자 아이디
	private String termAgreeStatus;		// 약관 동의 여부 (Y/N)
	private Timestamp tmermAgreeDate;	// 약관 동의일
	private Timestamp termAgreeChDate;	// 약관 동의 변경일
	
}
