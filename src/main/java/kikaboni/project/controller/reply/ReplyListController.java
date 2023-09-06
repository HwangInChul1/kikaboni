package kikaboni.project.controller.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyPageDTO;
import kikaboni.project.domain.ReplyVO;
import kikaboni.project.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyListController {

	@Autowired
	private ReplyService replyService;
	
	// 게시글 작성 컨트롤러
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/new")
	public ResponseEntity<String> newReply(@RequestBody ReplyVO vo){
		
		int result = replyService.insert(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 게시글 조회 컨트롤러
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyVO> getreply(@PathVariable Long rno){
		ReplyVO read = replyService.read(rno);
		return new ResponseEntity<ReplyVO>(read, HttpStatus.OK); // 성공응답과 동시에 댓글을 반환
	}
	
	// 게시물에 따른 게시글 조회 컨트롤러
	@GetMapping("/pages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable Long bno, @PathVariable int page){
		Criteria criteria = new Criteria(page, 5);
		return new ResponseEntity<>(replyService.getList(bno, criteria), HttpStatus.OK);
	}
	
	// 게시글 수정 컨트롤러
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/{rno}")
	public ResponseEntity<String> replyUpdate(@RequestBody ReplyVO vo, @PathVariable Long rno){
		int result = replyService.update(vo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	
	// 게시글 삭제 컨트롤러
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> replyDelete(@PathVariable Long rno){		
		
		int result = replyService.delete(rno);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
