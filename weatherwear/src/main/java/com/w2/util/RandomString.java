package com.w2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class RandomString {
	
	// 랜덤 문자열 생성
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
	
	// 년월일시분초 문자열 생성
	public static String createFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		return sdf.format(cal.getTime());
	}
}
