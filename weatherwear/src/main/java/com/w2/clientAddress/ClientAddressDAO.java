package com.w2.clientAddress;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientAddressDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	/** 배송지 목록 조회 */
	public List<ClientAddressVO> getAddressList(String clientId) {
		return sqlSessionTemplate.selectList("AddressDAO.getAddressList", clientId);
	}

	/** 주소 적용 */
	public ClientAddressVO getAddressInfo(String addressId) {
		return sqlSessionTemplate.selectOne("AddressDAO.getAddressInfo", addressId);
	}

	/** 기본 배송지 */
	public ClientAddressVO getBaseAddress(String clientId) {
		return sqlSessionTemplate.selectOne("AddressDAO.getBaseAddress", clientId);
	}

	/** 배송지 삭제 */
	public int deleteAddress(String addressId) {
		return sqlSessionTemplate.delete("AddressDAO.deleteAddress", addressId);
	}
}
