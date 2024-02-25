package com.w2.util;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/webserverdb.properties")
public class SmsUtil {

	@Value("${sms.phoneNumber}")
	private String FROM_NUMBER;

	@Value("${api.key}")
	private String APIKEY;
	
	@Value("${api.secretKey}")
	private String SECRETKEY;	
	
	// user(name_이름, text_문자, to_전화번호)
	public HashMap<String, JSONObject> sentMessage(List<HashMap<String, String>> toList) {
		Coolsms coolsms = new Coolsms(APIKEY, SECRETKEY);
		HashMap<String, String> setMessage = new HashMap<String, String>();
		HashMap<String, JSONObject> resultMap = new HashMap<String, JSONObject>();
		
		setMessage.put("from", FROM_NUMBER);
		for(HashMap<String, String> user : toList) {
			System.err.println("user : " + user);
			setMessage.put("text", (String)user.get("text"));
			setMessage.put("to", (String)user.get("to").replace("-","").replace("/", ""));
			if(((String)user.get("text")).length() < 45) {
				setMessage.put("type", "sms");
			} else {
				setMessage.put("type", "lms");
			}
			
			// 전송 결과 확인
			JSONObject result = coolsms.send(setMessage);
			
			//이름_전화번호 : result
			resultMap.put((String)user.get("name") + "_" + (String)user.get("to"), result);
			if((boolean)result.get("status") == true) {
				System.err.println("성공 =====");
				/**
				 result.get("")
				 	- group_id			: 그룹아이디
				 	- result_code		: 결과코드
				 	- result_message	: 결과메세지
				 	- success_count		: 메세지아이디
				 	- error_count		: 다건 전송시 오류 수
				 */
			} else {
				System.err.println("실패 =====");
				/**
				 result.get("")
				 	- code			: REST API 에러코드
				 	- message		: 에러메세지
				 */
			}
		}
		return null;
	}
	
	
	/** 단일 메세지 발송 예제 */
	public String sentOneMessage() {
		Coolsms coolsms = new Coolsms(APIKEY, SECRETKEY);
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("from", FROM_NUMBER);	//발신번호
		set.put("to", "01043492989");	// 수신번호
		set.put("text", "한건발송예제 : SMS/한글:45자,영문:90자, 그 이상인 경우 LMS로 변환");
		set.put("type", "sms");
		
		// 전송 결과 확인
		JSONObject result = coolsms.send(set);
		
		if((boolean)result.get("status") == true) {
			System.err.println("성공 =====");
			/**
			 result.get("")
			 	- group_id			: 그룹아이디
			 	- result_code		: 결과코드
			 	- result_message	: 결과메세지
			 	- success_count		: 메세지아이디
			 	- error_count		: 다건 전송시 오류 수
			 */
		} else {
			System.err.println("실패 =====");
			/**
			 result.get("")
			 	- code			: REST API 에러코드
			 	- message		: 에러메세지
			 */
		}
		return "redirect:test.do";
	}
	
	
	public String sentOneMessage(String to, String text) {
		Coolsms coolsms = new Coolsms(APIKEY, SECRETKEY);
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("from", FROM_NUMBER);	//발신번호
		set.put("to", to);	// 수신번호
		set.put("text", text);
		set.put("type", "sms");
		
		// 전송 결과 확인
		JSONObject result = coolsms.send(set);
		
		if((boolean)result.get("status") == true) {
			System.err.println("성공 =====");
			/**
			 result.get("")
			 	- group_id			: 그룹아이디
			 	- result_code		: 결과코드
			 	- result_message	: 결과메세지
			 	- success_count		: 메세지아이디
			 	- error_count		: 다건 전송시 오류 수
			 */
		} else {
			System.err.println("실패 =====");
			/**
			 result.get("")
			 	- code			: REST API 에러코드
			 	- message		: 에러메세지
			 */
		}
		return "redirect:test.do";
	}
}
