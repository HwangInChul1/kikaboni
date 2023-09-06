package kikaboni.project.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardGetController {

	@Autowired
	BoardService boardService;
	
	// 단일 게시물 출력 처리 및 get 페이지로 이동
	@GetMapping("/goodList/get")
	public String getList1(Model model, Long bno, Criteria criteria) {
		BoardVO vo = boardService.commendGet(bno);
		model.addAttribute("vo", vo);
		return "boardget/goodbreadget";
	}
	
	@GetMapping("/menuList/get")
	public String getList2(Model model, Long bno, Criteria criteria) {
		BoardVO vo = boardService.menuGet(bno);
		model.addAttribute("vo", vo);
		return "boardget/menubreadget";
	}
	
	@GetMapping("/talkList/get")
	public String getList3(Model model, Long bno, Criteria criteria) {
		BoardVO vo = boardService.talkGet(bno);
		model.addAttribute("vo", vo);
		return "boardget/talkget";
	}
	
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		
		return new ResponseEntity<>(boardService.getAttachList(bno),HttpStatus.OK);
	}
	
	@GetMapping("/getAttachFileInfo")
	@ResponseBody
	public ResponseEntity<BoardAttachVO> getAttach(String uuid){
		
		return new ResponseEntity<>(boardService.getAttach(uuid), HttpStatus.OK);
	}
	
	
	
	
}
