package kikaboni.project.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kikaboni.project.domain.BoardKindVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.service.BoardService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/boardRegister")
public class BoardRegisterController {

	@Autowired
	BoardService boardService;
	
	// 게시판에서 글 작성으로 이동
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/goodregister")
	public void goodregister(Model model, Long bno) { 
		
	}
	
	// 게시판에서 글 작성으로 이동
	@GetMapping("/menuregister")
	public void menuregister(Model model, Long bno) { 
		
	}
	
	// 게시판에서 글 작성으로 이동
	@GetMapping("/talkregister")
	public void talkregister(Model model, Long bno) { 
		
	}
	
	// 글 작성 처리하는 부분
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/goodregister")
	public String goodListRegister(BoardVO vo, RedirectAttributes rttr){ // 여기에 매개변수로 model 줄 필요 없음
		log.info(vo);
		boardService.commendRegister(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		
		
		return "redirect:/boardkind/breadGoodList";
	}

	@PostMapping("/menuregister")
	public String menuListRegister(BoardVO vo, RedirectAttributes rttr) {
		
		boardService.menuRegister(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		
		return "redirect:/boardkind/breadMenuProposalList";
	}
	
	@PostMapping("/talkregister")
	public String talkListRegister(BoardVO vo, RedirectAttributes rttr) {
		
		boardService.talkRegister(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		
		return "redirect:/boardkind/CEOtalkList";
	}
	
	
	
}
