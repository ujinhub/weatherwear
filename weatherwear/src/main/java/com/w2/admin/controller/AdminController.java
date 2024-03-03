package com.w2.admin.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.admin.AdminVO;
import com.w2.admin.service.AdminService;
import com.w2.board.service.TermsService;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.statistic.service.StatisticService;
import com.w2.util.Search;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private StatisticService statisticService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private TermsService termsService;

	/**
	 * 로그인 화면 호출
	 * @return
	 */
	@RequestMapping("login.mdo")
	public String loginView() {
		return "login";
	}
	
	/**
	 * 로그인 처리
	 * @param vo: 로그인 정보
	 * @param request
	 * @return
	 */
	@RequestMapping("loginProc.mdo")
	public String loginProc(AdminVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		AdminVO admin = adminService.getAdmin(vo);
		
		if(admin != null) {
			// 아이디 존재
			if(!passwordEncoder.matches(vo.getAdminPwd(), admin.getAdminPwd())) {
				// 비밀번호 불일치
				session.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "redirect:login.mdo";
			} else {
				// 로그인 성공
				session.removeAttribute("msg");
				session.setAttribute("adminInfo", admin);
				
				return "redirect: main.mdo";
			}
		} else {
			// 아이디 불일치
			session.setAttribute("msg", "아이디가 존재하지 않습니다.");
			return "redirect:login.mdo";
		}
	}
	
	/**
	 * 로그인 세션 정보 확인
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sessionCheck.mdo")
	public String sessionCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		
		if(session.getAttribute("adminInfo") == null) {
			return null;
		}
		
		return "ok";
	}
	
	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping("logoutProc.mdo")
	public String logoutProc(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		adminService.setLogDate((AdminVO)session.getAttribute("adminInfo"));
		session.invalidate();
		
		return "login";
	}

	/**
	 * 메인 화면 호출
	 * @return
	 */
	@RequestMapping("main.mdo")
	public String mainView(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "login";
		}
		
		if(session.getAttribute("adminInfo") == null) {
			return "login";
		}
		
		model.addAttribute("adminInfo", session.getAttribute("adminInfo"));
		model.addAttribute("statisticInfo", statisticService.getStatisticInfo((AdminVO)session.getAttribute("adminInfo")));
		
		return "main";
	}

	/**
	 * 관리자 등록 화면 호출
	 * @return
	 */
	@RequestMapping("adminRegister.mdo")
	public String adminRegisterView() {
		return "member/adminRegister";
	}
	
	/**
	 * 관리자 등록
	 * @param vo: 관리자 정보
	 * @return
	 */
	@RequestMapping("adminRegProc.mdo")
	public String adminRegProc(AdminVO vo) {
		
		vo.setAdminPwd(passwordEncoder.encode(vo.getAdminPwd()));
		vo.setAdminNum(vo.getAdminNum().replace("-", ""));
		adminService.insertAdmin(vo);
		return "redirect:adminList.mdo";
	}
	
	/**
	 * 아이디 / 비밀번호 일치 확인
	 * @param vo: 관리자 정보	
	 * @param chkType: 체크 대상 (adminId: 아이디, adminPwd: 비밀번호)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("adminCheck.mdo")
	public boolean adminCheck(AdminVO vo, String chkType) {
		AdminVO admin = adminService.getAdmin(vo);
		if(chkType.equals("adminId")) {
			// 아이디 중복 확인
			if(admin != null) {
				System.err.println("admin is not null");
				return false;
			} else {
				System.err.println("admin is null");
				return true;
			}
		} else if(chkType.equals("adminPwd")){
			// 비밀번호 일치 확인
			return passwordEncoder.matches(vo.getAdminPwd(), admin.getAdminPwd());
		} 
		
		return false;
	}

	/**
	 * 관리자 목록 가져오기
	 * @param model
	 * @param page : 가져올 페이지 번호
	 * @param range : 페이지 범위 시작 번호
	 * @param searchType : 검색 조건
	 * @param keyword : 검색 키워드
	 * @param search
	 * @return
	 */
	@RequestMapping("adminList.mdo")
	public String adminList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "adminId") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
			
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = adminService.getAdminListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("adminList", adminService.getAdminList(search));

		return "member/adminList";
	}
	
	/**
	 * 관리자 정보 가져오기
	 * @param vo: 관리자 정보
	 * @param model
	 * @return
	 */
	@RequestMapping("adminInfo.mdo")
	public String adminInfo(AdminVO vo, Model model) {
		model.addAttribute("info", adminService.getAdmin(vo));
		return "member/adminInfo";
	}
	
	/**
	 * 관리자 정보 수정
	 * @param vo: 관리자 정보
	 * @param changePwd: 비밀번호 일치 여부 (true: 변경 안함, false: 변경)
	 * @return
	 */
	@RequestMapping("adminUpdateProc.mdo")
	public String adminUpdateProc(AdminVO vo, boolean changePwd) {
		vo.setAdminNum(vo.getAdminNum().replace("-", ""));
		vo.setAdminPwd(passwordEncoder.encode(vo.getAdminPwd()));
		if(!changePwd) {
			vo.setAdminPwdDate(new Timestamp(System.currentTimeMillis()));
		}
		adminService.updateAdmin(vo);
		return "redirect:adminList.mdo";
	}
	
	@RequestMapping("adminDeleteProc.mdo")
	public String adminDeleteProc(AdminVO vo) {
		adminService.deleteAdmin(vo);
		return "redirect:adminList.mdo";
	}

	/**
	 * 회원 목록 가져오기
	 * @param model
	 * @param page : 가져올 페이지 번호
	 * @param range : 페이지 범위 시작 번호
	 * @param searchType : 검색 조건
	 * @param keyword : 검색 키워드
	 * @param search
	 * @return
	 */
	@RequestMapping("clientList.mdo")
	public String clientList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "clientId") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		int listCnt = clientService.getClientListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 화면 출력
		model.addAttribute("clientList", clientService.getClientList(search));

		return "member/clientList";
	}
	
	/**
	 * 회원 정보 가져오기
	 * @param vo: 회원 정보
	 * @param model
	 * @return
	 */
	@RequestMapping("clientInfo.mdo")
	public String clientInfo(ClientVO vo, Model model) {
		ClientVO client = clientService.getClient(vo);

		if(client != null) {
			model.addAttribute("info", client);
			model.addAttribute("termsAgree", termsService.getTermsAgree(client.getClientId()));
			return "member/clientInfo";
		} else {
			model.addAttribute("msg", "탈퇴한 회원의 정보는 확인하실 수 없습니다.");
			return "forward:clientList.mdo";
		}
	}
}
