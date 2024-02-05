package com.w2.file.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.w2.file.AwsS3;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {
	
	@Autowired
	private AwsS3 awsS3;
	
	@ResponseBody
	@RequestMapping(value = "fileUpload.mdo")
	public List<String> fileUpload(MultipartHttpServletRequest request) throws Exception {
		
		String key = request.getParameter("key");
		System.err.println(key);
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		
		for(MultipartFile multipartFile: fileMap.values()) {
			String resultUrl = awsS3.upload(multipartFile, key);
			System.out.println("resultUrl: " + resultUrl);
			String s3Path = resultUrl.substring(0, resultUrl.indexOf(key) + key.length() + 1);
			System.out.println("s3Path: " + s3Path);
			String s3FileName = resultUrl.substring(resultUrl.indexOf(key) + key.length() + 1, resultUrl.length());
			System.out.println("s3FileName: " + s3FileName);
		}
		
		return null;
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
		System.err.println("summernoteFileUpload ================================ " + file + " / " + key);
		
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
		System.err.println("summernoteFileDelete ================================ " + file);
		awsS3.delete(file.substring(file.indexOf("/", 10) + 1));
	}
	
}
