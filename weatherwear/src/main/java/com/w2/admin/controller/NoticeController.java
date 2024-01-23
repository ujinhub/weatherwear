package com.w2.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.board.NoticeVO;
import com.w2.board.service.NoticeService;
import com.w2.util.CommonUtil;
import com.w2.util.Search;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 공지사항 목록 가져오기
	 * @param model
	 * @return
	 */
	@RequestMapping("noticeList.mdo")
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
		
		return "notice/noticeList";
	}
	
	/**
	 * 공지사항 상세 정보
	 * @param vo: 공지사항 정보
	 * @param model
	 * @return
	 */
	@RequestMapping("noticeInfo.mdo")
	public String noticeInfo(NoticeVO vo, Model model) {
		model.addAttribute("info", noticeService.getNotice(vo));
		return "notice/noticeInfo";
	}
	
	/**
	 * 공지사항 등록 페이지 호출
	 * @return
	 */
	@RequestMapping("noticeRegister.mdo")
	public String noticeRegisterView() {
		return "notice/noticeRegister";
	}
	
	/**
	 * 공지사항 등록
	 * @param vo: 공지사항 정보
	 * @return
	 */
	@RequestMapping("noticeRegProc.mdo")
	public String noticeRegProc(NoticeVO vo) {
		vo.setNoticeId("NO" + CommonUtil.createFileName());
		noticeService.insertNotice(vo);
		return "redirect:noticeList.mdo";
	}
	
	/**
	 * 공지사항 수정
	 * @param vo: 공지사항 정보
	 * @return
	 */
	@RequestMapping("noticeUpdateProc.mdo")
	public String noticeUpdateProc(NoticeVO vo) {
		noticeService.updateNotice(vo);
		return "redirect:noticeList.mdo";
	}
	
	@RequestMapping("noticeDeleteProc.mdo")
	public String noticeDeleteProc(NoticeVO vo) {
		noticeService.deleteNotice(vo);
		return "redirect:noticeList.mdo";
	}
}
