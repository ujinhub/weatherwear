package com.w2.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.delivery.DeliveryVO;
import com.w2.delivery.service.DeliveryService;
import com.w2.util.ResponseDTO;
import com.w2.util.Search;

@Controller
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	/**
	 * 택배사 목록 가져오기
	 * @param model
	 * @return
	 */
	@RequestMapping("deliveryList.mdo")
	public String deliveryList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "productName") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = deliveryService.getProductListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("deliveryList", deliveryService.getProductList(search));
		
		return "delivery/deliveryList";
	}

	/**
	 * 택배사 등록
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deliveryInsert.mdo")
	public ResponseDTO<DeliveryVO> DeliveryInsert(DeliveryVO deli) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;

		try {
			int result = deliveryService.insertDelivery(deli);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				message = "등록이 완료되었습니다.";
			} else if (result == -2){
				code = -2;
				resultCode = "fail";
				message = "택배사번호가 이미 존재합니다.";
			} else {
				code = -1;
				resultCode = "fail";
				message = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			message = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<DeliveryVO>(statusCode, code, resultCode, message, deli);
	}

	/**
	 * 택배사 수정
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deliveryUpdate.mdo")
	public ResponseDTO<DeliveryVO> DeliveryUpdate(DeliveryVO deli) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;

		try {
			int result = deliveryService.updateDelivery(deli);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				message = "수정되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				message = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			message = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<DeliveryVO>(statusCode, code, resultCode, message, deli);
	}

	/**
	 * 택배사 삭제
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deliveryDelete.mdo")
	public ResponseDTO<DeliveryVO> deleteDeli(DeliveryVO deli) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;

		try {
			int result = deliveryService.deleteDelivery(deli.getDeliveryId());
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				message = "삭제되었습니다.";
			} else {
				code = -1;
				resultCode = "fail";
				message = "오류가 발생했습니다. 다시 시도해주세요";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			resultCode = "fail";
			message = "오류가 발생했습니다. 다시 시도해주세요";
		}
		
		return new ResponseDTO<DeliveryVO>(statusCode, code, resultCode, message, deli);
	}
}
