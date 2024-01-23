package com.w2.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.board.QnaVO;
import com.w2.board.service.QnaService;
import com.w2.util.Search;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	

	/**
	 * 문의 상세 정보
	 * @param vo: 문의 정보
	 * @param model
	 * @return
	 */
	@RequestMapping("qnaInfo.mdo")
	public String qnaInfo(QnaVO vo, Model model) {
		
		model.addAttribute("info", qnaService.getQna(vo));
		return "qna/qnaInfo";
	}
	
	/**
	 * 문의 목록 가져오기
	 * @param model
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("qnaList.mdo")
	public String qnaListView(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "qnaTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = qnaService.getQnaListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("qnaList", qnaService.getQnaList(search));
		
		return "qna/qnaList";
	}
	
	/**
	 * 문의 답변 등록/수정
	 * @param vo: 문의 정보
	 * @return
	 */
	@RequestMapping("qnaUpdateProc.mdo")
	public String qnaUpdateProc(QnaVO vo) {
		vo.setQnaStatus("답변완료");
		qnaService.updateQnaAnswer(vo);
		return "forward:qnaInfo.mdo";
	}
	
	/**
	 * 답변 삭제
	 * @param vo: 문의 정보
	 * @return
	 */
	@RequestMapping("qnaDeleteProc.mdo")
	public String qnaDeleteProc(QnaVO vo) {
		
		vo.setQnaStatus("답변대기");
		vo.setQnaAnswer(null);
		vo.setQnaAnswerDate(null);
		
		qnaService.deleteQnaAnswer(vo);
		return "forward:qnaInfo.mdo";
	}
}
