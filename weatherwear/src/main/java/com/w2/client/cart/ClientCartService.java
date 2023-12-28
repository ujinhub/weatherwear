package com.w2.client.cart;

import java.util.List;

public interface ClientCartService {
	int insertClientCart(ClientCartVO vo);
	List<ClientCartDTO> getClientCart(ClientCartVO vo);
	int updateClientCartCnt(ClientCartVO vo);
	ClientCartVO checkClientCart(ClientCartVO vo);
	int deleteClientCart(ClientCartVO vo);
	int checkProCnt(String opId);
	int updateClientCart(ClientCartVO vo);
	
	// 비회원 장바구니 조회
	List<ClientCartVO> getNonClientCart();
}
