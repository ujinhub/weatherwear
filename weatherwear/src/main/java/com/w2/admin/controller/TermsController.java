package com.w2.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.board.TermsVO;
import com.w2.board.service.TermsService;
import com.w2.util.Search;

@Controller
public class TermsController {
	
	@Autowired
	private TermsService termsService;
	
	/**
	 * 약관 목록 가져오기
	 * @param model
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("termsList.mdo")
	public String termsList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "termTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = termsService.getTermsListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		
		model.addAttribute("termsList", termsService.getTermsList(search));
		return "terms/termsList";
	}

	@RequestMapping("termsInfo.mdo")
	public String termsInfo(TermsVO vo, Model model) {
		model.addAttribute("info", termsService.getTerms(vo));
		return "terms/termsInfo";
	}
	
	/**
	 * 약관 등록 화면 호출
	 * @return
	 */
	@RequestMapping("termsRegister.mdo")
	public String termsRegisterView() {
		return "terms/termsRegister";
	}

	@RequestMapping("termsRegProc.mdo")
	public String termsRegProc(TermsVO vo) {
		System.err.println("====================================termsRegProc " + vo);
		
		termsService.insertTerms(vo);
		return "redirect:termsList.mdo";
	}
	
	@RequestMapping("termsUpdateProc.mdo")
	public String termsUpdateProc(TermsVO vo) {
		termsService.updateTerms(vo);
		return "forward:termsInfo.mdo";
	}
	
	@RequestMapping("termsDeleteProc.mdo")
	public String termsDeleteProc(TermsVO vo) {
		termsService.deleteTerms(vo);
		return "redirect:termsList.mdo";
	}
}
