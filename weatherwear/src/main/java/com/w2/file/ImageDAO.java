package com.w2.file;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insertImage(Map<String, Object> imageMap) {
		return sqlSessionTemplate.insert("ImageDAO.insertImage", imageMap);
	}

	public String checkImage(ImageVO image) {
		return sqlSessionTemplate.selectOne("ImageDAO.checkImage", image);
	}

	public void deleteProductImage(ImageVO image) {
		sqlSessionTemplate.delete("ImageDAO.deleteProductImage", image);
	}

	public int deleteReviewImage(String imageBy) {
		return sqlSessionTemplate.delete("ImageDAO.deleteReviewImage", imageBy);
	}
	
}
