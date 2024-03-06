package com.w2.delivery;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliverInfoVO {
	private String deliverId;		// 배송정보 번호
	private String orderId;			// 주문 번호
	private String deliveryId;		// 배송회사 번호
	private String deliverType;		// 배송유형(신규, 수거, 재발송)
	private int deliverPrice;		// 배송비
	private String deliverNum;		// 송장번호
	private Timestamp deliverDate;	// 배송일자
}
