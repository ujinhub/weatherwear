package com.w2.admin.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.w2.board.TermsVO;
import com.w2.board.service.TermsService;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.util.Search;

@Controller
public class TermsController {
	
	@Autowired
	private TermsService termsService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private JavaMailSender mailSender;
	
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
		vo.setTermId("TM" + String.format("%04d", termsService.getTermsListCnt(null) + 1));
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
	
	@RequestMapping("sendTermsMail.mdo")
	public void sendTermsMail(TermsVO vo) {
		System.err.println("=======================sendTermsMail\n" + vo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");
		
		List<ClientVO> emailList = clientService.getClientEmailList();
		System.err.println(emailList);
		
		for(int i = 0; i < emailList.size(); i++) {
			String receiveMail = emailList.get(i).getClientEmail();
			
			//if(receiveMail.equals("yeogiogae@gmail.com")) {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				
				StringBuffer content = new StringBuffer()
									.append("<p><img src='https://hyeongabucket.s3.ap-northeast-2.amazonaws.com/main/logo.png' width='237px'</p><p>&nbsp;</p>")
									.append("<h1><span style=\"font-family: 'Nanum Gothic';\"><b>웨더웨어 ")
									.append(vo.getTermTitle())
									.append(" 개정 안내</b></span></h1><hr>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">안녕하세요.</span></p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">항상 웨더웨어를 이용해 주시는 고객님께 진심으로 감사드립니다.</span></p><p>&nbsp;</p>")
									.append("<p><span style=\"font-family: 'Nanum Gothic';\">웨더웨어 ")
									.append(vo.getTermTitle())
									.append(" 이용약관이 아래와 같이 개정됨을 알려드리오니 이용에 참고하여 주시기 바랍니다.</span><span style=\"font-family: 'Nanum Gothic';\"></span></p>")
									.append("<hr>")
									.append("<p><b><span style=\"font-family: 'Nanum Gothic';\">1. 개정 약관</span></b></p>")
									.append("<p><span style=\"\">ㆍ")
									.append(vo.getTermTitle()) 
									.append("</span></p><p style=\"text-align: left;\">&nbsp;</p><p style=\"text-align: left;\"><b><span style=\"\">2. 시행 일자</span></b></p>")
									.append("<p><span style=\"\"> <span style=\"font-family: 'Nanum Gothic'; color: #333333; text-align: left;\">ㆍ</span> ")
									.append(sdf.format(vo.getTermApplyDate()))
									.append("</span></p><p style=\"text-align: left;\">&nbsp;</p><p style=\"text-align: left;\"><b><span style=\"\">3. 약관 내용 </span></b></p>")
									.append(vo.getTermContent());
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setFrom(new InternetAddress("weatherwear493@gmail.com", "WeatherWear", "UTF-8"));
					mimeMessage.setSubject("[웨더웨어] " + vo.getTermTitle() + " 개정 안내");
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
			//}
		}
	}
}
