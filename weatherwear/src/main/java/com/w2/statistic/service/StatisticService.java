package com.w2.statistic.service;

import java.util.HashMap;

import com.w2.admin.AdminVO;

public interface StatisticService {
	HashMap<String, Object> getStatisticInfo(AdminVO vo);	// 관리자 메인페이지 정보 조회
}
