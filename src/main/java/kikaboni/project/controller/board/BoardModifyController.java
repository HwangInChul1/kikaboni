package kikaboni.project.controller.board;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.service.BoardService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/boardModify")
@Log4j
public class BoardModifyController {

	@Autowired
	BoardService boardService;
	
	// 상세 게시판에서 수정창으로 이동(빵 추천)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/goodbreadmodify")
	public void goodmodify(Model model, Long bno, Criteria criteria, Authentication auth) { 
		BoardVO vo = boardService.commendGet(bno);
		String name = auth.getName();
		if(!vo.getWriter().equals(name) &&
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied"); 
		}
		model.addAttribute("vo", vo);
	}
	
	// 상세 게시판에서 수정창으로 이동(메뉴 건의)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/menubreadmodify")
	public void menuregister(Model model, Long bno, Criteria criteria, Authentication auth) { 
		BoardVO vo = boardService.menuGet(bno);
		String name = auth.getName();
		if(!vo.getWriter().equals(name) &&
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied"); 
		}
		model.addAttribute("vo", vo);
	}
	
	// 상세 게시판에서 수정창으로 이동(사장님과 대화)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/talkmodify")
	public void talkregister(Model model, Long bno, Criteria criteria, Authentication auth) { 
		BoardVO vo = boardService.talkGet(bno);
		String name = auth.getName();
		if(!vo.getWriter().equals(name) &&
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied"); 
		}
		model.addAttribute("vo", vo);
	}
	
	// 상세 게시판에서 수정창으로 이동(이벤트)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/eventmodify")
	public void eventregister(Model model, Long bno, Criteria criteria, Authentication auth) { 
		BoardVO vo = boardService.eventGet(bno);
		String name = auth.getName();
		if(!vo.getWriter().equals(name) &&
			!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied"); 
		}
		model.addAttribute("vo", vo);
	}
	
	
	
	
	// 수정 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/goodmodify")
	public String changeGoodModify(BoardVO vo, RedirectAttributes rttr, Criteria criteria, Authentication auth) {
		
		log.info(vo.getAttachList());
		log.info("Principal Name: " + auth.getName());
		log.info("VO Writer: " + vo.getWriter());
		
//		List<BoardAttachVO> attachList = vo.getAttachList();
//		
//		// 추가 리스트
//		List<BoardAttachVO> insertList = attachList.stream().filter(attach -> attach.getBno() == null).collect(Collectors.toList());
//		List<BoardAttachVO> delList = attachList.stream().filter(attach -> attach.getBno() != null).collect(Collectors.toList());
//		
//		log.info("====파일추가목록====");
//		insertList.forEach(e -> log.info(e.getFileName()));
//		
//		log.info("====파일삭제목록====");
//		delList.forEach(e -> log.info(e.getFileName()));
		
		if(boardService.commendUpdate(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "update");
		}
		
		return "redirect:/boardkind/breadGoodList"+criteria.getListLink();
	}
	
	// 수정 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/menumodify")
	public String changemenuModify(BoardVO vo, RedirectAttributes rttr, Criteria criteria, Authentication auth) {
		
		log.info(vo.getAttachList());
		log.info("Principal Name: " + auth.getName());
		log.info("VO Writer: " + vo.getWriter());
		
		if(boardService.menuUpdate(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "update");
		}
		
		return "redirect:/boardkind/breadMenuProposalList"+criteria.getListLink();
	}
	
	// 수정 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/talkmodify")
	public String changetalkModify(BoardVO vo, RedirectAttributes rttr, Criteria criteria, Authentication auth) {
		
		log.info(vo.getAttachList());
		log.info("Principal Name: " + auth.getName());
		log.info("VO Writer: " + vo.getWriter());
		
		if(boardService.talkUpdate(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "update");
		}
		
		return "redirect:/boardkind/CEOtalkList"+criteria.getListLink();
	}
	
	// 수정 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/eventmodify")
	public String changeeventModify(BoardVO vo, RedirectAttributes rttr, Criteria criteria, Authentication auth) {
		
		log.info(vo.getAttachList());
		log.info("Principal Name: " + auth.getName());
		log.info("VO Writer: " + vo.getWriter());
		
		if(boardService.eventUpdate(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "update");
		}
		
		return "redirect:/boardkind/eventList"+criteria.getListLink();
	}
	
	
	
	
	
	
	// 삭제 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/gooddelete")
	public String changeGoodDelete(BoardVO vo, Long bno, RedirectAttributes rttr, Criteria criteria) {
		
		boardService.commendDelete(bno);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "delete");
		
		return "redirect:/boardkind/breadGoodList"+criteria.getListLink();
	}
	
	// 삭제 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/menudelete")
	public String changemenuDelete(BoardVO vo, Long bno, RedirectAttributes rttr, Criteria criteria) {
		
		boardService.menuDelete(bno);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "delete");
		
		return "redirect:/boardkind/breadMenuProposalList"+criteria.getListLink();
	}
	
	// 삭제 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/talkdelete")
	public String changetalkDelete(BoardVO vo, Long bno, RedirectAttributes rttr, Criteria criteria) {
		
		boardService.talkDelete(bno);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "delete");
		
		return "redirect:/boardkind/CEOtalkList"+criteria.getListLink();
	}
	
	// 삭제 처리하는 부분
	@PreAuthorize("isAuthenticated() and principal.username == #vo.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/eventdelete")
	public String changeEventDelete(BoardVO vo, Long bno, RedirectAttributes rttr, Criteria criteria) {
		
		boardService.eventDelete(bno);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "delete");
		
		return "redirect:/boardkind/eventList"+criteria.getListLink();
	}
	
}
