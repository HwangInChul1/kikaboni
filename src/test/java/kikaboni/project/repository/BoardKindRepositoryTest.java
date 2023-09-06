package kikaboni.project.repository;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardKindVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardKindRepositoryTest extends AppTest{

	@Autowired
	BoardKindRepository boardKindRepository;
	
	@Test
//	@Ignore
	public void testInsert1() {
		BoardKindVO vo = new BoardKindVO();
		vo.setBoard_id(1L);
		vo.setBoard_name("빵 추천합니다.");
		vo.setBoard_content("빵 추천 게시판");
		boardKindRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testInsert2() {
		BoardKindVO vo = new BoardKindVO();
		vo.setBoard_id(2L);
		vo.setBoard_name("빵 메뉴 건의");
		vo.setBoard_content("빵 메뉴 건의하는 게시판");
		boardKindRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testInsert3() {
		BoardKindVO vo = new BoardKindVO();
		vo.setBoard_id(3L);
		vo.setBoard_name("사장님께 한 마디");
		vo.setBoard_content("사장님께 힘나는 한 마디 하는 게시판");
		boardKindRepository.insert(vo);
	}
	

}
