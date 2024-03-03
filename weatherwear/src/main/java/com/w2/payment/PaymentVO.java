package com.w2.payment;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentVO {
	private String paymentId;		// 결제정보 번호
	private String orderId;			// 주문 번호
	private String paymentMethod;	// 결제 방법
	private String paymentStatus;	// 결제 상태
	private Date paymentDate;		// 결제 일자
}
