package com.w2.delivery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryVO {
	private String deliveryId;			// 택배회사번호
	private String deliveryComName;	// 택배회사이름
	private String deliveryComNum;		// 택배회사연락처
	private int deliveryPrice;			// 택배비
	private String deliveryName;		// 담당자
	private String deliveryNum;			// 담당자 연락처
}
