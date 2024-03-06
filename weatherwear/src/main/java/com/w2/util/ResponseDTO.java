package com.w2.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
	private int statusCode;		// HTTP 상태코드
	private int code;			// 성공(1) 실패(-1)
	private String resultCode;	// 성공(success) 실패(fail)
	private String message;		// 메세지
	private T data;				// 데이터
}
