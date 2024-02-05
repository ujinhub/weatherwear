package com.w2.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ImageVO {
	private String ImageId;		// 이미지번호
	private String ImageName;	// 이미지이름(이미지번호 + 확장자)
	private String ImageDir;	// 이미지경로
	private String ImageStatus;
		/*
		 * product: 대표, 상세, (추가)
		 * admin : 공지, 메인, 기타 
		 * client : 문의, 리뷰, 환불
		 */
	private String ImageBy;		
		/*
		 * product: productId
		 * admin: adminId
		 * client: clientId
		 */
}
