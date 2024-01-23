package com.w2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtil {
	
	public static String createFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		return sdf.format(cal.getTime());
	}
}
