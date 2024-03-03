package com.w2.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RandomString {
	
	/**
	 * 랜덤 문자열 생성
	 * @param num
	 * @param method
	 * @return
	 */
	public static String setRandomString(int num, String method) {
		
		String spell = "1234567890";
		
		if(method == "word") {
			spell += "abcdefghijklmnopqrstuvwxyz";
			spell += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		
		StringBuilder builder;
		
		builder = new StringBuilder(num);
		
		for(int i=0; i<num; i++) {
			int index = (int)(spell.length() * Math.random());
			
			builder.append(spell.charAt(index));
		}
		
		return builder.toString();
	}
	
	/**
	 * 년월일시분초 문자열 생성
	 * @return
	 */
	public static String createFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 날짜 변경 (date,time input 태그 > timestamp)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp setTime(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = "00:00"; 
		Date stringToDate = dateFormat.parse(date + " " + time);
		return new Timestamp(stringToDate.getTime());
	}
}
