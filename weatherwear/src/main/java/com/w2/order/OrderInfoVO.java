package com.w2.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInfoVO {
	private String orderId;		// 주문번호
	private String optionId;	// 옵션번호
	private int orderProCnt;	// 수량
	private int orderTotal;		// 총금액
}
