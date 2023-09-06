package kikaboni.project.controller.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.Pagination;
import kikaboni.project.service.BoardService;

@Controller
@RequestMapping("/boardkind")
public class BoardListController {

	@Autowired
	BoardService boardService;
	
	// 빵 추천 게시판으로 이동
	@GetMapping("/breadGoodList")
	public void goodList(Model model, Criteria criteria) { 
		List<BoardVO> list = boardService.commendList(criteria);
		model.addAttribute("list", list);
		model.addAttribute("page", new Pagination(criteria, boardService.totalCount()));
	}
	
	// 빵 메뉴 건의 게시판으로 이동
	@GetMapping("/breadMenuProposalList")
	public void MenuList(Model model) { 
		List<BoardVO> list = boardService.menuList();
		model.addAttribute("list", list);
	}
	
	// 사장님께 한 마디 게시판으로 이동
	@GetMapping("/CEOtalkList")
	public void talkList(Model model) { 
		List<BoardVO> list = boardService.talkList();
		model.addAttribute("list", list);
	}

	
}
