package kikaboni.project.controller.board;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.Pagination;
import kikaboni.project.domain.ReplyVO;
import kikaboni.project.service.BoardService;
import kikaboni.project.service.ReplyService;

@Controller
public class BoardMyPageController {

	@Autowired
	BoardService boardService;
	
	ReplyService replyService;
	
	// 수정
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/mytext")
	public String myPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> goodlist = boardService.myTextlist(memberId, criteria);
//		List<BoardVO> menulist = boardService.myMenuTextlist(memberId, criteria);
//		List<BoardVO> talklist = boardService.mytalkTextlist(memberId, criteria);
//		List<BoardVO> eventlist = boardService.myeventTextlist(memberId, criteria);
		model.addAttribute("good", goodlist);
//		model.addAttribute("menu", menulist);
//		model.addAttribute("talk", talklist);
//		model.addAttribute("event", eventlist);
		
		model.addAttribute("page", new Pagination(criteria, boardService.totalCount()));
		return "member/mytext";
	}
	
	
	

}
