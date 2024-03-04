package com.w2.client.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.client.ClientDAO;
import com.w2.client.ClientVO;
import com.w2.util.Search;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDAO clientDao;
	
	@Override	// 회원 목록 가져오기
	public List<ClientVO> getClientList(Search search) {
		return clientDao.getClientList(search);
	}

	@Override	// 회원 목록 개수 가져오기
	public int getClientListCnt(Search search) {
		return clientDao.getClientListCnt(search);
	}

	@Override	// 회원 정보 조회
	public ClientVO getClient(ClientVO vo) {
		return clientDao.getClient(vo);
	}

	@Override	// 회원 이메일 정보 조회
	public ClientVO getClientEmailNum(ClientVO vo) {
		return clientDao.getClientEmailNum(vo);
	}

	@Override	// 회원 이메일 리스트 조회
	public List<ClientVO> getClientEmailList() {
		return clientDao.getClientEmailList();
	}

	@Override	// 아이디/비밀번호 찾기
	public ClientVO getClientFindInfo(Map<String, Object> param) {
		return clientDao.getClientFindInfo(param);
	}

	@Override	// 비밀번호 변경
	public int updateClientPwd(ClientVO vo) {
		return clientDao.updateClientPwd(vo);
	}

	@Override	// 회원 정보 수정
	public int updateClient(ClientVO vo) {
		return clientDao.updateClient(vo);
	}

	@Override	// 위시리스트 조회
	public String getWishList(ClientVO vo) {
		return clientDao.getWishList(vo);
	}

	@Override	// 쿠키 해제(장바구니)
	public void changeCookieSetId(String cookieId, String clientId) {
		clientDao.changeCookieSetId(cookieId, clientId);		
	}

	@Override	// 최근 로그인 일자 변경
	public void setLogDate(String clientId) {
		clientDao.setLogDate(clientId);		
	}
	
	@Override	// 회원 가입
	public int insertClient(ClientVO vo) {
		return clientDao.insertClient(vo);
	}

	@Override	// 회원 탈퇴
	public int insertWithdraw(ClientVO vo) {
		return clientDao.insertWithdraw(vo);
	}

	@Override	// 회원 확인
	public ClientVO checkClient(ClientVO vo) {
		return clientDao.checkClient(vo);
	}

	@Override	// 탈퇴 회원 문의내역 삭제
	public int deleteWithdrawQna(ClientVO vo) {
		return clientDao.deleteWithdrawQna(vo);
	}
}
