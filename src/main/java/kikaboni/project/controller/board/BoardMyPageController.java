package kikaboni.project.controller.board;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kikaboni.project.domain.BoardKindVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.Pagination;
import kikaboni.project.domain.ReplyVO;
import kikaboni.project.service.BoardService;
import kikaboni.project.service.ReplyService;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardMyPageController {

	@Autowired
	BoardService boardService;
	
	ReplyService replyService;
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/mytext")
	public String myPageText() {

		
		return "member/mytext";
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myCommendtext")
	public String myPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> textlist = boardService.myTextlist(memberId, criteria);
		log.info(textlist);
		model.addAttribute("text", textlist);
		
		model.addAttribute("page", new Pagination(criteria, boardService.MytotalCount(memberId)));
		return "member/myCommendText";
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myMenutext")
	public String myMenuPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> menulist = boardService.myMenuTextlist(memberId, criteria);
		log.info(memberId);
		log.info(menulist);
		model.addAttribute("menu", menulist);

		model.addAttribute("page", new Pagination(criteria, boardService.MymenutotalCount(memberId)));
		return "member/myMenuText";
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myTalktext")
	public String myTalkPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> talklist = boardService.mytalkTextlist(memberId, criteria);
		log.info(talklist);
		model.addAttribute("talk", talklist);

		model.addAttribute("page", new Pagination(criteria, boardService.MytalktotalCount(memberId)));
		return "member/myTalkText";
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myEventtext")
	public String myEventPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> eventlist = boardService.myeventTextlist(memberId, criteria);
		log.info(eventlist);
		model.addAttribute("event", eventlist);

		model.addAttribute("page", new Pagination(criteria, boardService.MyeventtotalCount(memberId)));
		return "member/myEventText";
	}
	

}
