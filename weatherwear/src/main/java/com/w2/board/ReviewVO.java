package com.w2.board;

import com.google.protobuf.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVO {
	private int reviewIdx;			// 리뷰 리스트 번호
	private String reviewId;		// 리뷰 번호
	private String orderId;			// 주문 번호
	private String clientId;		// 회원 아이디
	private String optionId;		// 옵션 번호
	private String reviewContent;	// 리뷰 내용
	private double reviewStar;		// 리뷰 별점
	private Timestamp reviewDate;	// 리뷰 등록 일자
	private String reviewStatus;	// 리뷰 상태(일반, 포토)
	private String productId;		// 상품 번호
}
