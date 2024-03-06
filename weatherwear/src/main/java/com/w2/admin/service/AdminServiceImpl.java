package com.w2.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.admin.AdminDAO;
import com.w2.admin.AdminVO;
import com.w2.util.Search;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	@Override	// 관리자 목록 가져오기
	public List<AdminVO> getAdminList(Search search) {
		return adminDao.getAdminList(search);
	}

	@Override	// 관리자 목록 개수 가져오기
	public int getAdminListCnt(Search search) {
		return adminDao.getAdminListCnt(search);
	}

	@Override	// 관리자 정보 가져오기
	public AdminVO getAdmin(AdminVO vo) {
		return adminDao.getAdmin(vo);
	}

	@Override	// 관리자 정보 등록
	public int insertAdmin(AdminVO vo) {
		return adminDao.insertAdmin(vo);
	}

	@Override	// 관리자 정보 수정
	public int updateAdmin(AdminVO vo) {
		return adminDao.updateAdmin(vo);
	}

	@Override	// 관리자 정보 삭제
	public int deleteAdmin(AdminVO vo) {
		return adminDao.deleteAdmin(vo);
	}

	@Override	// 최종 로그인 일자 변경
	public void setLogDate(AdminVO vo) {
		adminDao.setLogDate(vo);
	}
	
}
