package com.w2.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	private int qnaIdx;					// 문의 리스트 번호 (rownum)
	private String qnaId;				// 문의 아이디
	private String clientId;			// 회원 번호
	private String qnaTitle;			// 문의 제목
	private String qnaContent;			// 문의 내용
	private Timestamp qnaDate;			// 문의 등록일시
	private String qnaSecCheck;			// 문의 비밀글 여부 (Y, N)
	private String qnaSecPwd;			// 문의 비밀번호
	private String qnaStatus;			// 문의 답변상태 (답변대기, 답변완료)
	private String qnaAnswer;			// 문의 답변내용
	private Timestamp qnaAnswerDate;	// 문의 답변일자
	private String qnaType;				// 문의 타입 (회원정보, 상품확인, 주문/결제, 배송, 교환/취소, 서비스)
}
