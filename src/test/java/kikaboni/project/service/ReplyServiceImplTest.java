package kikaboni.project.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.ReplyVO;


public class ReplyServiceImplTest extends AppTest{

	@Autowired
	ReplyService replyService;
	
	@Test
	public void test() {
		ReplyVO vo = ReplyVO.builder()
				.bno(1L)
				.reply("작성!!")
				.replyer("작성자!!!").build();
		replyService.insert(vo);
	}
	
	
	

}
