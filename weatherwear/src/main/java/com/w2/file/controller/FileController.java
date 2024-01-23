package com.w2.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.w2.file.AwsS3;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {
	
	@Autowired
	private AwsS3 awsS3;
	
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
