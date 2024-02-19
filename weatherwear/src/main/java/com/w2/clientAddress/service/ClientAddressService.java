package com.w2.clientAddress.service;

import java.util.HashMap;
import java.util.List;

import com.w2.clientAddress.ClientAddressVO;

public interface ClientAddressService {
	List<ClientAddressVO> getAddressList(String clientId);	// 배송지 목록
	ClientAddressVO getAddressInfo(String addressId);		// 주소 적용
	ClientAddressVO getBaseAddress(String clientId);		// 기본 배송지
	int deleteAddress(String addressId);		// 배송지 삭제
	
//	/**
//	 * 나의 배송지 목록 가져오기 (페이징) - 권유진 추가
//	 */
//	List<ClientAddressVO> getMyAddressList(HashMap<String, Object> param);
//	int getMyAddressListCnt(String clientId);
//	int insertAddress(ClientAddressVO vo);
}
