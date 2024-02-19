package com.w2.clientAddress;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientAddressDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<ClientAddressVO> getAddressList(String clientId) {
		return sqlSessionTemplate.selectList("AddressDAO.getAddressList", clientId);
	}

	public ClientAddressVO getAddressInfo(String addressId) {
		return sqlSessionTemplate.selectOne("AddressDAO.getAddressInfo", addressId);
	}

	public ClientAddressVO getBaseAddress(String clientId) {
		return sqlSessionTemplate.selectOne("AddressDAO.getBaseAddress", clientId);
	}

	public int deleteAddress(String addressId) {
		return sqlSessionTemplate.delete("AddressDAO.deleteAddress", addressId);
	}
	

//	/**
//	 * 나의 배송지 목록 가져오기 (페이징) - 권유진 추가
//	 */
//	public List<ClientAddressVO> getMyAddressList(HashMap<String, Object> param) {
//		return sqlSessionTemplate.selectList("AddressDAO.getMyAddressList", param);
//	}
//	
//	public int getMyAddressListCnt(String clientId) {
//		return sqlSessionTemplate.selectOne("AddressDAO.getMyAddressListCnt", clientId);
//	}
//	
//	public int insertAddress(ClientAddressVO vo) {
//		return sqlSessionTemplate.insert("AddressDAO.insertAddress", vo);
//	}
	
}
