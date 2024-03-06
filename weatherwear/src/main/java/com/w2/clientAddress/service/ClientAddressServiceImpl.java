package com.w2.clientAddress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.clientAddress.ClientAddressDAO;
import com.w2.clientAddress.ClientAddressVO;

@Service
public class ClientAddressServiceImpl implements ClientAddressService {

	@Autowired
	ClientAddressDAO addressDAO;
	
	@Override	// 배송지 목록
	public List<ClientAddressVO> getAddressList(String clientId) {
		return addressDAO.getAddressList(clientId);
	}

	@Override	// 주소 적용
	public ClientAddressVO getAddressInfo(String addressId) {
		return addressDAO.getAddressInfo(addressId);
	}

	@Override	// 기본 배송지
	public ClientAddressVO getBaseAddress(String clientId) {
		return addressDAO.getBaseAddress(clientId);
	}

	@Override	// 배송지 삭제
	public int deleteAddress(String addressId) {
		return addressDAO.deleteAddress(addressId);
	}
}
