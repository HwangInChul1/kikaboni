package kikaboni.project.controller.board;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/mytext")
	public String myPageText(Model model, Principal principal, Criteria criteria) {
		String memberId = principal.getName();
		List<BoardVO> textlist = boardService.myTextlist(memberId, criteria);
		model.addAttribute("text", textlist);
		model.addAttribute("page", new Pagination(criteria, boardService.totalCount()));
		return "member/mytext";
	}
	
	@GetMapping("/member/myreply")
	public void myReplyList(Model model) {
		List<ReplyVO> replyList = replyService.myReplyList();
		model.addAttribute("reply", replyList);
	}
}
