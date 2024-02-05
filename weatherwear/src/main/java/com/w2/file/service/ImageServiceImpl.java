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

	@Override
	public int insertImage(Map<String, Object> imageMap) {
		return imageDAO.insertImage(imageMap);
	}

	@Override
	public String checkImage(ImageVO image) {
		return imageDAO.checkImage(image);
	}

	@Override
	public void deleteProductImage(ImageVO image) {
		imageDAO.deleteProductImage(image);
	}
}
