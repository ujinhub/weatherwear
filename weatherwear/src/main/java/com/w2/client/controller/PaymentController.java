package com.w2.client.controller;

import java.io.IOException;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PropertySource("classpath:config/webserverdb.properties")
public class PaymentController {

	@Value("${imp.api.key}")
	private String apiKey;
	
	@Value("${imp.api.secretKey}")
	private String secretKey;
	
	private IamportClient iamportClient;
	
	@RequestMapping("sample.do")
	public String samplePayment() {
		return "test";
	}
	
	@ResponseBody
	@RequestMapping("verifyIamport.do")
    public IamportResponse<Payment> paymentByImpUid(@Param("imp_uid") String imp_uid) throws IamportResponseException, IOException {
		iamportClient = new IamportClient(apiKey, secretKey);
		IamportResponse<Payment> result = iamportClient.paymentByImpUid(imp_uid);

		return result;
	}
}
