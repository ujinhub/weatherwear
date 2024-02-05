package com.w2.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.clientAddress.ClientAddressVO;
import com.w2.clientAddress.service.ClientAddressService;
import com.w2.util.ResponseDTO;

@Controller
public class ClientAddressController {

	@Autowired
	ClientAddressService addressService;

	/** 주소록 조회 */
	@ResponseBody
	@PostMapping("getAddressList.do")
	public ResponseDTO<List<ClientAddressVO>> getAddressList(String clientId) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		List<ClientAddressVO> addressList = addressService.getAddressList(clientId);
		if(addressList != null) {
			code = 1;
			resultCode = "success";
			msg = "조회 성공";
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		return new ResponseDTO<List<ClientAddressVO>>(statusCode, code, resultCode, msg, addressList);
	}

	/** 주소 적용 */
	@ResponseBody
	@PostMapping("getAddressInfo.do")
	public ResponseDTO<ClientAddressVO> getAddressInfo(String addressId) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		ClientAddressVO addressInfo = addressService.getAddressInfo(addressId);
		if(addressInfo != null) {
			code = 1;
			resultCode = "success";
			msg = "조회 성공";
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		return new ResponseDTO<ClientAddressVO>(statusCode, code, resultCode, msg, addressInfo);
	}

	/** 주소 삭제 */
	@ResponseBody
	@PostMapping("deleteAddress.do")
	public ResponseDTO<Integer> deleteAddress(String addressId) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		int result = addressService.deleteAddress(addressId);
		if(result > 0) {
			code = 1;
			resultCode = "success";
			msg = "삭제되었습니다.";
		} else {
			code = -1;
			resultCode = "fail";
			msg = "오류가 발생했습니다. 다시 시도해주세요";
		}
		return new ResponseDTO<Integer>(statusCode, code, resultCode, msg, result);
	}
}
