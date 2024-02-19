package com.w2.clientAddress.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.clientAddress.ClientAddressDAO;
import com.w2.clientAddress.ClientAddressVO;

@Service
public class ClientAddressServiceImpl implements ClientAddressService {

	@Autowired
	ClientAddressDAO addressDAO;
	
	@Override
	public List<ClientAddressVO> getAddressList(String clientId) {
		return addressDAO.getAddressList(clientId);
	}

	@Override
	public ClientAddressVO getAddressInfo(String addressId) {
		return addressDAO.getAddressInfo(addressId);
	}

	@Override
	public ClientAddressVO getBaseAddress(String clientId) {
		return addressDAO.getBaseAddress(clientId);
	}

	@Override
	public int deleteAddress(String addressId) {
		return addressDAO.deleteAddress(addressId);
	}

//	@Override
//	public List<ClientAddressVO> getMyAddressList(HashMap<String, Object> param) {
//		return addressDAO.getMyAddressList(param);
//	}
//
//	@Override
//	public int getMyAddressListCnt(String clientId) {
//		return addressDAO.getMyAddressListCnt(clientId);
//	}
//
//	@Override
//	public int insertAddress(ClientAddressVO vo) {
//		return addressDAO.insertAddress(vo);
//	}
}
