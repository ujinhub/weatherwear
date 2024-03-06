package com.w2.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.w2.weather.WeatherDAO;
import com.w2.weather.WeatherVO;

import lombok.RequiredArgsConstructor;

@Service("weatherService")
@RequiredArgsConstructor
@PropertySource("classpath:config/webserverdb.properties")
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherDAO weatherDAO;
	
	@Value("${weather.key}")
	private String WEATHER_KEY;
	private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?";
	
	List<String> provinceList = new ArrayList<String>();

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
			int dayOfMonth = LocalDate.of(year, month, 1).lengthOfMonth();
			int targetDay = day + i;

			if(targetDay > dayOfMonth) {
				month = today.getMonthValue()+1;
				day = 0;
			}
			
			weathervo.setWeatherDate(LocalDate.of(year, month, (day+i)).toString());
			HashMap<String, Object> oneday = weatherDAO.getWeather(weathervo);			
			
			if(oneday == null) {	// 날짜 정보 없는 경우
				try {
					setUrl(weathervo.getProvince());
					oneday = weatherDAO.getWeather(weathervo);	
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if(oneday.get("weatherday") == null) {	// 요일 정보 없는 경우
				LocalDate target = LocalDate.of(year, month, (day+i));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
				weathervo.setWeatherday(target.format(formatter));
				weatherDAO.saveWeather(weathervo);
				oneday = weatherDAO.getWeather(weathervo);
			}
			
			//날짜 형식 변환
			oneday.put("weatherdate", oneday.get("weatherDate").toString().replace("-", "."));
			System.err.println(i + " : oneday : " + oneday);
			dayList.put("day"+(3+i), oneday); // day1, day2, day3(today), day4, day5
		}
		return dayList;
	}
	
	// 날씨 업데이트
	public void saveWeather() throws IOException, ParseException {
		provinceList.add("seoul");
		provinceList.add("busan");
		provinceList.add("daegu");
		provinceList.add("chuncheon");
		provinceList.add("suwon");
		provinceList.add("incheon");
		provinceList.add("jeju");

		for(String province : provinceList) {
			setUrl(province);
		}
	}
	
	// 도시별 JSON 파일 받아오기
	public void setUrl(String province) throws IOException, ParseException {
		// URL 구성
		StringBuilder urlBuilder = new StringBuilder(WEATHER_URL);
		urlBuilder.append(URLEncoder.encode("q", "UTF-8") + "=" + URLEncoder.encode(province, "UTF-8")); // 지역
		urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + WEATHER_KEY);
		urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=" + URLEncoder.encode("metric", "UTF-8")); // 섭씨
		
		// 날씨를 받아올 주소
		URL province_url = new URL(urlBuilder.toString());
		// url : https://api.openweathermap.org/data/2.5/forecast?q= [ 지역 ] &appid= [ API KEY ] &units=metric
		
		if(province_url != null) {
			// HTTP 요청
			HttpURLConnection conn = (HttpURLConnection) province_url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");
			
			int rsCode = conn.getResponseCode();
			
			BufferedReader br;
			if(rsCode >= 200 && rsCode <= 300) { // 정상 응답
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line = br.readLine())!=null ){
				sb.append(line);
			}
			
			br.close();
			conn.disconnect();
			String dataJson = sb.toString();	
			
			try {
				// 도시별 JSON 파일 확인
				getJSON(dataJson, province);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 도시별 JSON 파일 확인
	public void getJSON(String dataJson, String province) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(dataJson);
		JSONArray weatherList = (JSONArray) jsonObject.get("list");

		WeatherVO weathervo = new WeatherVO();

		double minTemp = 300.0;
		double maxTemp = -300.0;
		
		for(int city=0; city<weatherList.size(); city++) {
			JSONObject data = (JSONObject) weatherList.get(city);
			
			String dt_txt = (String) data.get("dt_txt");
			
			String date = dt_txt.substring(0, 13); // 날짜
			String time = dt_txt.substring(11, 13); // 시간
			
			JSONObject day_main = (JSONObject) data.get("main");
			JSONArray day_weather = (JSONArray) data.get("weather");
			
			JSONObject day_weather_info = (JSONObject) day_weather.get(0);
			long day_weather_id = (long) day_weather_info.get("id"); 
			
			
			// 연도, 월, 일
			LocalDate checkday = LocalDate.of(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
			
			weathervo.setWeatherDate(date.substring(0,10));
			weathervo.setWeatherday(getDay(checkday));
			weathervo.setProvince(province);

			double checkTemp = Double.parseDouble(day_main.get("temp").toString());
			
			// 최저온도 비교
			if(checkTemp < minTemp) {
				minTemp = Math.round(checkTemp*10)/10.0;
			} 
			
			// 최고온도 비교
			if(checkTemp > maxTemp) {
				maxTemp = Math.round(checkTemp*10)/10.0;
			}
			
			weathervo.setTemp_min(minTemp);
			weathervo.setTemp_max(maxTemp);
			
			if(time.equals("15")) {
				weathervo.setWeather_id((int)day_weather_id);
			}
			
			if(time.equals("21")) {
				// 데이터 저장
				weatherDAO.saveWeather(weathervo);
				
				// 최저/최고 온도 초기화
				minTemp = 300.0;
				maxTemp = -300.0;
			}
		}
	}

	// 요일 확인
	public String getDay(LocalDate date) {	// LocalDate date = LocalDate.of(2022, 10, 31)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
		return date.format(formatter);
	}
	
	// yyyy.MM.dd 형식 날짜 반환
	public String getDate(Long unixTime) {
		Date date = new Date(unixTime * 1000L);
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM.dd");
		
		simpledate.setTimeZone(TimeZone.getTimeZone("GMT+9"));
		return simpledate.format(date);
	}

	@Override
	public HashMap<String, Object> setWeather() {
		return weatherDAO.setWeather();
	}
}