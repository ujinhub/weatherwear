package com.w2.board;

import com.google.protobuf.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVO {
	private int reviewIdx;
	private String reviewId;
	private String orderId;
	private String optionId;
	private String reviewContent;
	private double reviewStar;
	private Timestamp reviewDate;
	private String reviewStatus;
	private String productId;
}
