package com.w2.weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 날씨 불러오기(메인페이지)
	public HashMap<String, Object> getWeather(String province) {
		HashMap<String, Object> dayList = new HashMap<String, Object>();
		WeatherVO weathervo = new WeatherVO();
		weathervo.setProvince(province);
		
		// 오늘 날짜
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		
		dayList.put("province", province);
		dayList.put("today", today.toString());
		
		for(int i=-2; i<3; i++) {
			weathervo.setWeatherDate(LocalDate.of(year, month, (day+i)).toString());
			
			HashMap<String, Object> oneday = sqlSessionTemplate.selectOne("WeatherDAO.getWeather", weathervo);			
			
			//날짜 형식 변환
			oneday.put("weatherdate", oneday.get("weatherDate").toString().replace("-", "."));
			dayList.put("day"+(3+i), oneday); // day1, day2, day3(today), day4, day5
		}
		return dayList;
	}
	
	// 날씨 저장
	public void saveWeather(WeatherVO weathervo) {
		int result = sqlSessionTemplate.selectOne("WeatherDAO.checkDate", weathervo);
		if(result > 0) {
			sqlSessionTemplate.update("WeatherDAO.updateWeather", weathervo);
		} else {
			sqlSessionTemplate.insert("WeatherDAO.insertWeather", weathervo);
		}
	}
	
	// 메인페이지 날씨 불러오기(세션)
	public HashMap<String, Object> setWeather(){
		HashMap<String, Object> weatherList = new HashMap<String, Object>();
		HashMap<String, Object> province = new HashMap<String, Object>();
		WeatherVO weathervo = new WeatherVO();
		
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		
		List<String> provinceList = new ArrayList<String>();
		provinceList.add("busan");
		provinceList.add("chuncheon");
		provinceList.add("daegu");
		provinceList.add("incheon");
		provinceList.add("seoul");
		provinceList.add("suwon");

		for(String pro : provinceList) {
			weathervo.setProvince(pro);
			for(int i=-2; i<3; i++) {
				weathervo.setWeatherDate(LocalDate.of(year, month, (day+i)).toString());
				
				HashMap<String, Object> oneday = sqlSessionTemplate.selectOne("WeatherDAO.getWeather", weathervo);			
				
				//날짜 형식 변환
				oneday.put("weatherdate", oneday.get("weatherDate").toString().replace("-", "."));
				province.put("day"+(3+i), oneday); // day1, day2, day3(today), day4, day5
			}
			weatherList.put(pro, province);
			System.err.println(pro + " >>> " + province);
		}
		return weatherList;
	}
}
