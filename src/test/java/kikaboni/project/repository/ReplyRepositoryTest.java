package kikaboni.project.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyRepositoryTest extends AppTest{

	@Autowired
	private ReplyRepository replyRepository;
	
	@Test
	@Ignore
	public void test() {
		for (int i = 0; i < 200; i++) {
			ReplyVO vo = new ReplyVO();
			vo.setBno(1L);
			vo.setReply("내용" + i);
			vo.setReplyer("작성자"+i);
		replyRepository.insert(vo);
		}
		
	}
	
	@Test
	@Ignore
	public void test1() {
		replyRepository.delete(200L);
	}
	
	@Test
	@Ignore
	public void test2() {
		List<ReplyVO> list = replyRepository.getList(1L, new Criteria());
		log.info(list);
	}
	

	
	
		
}


