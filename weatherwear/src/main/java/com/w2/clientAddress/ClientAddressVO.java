package com.w2.clientAddress;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClientAddressVO {
	private String addressId;		// 주소 번호
	private String clientId;		// 회원 번호
	private String addressTitle;	// 주소 이름
	private String addressName;		// 받는 사람
	private String addressNum;		// 연락처
	private String addressPostNum;	// 우편 번호
	private String address1; 		// 기본 주소
	private String address2;		// 상세 주소
	private String addressMemo;		// 배송 메세지
	private String addressBase;		// 기본 배송지 여부
}
