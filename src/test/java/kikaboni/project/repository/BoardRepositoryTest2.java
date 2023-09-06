package kikaboni.project.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardRepositoryTest2 extends AppTest{

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void test() {
		List<BoardVO> list = boardRepository.commendList(new Criteria());
		list.forEach(b -> {
			log.info(b);
		});
	}

}
