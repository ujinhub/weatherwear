package com.w2.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherVO {
	private String weatherDate;		// 날짜
	private String province;		// 지역
	private double temp_min;		// 최저 온도
	private double temp_max;		// 최고 온도
	private int weather_id;			// 대표 날씨
	private String weatherday;		// 요일
}
