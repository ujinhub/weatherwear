package com.w2.admin.controller;

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

import com.w2.board.QnaVO;
import com.w2.board.service.QnaService;
import com.w2.client.ClientVO;
import com.w2.client.service.ClientService;
import com.w2.util.Search;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private JavaMailSender mailSender;
	

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
			@RequestParam(required = false) String keyword, @RequestParam(required = false) String typeby, @ModelAttribute("search") Search search) {
		
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
		
		return "qna/qnaList";
	}
	
	/**
	 * 문의 답변 등록/수정
	 * @param vo: 문의 정보
	 * @return
	 */
	@RequestMapping("qnaUpdateProc.mdo")
	public String qnaUpdateProc(QnaVO vo) {
		
		if(vo.getQnaStatus().equals("답변대기")) {
			vo.setQnaStatus("답변완료");
			ClientVO client = new ClientVO();
			client.setClientId(vo.getClientId());
			
			client = clientService.getClientEmail(client);
			System.err.println(client);
			if(client.getClientEmailCheck().equals("Y")) {
				System.err.println("메일전송");
				sendAnswerMail(vo, client.getClientEmail());
			}
		}
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
	
	private void sendAnswerMail(QnaVO vo, String receiveMail) {
		System.err.println("============================sendAnswerMail: " + receiveMail);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			StringBuffer content = new StringBuffer()
								.append("<p><img src='https://hyeongabucket.s3.ap-northeast-2.amazonaws.com/main/logo.png' width='237px'</p><p>&nbsp;</p>")
								.append("<h1><span style=\"font-family: 'Nanum Gothic';\"><b>1:1 문의에 대한 답변이 완료되었습니다.</b></span></h1><hr>")
								.append("<p><span style=\"font-family: 'Nanum Gothic';\">문의하신 내용은&nbsp;<b>웨더웨어 &gt; 1:1 문의내역&nbsp;</b>에서도 확인 가능합니다.</span></p>")
								.append("<p><span style=\"font-family: 'Nanum Gothic';\">답변이 고객님께 충분한 도움이 되기를 바랍니다.</span></p><p>&nbsp;</p>")
								.append("<table style=\"border-collapse: collapse; width: 57.4418%; height: 102px;\" border=\"0\">")
								.append("<tbody><tr style=\"height: 17px;\"><td style=\"width: 3.04505%; height: 17px;\">문의작성일</td><td style=\"width: 12.0639%; height: 17px;\">")
								.append(vo.getQnaDate())
								.append("</td></tr><tr style=\"height: 17px;\"><td style=\"width: 3.04505%; height: 17px;\">문의유형</td><td style=\"width: 12.0639%; height: 17px;\">")
								.append(vo.getQnaType())
								.append("</td></tr><tr style=\"height: 17px;\"><td style=\"width: 15.109%; height: 17px;\" colspan=\"2\"><hr></td></tr><tr style=\"height: 17px;\">")
								.append("<td style=\"width: 3.04505%; height: 17px;\">제목</td><td style=\"width: 12.0639%; height: 17px;\">")
								.append(vo.getQnaTitle())
								.append("</td></tr><tr style=\"height: 17px;\"><td style=\"width: 3.04505%; height: 17px;\">문의내용</td><td style=\"width: 12.0639%; height: 17px;\">")
								.append(vo.getQnaContent())
								.append("</td></tr><tr style=\"height: 17px;\"><td style=\"width: 3.04505%; height: 17px;\">답변내용</td><td style=\"width: 12.0639%; height: 17px;\">")
								.append(vo.getQnaAnswer())
								.append("</td></tr></tbody></table>");
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(new InternetAddress("weatherwear493@gmail.com", "WeatherWear", "UTF-8"));
				mimeMessage.setSubject("[웨더웨어] 1:1문의 답변이 완료되었습니다.");
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
