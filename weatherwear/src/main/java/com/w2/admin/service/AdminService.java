package com.w2.admin.service;

import java.util.List;

import com.w2.admin.AdminVO;
import com.w2.util.Search;

public interface AdminService {
	List<AdminVO> getAdminList(Search search);	// 관리자 목록 가져오기
	int getAdminListCnt(Search search);			// 관리자 목록 개수 가져오기
	AdminVO getAdmin(AdminVO vo);				// 관리자 정보 가져오기
	int insertAdmin(AdminVO vo);				// 관리자 정보 등록
	int updateAdmin(AdminVO vo);				// 관리자 정보 수정
	int deleteAdmin(AdminVO vo);				// 관리자 정보 삭제
	void setLogDate(AdminVO vo);				// 최종 로그인 일자 변경
}
