package com.w2.clientAddress;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClientAddressVO {
	private String addressId;
	private String clientId;
	private String addressTitle;
	private String addressName;
	private String addressNum;
	private String addressPostNum;
	private String address1; 
	private String address2;
	private String addressMemo;
	private String addressBase;
}
