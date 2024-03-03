package com.w2.file;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	/** 이미지 등록 */
	public int insertImage(Map<String, Object> imageMap) {
		return sqlSessionTemplate.insert("ImageDAO.insertImage", imageMap);
	}

	/** 대표 이미지 존재 여부 확인 */
	public String checkImage(ImageVO image) {
		return sqlSessionTemplate.selectOne("ImageDAO.checkImage", image);
	}

	/** 상품 이미지 삭제 */
	public void deleteProductImage(ImageVO image) {
		sqlSessionTemplate.delete("ImageDAO.deleteProductImage", image);
	}

	/** 리뷰 이미지 삭제 */
	public int deleteReviewImage(String imageBy) {
		return sqlSessionTemplate.delete("ImageDAO.deleteReviewImage", imageBy);
	}
	
}
