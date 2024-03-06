package com.w2.file.service;

import java.util.Map;

import com.w2.file.ImageVO;

public interface ImageService {

	int insertImage(Map<String, Object> imageMap);	// 이미지 테이블 등록
	String checkImage(ImageVO image);				// 대표 이미지 존재 여부 확인
	void deleteProductImage(ImageVO image);			// 상품 이미지 삭제
	int deleteReviewImage(String imageBy);			// 리뷰 이미지 삭제
}
