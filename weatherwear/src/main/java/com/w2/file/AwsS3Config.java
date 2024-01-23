package com.w2.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {
	@Value("${s3.accesskey}")
	private String accesskey;
	@Value("${s3.secretkey}")
	private String secretkey;
	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials awsCreds = new BasicAWSCredentials(accesskey, secretkey);
		return AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}
}
