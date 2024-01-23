package com.w2.util;

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
}
