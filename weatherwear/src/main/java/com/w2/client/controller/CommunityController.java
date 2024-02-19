package com.w2.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.board.NoticeVO;
import com.w2.board.QnaVO;
import com.w2.board.ReviewVO;
import com.w2.board.service.NoticeService;
import com.w2.board.service.QnaService;
import com.w2.board.service.ReviewService;
import com.w2.util.ResponseDTO;
import com.w2.util.Search;

@Controller
public class CommunityController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private QnaService qnaService;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("community.do")
	public String communityView() {
		return "community/community";
	}
	
	/**
	 * 공지사항 목록
	 * @param model
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("noticeList.do")
	public String noticeList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "noticeTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = noticeService.getNoticeListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("noticeList", noticeService.getNoticeList(search));
		
		return "community/noticeList";
	}
	
	/**
	 * 공지사항 상세보기
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("noticeInfo.do")
	public String noticeInfo(Model model, NoticeVO vo) {
		noticeService.updateViewCnt(vo);
		model.addAttribute("info", noticeService.getNotice(vo));
		return "community/noticeInfo";
	}
	
	/**
	 * 문의 목록
	 * @param model
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param typeby
	 * @param search
	 * @return
	 */
	@RequestMapping("qnaList.do")
	public String qnaList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "qnaTitle") String searchType,
			@RequestParam(required = false) String keyword, @RequestParam(required = false, defaultValue = "0") String typeby, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setTypeby(typeby);
		
		// 전체 게시글 개수
		int listCnt = qnaService.getQnaListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("qnaList", qnaService.getQnaList(search));
		
		return "community/qnaList";
	}
	
	/**
	 * 문의글 비밀번호 체크
	 */
	@ResponseBody
	@RequestMapping("checkQnaPwd.do")
	public ResponseDTO<Boolean> checkQnaPwd(QnaVO vo) {
		System.err.println(vo);
		
		Integer statusCode = HttpStatus.OK.value();
		int code = 0;
		String resultCode = null;
		String msg = null;
		boolean data = false;
		
		QnaVO qna = qnaService.getQna(vo);
		
		if(qna.getQnaSecPwd().equals(vo.getQnaSecPwd())) {
			code = 1;
			resultCode = "success";
			data = true;
		} else {
			code = -1;
			resultCode = "fail";
			msg = "비밀글 비밀번호가 일치하지 않습니다.";
		}

		return new ResponseDTO<Boolean>(statusCode, code, resultCode, msg, data);
	}
	
	/**
	 * 문의 상세 정보
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("qnaInfo.do")
	public String qnaInfo(Model model, QnaVO vo) {
		model.addAttribute("info", qnaService.getQna(vo));
		return "community/qnaInfo";
	}
	
	/**
	 * 리뷰 목록
	 * @param model
	 * @param page
	 * @param range
	 * @param searchType
	 * @param keyword
	 * @param search
	 * @return
	 */
	@RequestMapping("reviewList.do")
	public String reviewList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "reviewTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = reviewService.getReviewListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("reviewList", reviewService.getReviewList(search));
		
		return "community/reviewList";
	}
	
	@RequestMapping("reviewInfo.do")
	public String reviewInfo(Model model, ReviewVO vo) {
		
		return "community/reviewInfo";
	}
}
