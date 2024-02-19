package com.w2.payment;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentVO {
	private String paymentId;
	private String orderId;
	private String paymentMethod;
	private String paymentStatus;
	private Date paymentDate;
}
