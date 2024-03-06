package com.w2.admin.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.coupon.CouponVO;
import com.w2.coupon.service.CouponService;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;
import com.w2.util.Search;

@Controller
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	/**
	 * 쿠폰 목록 가져오기
	 * @param model
	 * @return
	 */
	@RequestMapping("couponList.mdo")
	public String couponList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "couponId") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = couponService.getCouponListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("couponList", couponService.getCouponListAll(search));
		
		return "coupon/couponList";
	}
	
	/**
	 * 쿠폰 등록
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("couponInsert.mdo")
	public ResponseDTO<CouponVO> couponInsert(String couponName, int couponPrice, int minPrice, String couponStDate, String couponEndDate, String couponStTime, String couponEndTime, HttpServletRequest request, String gradeList) throws ParseException {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;
		
		CouponVO coupon = new CouponVO();
		coupon.setCouponId("CP" + RandomString.createFileName() + RandomString.setRandomString(4,"number"));
		coupon.setCouponName(couponName);
		coupon.setCouponPrice(couponPrice);
		coupon.setMinPrice(minPrice);
		coupon.setGradeList(gradeList);
		coupon.setCouponEndDate(RandomString.setTime(couponEndDate));
		coupon.setCouponStDate(RandomString.setTime(couponStDate));
		try {
			int result = couponService.insertCoupon(coupon);
			
			if(result > 0) {
				code = 1;
				resultCode = "success";
				message = "등록이 완료되었습니다.";
			} else if (result == -2){
				code = -2;
				resultCode = "fail";
				message = "쿠폰번호가 이미 존재합니다.";
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
		return new ResponseDTO<CouponVO>(statusCode, code, resultCode, message, coupon);
	}

	/**
	 * 쿠폰 수정
	 * @return
	 */
	@ResponseBody
	@RequestMapping("couponUpdate.mdo")
	public ResponseDTO<CouponVO> couponUpdate(CouponVO coupon) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;

		try {
			int result = couponService.updateCoupon(coupon);
			
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
		
		return new ResponseDTO<CouponVO>(statusCode, code, resultCode, message, coupon);
	}

	/**
	 * 쿠폰 삭제
	 * @return
	 */
	@ResponseBody
	@RequestMapping("couponDelete.mdo")
	public ResponseDTO<String> deletecoupon(@Param("couponId") String couponId) {
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String message;

		try {
			int result = couponService.deleteCoupon(couponId);
			
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
		
		return new ResponseDTO<String>(statusCode, code, resultCode, message, couponId);
	}
}
