package com.w2.weather.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;

public interface WeatherService {
	List<String> provinceList = new ArrayList<String>();
	HashMap<String, Object> getWeather(String province);				// 날씨 불러오기(메인페이지)
	void saveWeather() throws IOException, ParseException;				// 날씨 업데이트
	void setUrl(String province) throws IOException, ParseException;	// 도시별 JSON 파일 받아오기
	void getJSON(String dataJson, String province) throws ParseException;	// 도시별 JSON 파일 확인
	String getDay(LocalDate date);	// 요일 확인
	String getDate(Long unixTime);	// yyyy.MM.dd 형식 날짜 반환
	HashMap<String, Object> setWeather();
}
