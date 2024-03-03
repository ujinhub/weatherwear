package com.w2.file.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2.file.ImageDAO;
import com.w2.file.ImageVO;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDAO imageDAO;

	@Override	// 이미지 등록
	public int insertImage(Map<String, Object> imageMap) {
		return imageDAO.insertImage(imageMap);
	}

	@Override	// 대표 이미지 존재 여부 확인 
	public String checkImage(ImageVO image) {
		return imageDAO.checkImage(image);
	}

	@Override	// 상품 이미지 삭제
	public void deleteProductImage(ImageVO image) {
		imageDAO.deleteProductImage(image);
	}

	@Override	// 리뷰 이미지 삭제
	public int deleteReviewImage(String imageBy) {
		return imageDAO.deleteReviewImage(imageBy);
	}
}
