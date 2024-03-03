package com.w2.client.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
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
	
	/**
	 * 금액 비교
	 * @param imp_uid
	 * @return
	 * @throws IamportResponseException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("verifyIamport.do")
	public IamportResponse<Payment> paymentByImpUid(@Param("imp_uid") String imp_uid) throws IamportResponseException, IOException {
		iamportClient = new IamportClient(apiKey, secretKey);
		IamportResponse<Payment> result = iamportClient.paymentByImpUid(imp_uid);

		return result;
	}

	/**
	 * 결제 오류 시 결제 취소
	 * @param paymentId
	 * @throws IamportResponseException
	 * @throws IOException
	 */
	@PostMapping("canclePayment.do")
	public void canclePayment(String paymentId) throws IamportResponseException, IOException {
		iamportClient = new IamportClient(apiKey, secretKey);
		CancelData cancel_data = new CancelData(paymentId, true);

		try {
            IamportResponse<Payment> payment_response = iamportClient.cancelPaymentByImpUid(cancel_data);
        } catch (IamportResponseException e) {
            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/** 
	 * 결제 취소(관리자)
	 * @param refundPrice
	 * @param totalPrice
	 * @param paymentId
	 * @return
	 */
	@ResponseBody
	@PostMapping("refundPriceProc.do")
	public Map<String, Object> refundPriceProc(int refundPrice, int totalPrice, String paymentId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		IamportResponse<Payment> payment_response;
		iamportClient = new IamportClient(apiKey, secretKey);
		CancelData cancel_data;
		
		if(refundPrice == totalPrice) {
			cancel_data = new CancelData(paymentId, true);
			result.put("cancleMethod", "전액취소");
		} else {
			cancel_data = new CancelData(paymentId, true, BigDecimal.valueOf(refundPrice));
			result.put("cancleMethod", "부분취소");
		}
		
		try {
			IamportResponse<Payment> cancledInfo = iamportClient.paymentByImpUid(paymentId);
			Payment paymentInfo = (Payment)cancledInfo.getResponse();
			
			if(paymentInfo.getAmount().intValue() > refundPrice) {
				payment_response = iamportClient.cancelPaymentByImpUid(cancel_data);
				
				if(payment_response != null) {
					result.put("code", 1);
					result.put("resultCode", "success");
					result.put("msg", "환불처리가 완료되었습니다.");
				} 
			} else {
				result.put("code", -1);
				result.put("resultCode", "fail");
				result.put("msg", "환불 처리 중 문제가 발생했습니다.");
			}
		} catch (IamportResponseException e) {
			
			result.put("code", -1);
			result.put("resultCode", "fail");
			switch (e.getHttpStatusCode()) {
				case 401:	// 클라이언트가 서버에 접근하기 위해 필요한 인증 정보를 제공하지 못한 경우
					System.err.println("인증 실패");
					result.put("msg", "인증에 실패하였습니다.");
					break;
				case 500:	// 서버가 요청을 처리하는 도중에 오류가 발생한 경우
					System.err.println("서버 오류");
					result.put("msg", "서버에 오류가 발생했습니다.");
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
