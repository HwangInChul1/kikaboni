package kikaboni.project.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardVO;

public class BoardRepositoryTest extends AppTest{

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void test() {
		for (int i = 0; i < 200; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle("제목"+i);
			vo.setContent("내용" + i);
			vo.setWriter("작성자" + i);
			boardRepository.commendRegister(vo);
		}
	}

}
