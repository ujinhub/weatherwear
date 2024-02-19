package com.w2.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherVO {
	private String weatherDate;
	private String province;
	private double temp_min;
	private double temp_max;
	private int weather_id;
	private String weatherday;
}
