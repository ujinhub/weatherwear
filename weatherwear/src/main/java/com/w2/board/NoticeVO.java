package com.w2.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeVO {
	private int noticeIdx;				// 공지사항 리스트 번호 (rownum)
	private String noticeId;			// 공지사항 아이디
	private String noticeWriter;		// 공지사항 작성자 아이디
	private Timestamp noticeDate;		// 공지사항 등록일시
	private String noticeTitle;			// 공지사항 제목
	private String noticeContent;		// 공지사항 내용
	private String noticeImage;			// 공지사항 등록 이미지
	private int noticeView;				// 공지사항 조회수
}
