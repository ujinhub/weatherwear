package com.w2.util;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.w2.cart.CartDAO;
import com.w2.coupon.service.CouponService;
import com.w2.weather.service.WeatherService;

@Service(value="schedulerService")
public class SchedulingService {

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private CouponService couponService;
	
	/*
	 	cron 표현식
	  	초 		| 분 	| 시 	| 일 	| 월 	| 요일 	| 연도
	  	0-59 	 0-59	 0-23	 1-31	 1-12	 1-12	(생략가능)
	 */
	//@Scheduled(cron="0 0 0 * * *")
//	@Scheduled(cron="*/30 * * * * *")
	@Scheduled(cron="0 0 17 * * *")
	public void scheduleRun() throws IOException, ParseException {
		checkCookieLimit();
		weatherService.saveWeather();
		couponService.setCouponLimit();
		couponService.setCoupon();
	}
	
	// 만료된 쿠키 확인
	public void checkCookieLimit() {
		cartDAO.checkCookieLimit();
	}
}
