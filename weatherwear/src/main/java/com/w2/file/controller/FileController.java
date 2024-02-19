package com.w2.file.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.w2.file.AwsS3;
import com.w2.file.ImageVO;
import com.w2.file.service.ImageService;
import com.w2.util.ResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {
	
	@Autowired
	private AwsS3 awsS3;

	@Autowired
	private ImageService imageService;
	
	/** 
	 * Dropzone(상품 등록)
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "fileUpload.mdo")
	public List<ImageVO> fileUpload(MultipartHttpServletRequest request) throws Exception {
		String key = request.getParameter("key");
		String imageStatus = request.getParameter("imageStatus");
		String imageBy = request.getParameter("imageBy");

		System.err.println("key : " + key);
		System.err.println("imageStatus : " + imageStatus);
		System.err.println("imageBy : " + imageBy);
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		List<ImageVO> imageList = new ArrayList<ImageVO>();
		
		for(MultipartFile multipartFile: fileMap.values()) {
//			String resultUrl = awsS3.upload(multipartFile, key+"_image");
//			System.err.println("resultUrl: " + resultUrl);
//			String s3Path = resultUrl.substring(0, resultUrl.indexOf(key) + (key + "_image").length() + 1);
//			System.err.println("s3Path: " + s3Path);
//			String s3FileName = resultUrl.substring(resultUrl.indexOf(key) + (key + "_image").length() + 1, resultUrl.length());
//			System.err.println("s3FileName: " + s3FileName);

			String resultUrl = awsS3.upload(multipartFile, key);
			System.err.println("resultUrl: " + resultUrl);
			String s3Path = resultUrl.substring(0, resultUrl.indexOf(key) + key.length() + 1);
			System.err.println("s3Path: " + s3Path);
			String s3FileName = resultUrl.substring(resultUrl.indexOf(key) + key.length() + 1, resultUrl.length());
			System.err.println("s3FileName: " + s3FileName);
			
			ImageVO image = new ImageVO();
			image.setImageBy(imageBy);
			image.setImageStatus(imageStatus);
			image.setImageDir(s3Path);
			if(key.equals("product")) {	// 상품 대표 이미지 삭제
				imageService.deleteProductImage(image);
				image.setImageId(imageService.checkImage(image));
			}
			image.setImageId(s3FileName.split("\\.")[0]);
			image.setImageName(s3FileName);
			imageList.add(image);
		}

		Map<String, Object> imageMap = new HashMap<String, Object>();
		imageMap.put("list", imageList);
		imageMap.put("key", key);

		int result = imageService.insertImage(imageMap);
		
		return imageList;
	}
	
	/**
	 * summernote 이미지 파일 s3 server 업로드
	 * @param file: 이미지 파일 정보
	 * @param key: 업로드 폴더 정보
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("summernoteFileUpload.mdo")
	public String summernoteFileUpload(MultipartFile file, String key) throws IOException {
		
		if(key.equals("notice")) {
			key = "notice_image";
		}
			
		return awsS3.upload(file, key);
	}
	
	/**
	 * summernote 이미지 파일 s3 server 삭제
	 * @param file: 삭제할 파일 경로
	 */
	@ResponseBody
	@RequestMapping("summernoteFileDelete.mdo")
	public void summernoteFileDelete(String file) {
		awsS3.delete(file.substring(file.indexOf("/", 10) + 1));
	}

	/**
	 * 이미지 테이블 업로드
	 * @param request
	 * @param type
	 * @param image
	 * @return
	 */
	@ResponseBody
	@RequestMapping("imageInsert.mdo")
	public ResponseDTO<String>  imageInsert(HttpServletRequest request, @RequestBody List<ImageVO> imageList) {
		Integer statusCode = HttpStatus.OK.value();
		int code=1;
		String resultCode="";
		String msg="";
		
		Map<String, Object> imageMap = new HashMap<String, Object>();
		imageMap.put("list", imageList);
		imageMap.put("key", request.getParameter("key"));

		try {
			int result = imageService.insertImage(imageMap);

			if(result > 0) {
				code = 1;
				resultCode = "success";
				msg = "이미지 등록 성공";
			} else {
				code = -1;
				resultCode = "fail";
				msg = "이미지 등록 실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, null);
	}
}
