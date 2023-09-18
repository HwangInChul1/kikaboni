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
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/new")
	public ResponseEntity<String> newReply(@RequestBody ReplyVO vo){
		
		int result = replyService.insert(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/newMenu")
	public ResponseEntity<String> newmenuReply(@RequestBody ReplyVO vo){
		
		int result = replyService.menuinsert(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/newTalk")
	public ResponseEntity<String> newtalkReply(@RequestBody ReplyVO vo){
		
		int result = replyService.talkinsert(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/newEvent")
	public ResponseEntity<String> newEventReply(@RequestBody ReplyVO vo){
		
		int result = replyService.eventinsert(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/commend/{rno}")
	public ResponseEntity<ReplyVO> getreply(@PathVariable Long rno){
		ReplyVO read = replyService.read(rno);
		return new ResponseEntity<ReplyVO>(read, HttpStatus.OK); 
	}
	
	@GetMapping("/menu/{rno}")
	public ResponseEntity<ReplyVO> menureply(@PathVariable Long rno){
		ReplyVO read = replyService.menuread(rno);
		return new ResponseEntity<ReplyVO>(read, HttpStatus.OK); // 성공응답과 동시에 댓글을 반환
	}

	@GetMapping("/talk/{rno}")
	public ResponseEntity<ReplyVO> talkreply(@PathVariable Long rno){
		ReplyVO read = replyService.talkread(rno);
		return new ResponseEntity<ReplyVO>(read, HttpStatus.OK); // 성공응답과 동시에 댓글을 반환
	}

	@GetMapping("/event/{rno}")
	public ResponseEntity<ReplyVO> eventreply(@PathVariable Long rno){
		ReplyVO read = replyService.eventread(rno);
		return new ResponseEntity<ReplyVO>(read, HttpStatus.OK); // 성공응답과 동시에 댓글을 반환
	}
	
	
	@GetMapping("/commendPages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable Long bno, @PathVariable int page){
		Criteria criteria = new Criteria(page, 5);
		return new ResponseEntity<>(replyService.getList(bno, criteria), HttpStatus.OK);
	}
	
	@GetMapping("/menuPages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> menuList(
			@PathVariable Long bno, @PathVariable int page){
		Criteria criteria = new Criteria(page, 5);
		return new ResponseEntity<>(replyService.menuList(bno, criteria), HttpStatus.OK);
	}

	@GetMapping("/talkPages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> talkList(
			@PathVariable Long bno, @PathVariable int page){
		Criteria criteria = new Criteria(page, 5);
		return new ResponseEntity<>(replyService.talkList(bno, criteria), HttpStatus.OK);
	}

	@GetMapping("/eventPages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> eventList(
			@PathVariable Long bno, @PathVariable int page){
		Criteria criteria = new Criteria(page, 5);
		return new ResponseEntity<>(replyService.eventList(bno, criteria), HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/commend/{rno}")
	public ResponseEntity<String> replyUpdate(@RequestBody ReplyVO vo, @PathVariable Long rno){
		int result = replyService.update(vo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/menu/{rno}")
	public ResponseEntity<String> replyMenuUpdate(@RequestBody ReplyVO vo, @PathVariable Long rno){
		int result = replyService.menuupdate(vo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/talk/{rno}")
	public ResponseEntity<String> replytalkUpdate(@RequestBody ReplyVO vo, @PathVariable Long rno){
		int result = replyService.talkupdate(vo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/event/{rno}")
	public ResponseEntity<String> replyEventUpdate(@RequestBody ReplyVO vo, @PathVariable Long rno){
		int result = replyService.eventupdate(vo);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/commend/{rno}")
	public ResponseEntity<String> replyDelete(@PathVariable Long rno){		
		
		int result = replyService.delete(rno);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/menu/{rno}")
	public ResponseEntity<String> replyMenuDelete(@PathVariable Long rno){		
		
		int result = replyService.menudelete(rno);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/talk/{rno}")
	public ResponseEntity<String> replyTalkDelete(@PathVariable Long rno){		
		
		int result = replyService.talkdelete(rno);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/event/{rno}")
	public ResponseEntity<String> replyEventDelete(@PathVariable Long rno){		
		
		int result = replyService.eventdelete(rno);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
