package com.w2.file;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config/webserverdb.properties")
public class AwsS3 {
	
	@Value("${s3.bucket}")
	private String bucket;
	
	private final AmazonS3 amazonS3;
	
	public String upload(MultipartFile multipartFile, String key) throws IOException {
		String s3FileName = key + "/" + UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		
		ObjectMetadata objMetaData = new ObjectMetadata();
		objMetaData.setContentType(multipartFile.getContentType());
		objMetaData.setContentLength(multipartFile.getInputStream().available());
		
		amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMetaData);
		
		return amazonS3.getUrl(bucket, s3FileName).toString();
	}
	
	public void delete(String keyName) {
		amazonS3.deleteObject(bucket, keyName);
	}
}
