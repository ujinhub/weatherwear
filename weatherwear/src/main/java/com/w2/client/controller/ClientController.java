package com.w2.client.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w2.board.QnaVO;
import com.w2.board.TermsVO;
import com.w2.board.service.TermsService;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.product.service.ProductService;
import com.w2.util.RandomString;
import com.w2.util.ResponseDTO;
import com.w2.util.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClientController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TermsService termsService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("test.do")
	public String test() {
		return "test";
	}
	
	/**
	 * 로그인 화면 호출
	 * @return
	 */
	@RequestMapping("login.do")
	public String loginView() {
		return "login";
	}
	
	/**
	 * 로그인 프로세스
	 * @param vo: 사용자 정보
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("loginProc.do")
	public String loginProc(ClientVO vo, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ClientVO client = clientService.getClient(vo);
		
		if(client != null) {
			// 아이디 존재
			if(!passwordEncoder.matches(vo.getClientPwd(), client.getClientPwd())) {
				// 비밀번호 불일치
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "login";
			} else {
				// 로그인 성공
				session.setAttribute("userInfo", client);
				session.setMaxInactiveInterval(3600);
				return "redirect:main.do";
			}
		} else {
			// 아이디 불일치
			model.addAttribute("msg", "아이디가 존재하지 않습니다.");
			return "login";
		}
	}
	
	/**
	 * 로그인 세션 정보 확인
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sessionCheck.do")
	public String sessionCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		
		if(session.getAttribute("userInfo") == null) {
			return null;
		}
		
		return "ok";
	}
	
	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping("logoutProc.do")
	public String logoutProc(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		session.invalidate();

		return "redirect:main.do";
	}
	
	/**
	 * 회원가입 화면 호출
	 * @return
	 */
	@RequestMapping("clientRegister.do")
	public String clientRegisterView(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, @RequestParam(required = false, defaultValue = "termDate") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(LocalDate.now().toString());
		
		// 전체 게시글 개수
		int listCnt = termsService.getTermsListCnt(search);
		
		// 검색 페이지 정보
		search.pageInfo(page, range, listCnt);
		// 화면 출력
		model.addAttribute("termList", termsService.getTermsList(search));
		
		return "clientInfo/clientRegister";
	}
	
	@RequestMapping("clientRegProc.do")
	public String clientRegProc(ClientVO client, TermsVO terms) {
		System.err.println(client);
		System.err.println(terms);
		
		return "login";
	}
	
	/**
	 * 아이디 / 비밀번호 일치 확인
	 * @param vo: 관리자 정보	
	 * @param chkType: 체크 대상 (adminId: 아이디, adminPwd: 비밀번호)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("clientCheck.do")
	public boolean clientCheck(ClientVO vo, String chkType) {

		System.err.println(vo + " " + chkType);
		
		ClientVO client = clientService.getClient(vo);
		if(chkType.equals("clientId")) {
			// 아이디 중복 확인
			if(client != null) {
				return false;
			} else {
				return true;
			}
		} else if(chkType.equals("clientPwd")){
			// 비밀번호 일치 확인
			return passwordEncoder.matches(vo.getClientPwd(), client.getClientPwd());
		} 
		
		return false;
	}
	
	/**
	 * 아이디찾기 화면 호출
	 * @return
	 */
	@RequestMapping("findClientId.do")
	public String findClientIdView(Model model) {
		model.addAttribute("findType", "clientId");
		return "clientInfo/findClientInfo";
	}
	
	/**
	 * 비밀번호찾기 화면 호출
	 * @return
	 * 
	 */
	@RequestMapping("findClientPwd.do")
	public String findClientPwd(Model model) {
		model.addAttribute("findType", "clientPwd");
		return "clientInfo/findClientInfo";
	}
	
	/**
	 * 아이디/비밀번호 찾기 결과 화면
	 * @param vo
	 * @param searchType
	 * @param keyType
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findInfoProc.do")
	public ResponseDTO<ClientVO> findInfoProc(ClientVO vo, String searchType, String keyType, Model model) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("searchType", searchType);
		param.put("keyType", keyType);
		
		param.put("clientName", vo.getClientName());
		param.put("clientEmail", vo.getClientEmail());
		param.put("clientNum", vo.getClientNum());
		param.put("clientId", vo.getClientId());

		ClientVO client = clientService.getClientFindInfo(param);

		if(client != null) {
			return new ResponseDTO<ClientVO>(HttpStatus.OK.value(), 1, "success", "완료", client);
		} else {
			return new ResponseDTO<ClientVO>(HttpStatus.OK.value(), -1, "fail", "실패", client);
		}
	}
	
	/**
	 * 임시 비밀번호 발급
	 * @param vo
	 * @param keyType
	 * @param model
	 * @return
	 */
	@RequestMapping("tempPwd.do")
	public String tempPwd(ClientVO vo, String keyType, Model model) {
		
		String strTemp = RandomString.setRandomString(12, "word");
		vo.setClientPwd(passwordEncoder.encode(strTemp));
		
		clientService.updateClientPwd(vo);
		
		if(keyType.equals("clientEmail")) {
			sendTempMail(vo.getClientEmail(), strTemp);
		} else if(keyType.equals("clientNum")) {
			
		}

		model.addAttribute("msg", "임시 비밀번호 발급 성공.");
		return "clientInfo/findClientInfo";
	}
	
	/** 위시리스트 상품 추가 */
	@ResponseBody
	@PostMapping("wishListInsert.do")
	public ResponseDTO<String> wishListInsert(HttpSession session, @RequestBody List<String> checkList) throws IOException {
		int result;
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		String data = null;
		
		// 비로그인 상태인 경우
		if( session.getAttribute("userInfo") == null) {
			code = -2;
			resultCode = "fail";
			msg = "로그인 후 이용할 수 있습니다.";
			data = "로그인하시겠습니까?";
		} else { // 로그인 상태인 경우
			ClientVO user = (ClientVO) session.getAttribute("userInfo");
			Map<String, Object> client = new HashMap<String, Object>();
			client.put("productList", checkList);
			client.put("clientId", user.getClientId());
			System.err.println("client : " + client);

			try {
				result = productService.insertWishList(client);
				if(result > 0) {
					code = 1;
					resultCode = "success";
					msg = "위시리스트에 상품이 담겼습니다.";
					data = "위시리스트로 이동하시겠습니까?";
				} else if (result == -3){
					code = -3;
					resultCode = "fail";
					msg = "위시리스트에 이미 상품이 담겨있습니다.";
					data = "위시리스트에서 삭제하시겠습니까?";
				} else {
					code = -1;
					resultCode = "fail";
					msg = "오류가 발생했습니다. 다시 시도해주세요";
				}
			} catch (Exception e) {
				e.printStackTrace();
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, data);
	}
	
	/** 위시리스트 상품 삭제 */
	@ResponseBody
	@PostMapping("wishListDelete.do")
	public ResponseDTO<String> wishListDelete(HttpSession session, @RequestBody List<String> checkList) throws IOException {
		int result;
		Integer statusCode = HttpStatus.OK.value();
		int code;
		String resultCode;
		String msg;
		
		System.err.println("========================wishListDelete");
		System.err.println(checkList);
		
		// 비로그인 상태인 경우
		if(session.getAttribute("userInfo") == null) {
			code = -2;
			resultCode = "fail";
			msg = "로그인 후 이용할 수 있습니다.\n 로그인하시겠습니까?";
		} else { // 로그인 상태인 경우
			ClientVO user = (ClientVO) session.getAttribute("userInfo");
			Map<String, Object> client = new HashMap<String, Object>();
			client.put("productList", checkList);
			client.put("clientId", user.getClientId());
			try {
				result = productService.deleteWishList(client);
				if(result > 0) {
					code = 1;
					resultCode = "success";
					msg = "위시리스트에서 삭제되었습니다.";
				} else {
					code = -1;
					resultCode = "fail";
					msg = "오류가 발생했습니다. 다시 시도해주세요";
				}
			} catch (Exception e) {
				e.printStackTrace();
				code = -1;
				resultCode = "fail";
				msg = "오류가 발생했습니다. 다시 시도해주세요";
			}
		}
		return new ResponseDTO<String>(statusCode, code, resultCode, msg, null);
	}
	
	
	private void sendTempMail(String receiveMail, String tempPwd) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			StringBuffer content = new StringBuffer()
								.append("<p><img src='https://hyeongabucket.s3.ap-northeast-2.amazonaws.com/main/logo.png' width='237px'</p><p>&nbsp;</p>")
								.append("<h1><span style=\"font-family: 'Nanum Gothic';\"><b>임시 비밀번호 발급</b></span></h1><hr>")
								.append("<p><span style=\"font-family: 'Nanum Gothic';\">비밀번호 찾기를 통한 임시 비밀번호입니다.</span></p><p>&nbsp;</p>")
								.append("<p><span style=\"font-family: 'Nanum Gothic';\">로그인 후 &nbsp;<b>마이페이지 &gt; 정보수정&nbsp;</b>에서 비밀번호 변경을 해주세요.</span></p>")
								.append("<table style=\"border-collapse: collapse; width: 57.4418%; height: 102px;\" border=\"0\">")
								.append("<tbody><tr style=\"height: 17px;\"><td style=\"width: 3.04505%; height: 17px;\">임시 비밀번호</td><td style=\"width: 12.0639%; height: 17px;\"><b>")
								.append(tempPwd)
								.append("</b></td></tr></tbody></table>");
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(new InternetAddress("weatherwear493@gmail.com", "WeatherWear", "UTF-8"));
				mimeMessage.setSubject("[웨더웨어] 임시 비밀번호 입니다.");
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveMail));
				mimeMessage.setContent(content.toString(), "text/html;charset=UTF-8");
				mimeMessage.setReplyTo(InternetAddress.parse(receiveMail));
			}
		};
		
		try {
			mailSender.send(preparator);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}