package com.w2.client.cart;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientCartVO {
	private int caId;
	private String proId;		// 상품 ID
	private String opId;		// 옵션 ID
	private String cId;			// 사용자 ID
	private String ckId;		// 쿠키 ID
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp caDate;	// 장바구니 등록 일시
	private int caCnt;			// 상품 수량
	private Timestamp ckLimit;	// 쿠키 유효시간
}
