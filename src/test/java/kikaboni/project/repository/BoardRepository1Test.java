package kikaboni.project.repository;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardKindVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardRepository1Test extends AppTest{

	@Autowired
	BoardRepository boardRepository;
	
	// 전체 게시물 출력
	@Test
	@Ignore
	public void Alltest() {
		List<BoardVO> list = boardRepository.commendList(new Criteria());
		log.info(list);
	} // 테스트용으로 하나 넣어서 리스트가 출력되었는지 확인해보았음
	
	// 단일 게시물 출력
	@Test
	@Ignore
	public void getTest() {
		BoardVO vo = boardRepository.commendGet(2L);
		log.info(vo);
	}
	
	// 게시물 삽입
	@Test
	@Ignore
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목5");
		vo.setContent("내용5");
		vo.setWriter("작성자5");
		boardRepository.commendRegister(vo);
		log.info(vo);
	}
	
	// 게시물 수정
	@Test
	@Ignore
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setBno(3L);
		vo.setTitle("제목3");
		vo.setContent("내용3");
		vo.setWriter("작성자3");
		boardRepository.commendUpdate(vo);
	}
	
	// 게시물 삭제
	@Test
	@Ignore
	public void deleteTest() {
		boardRepository.commendDelete(4L);
	}
	
	@Test
	public void myText() {
		List<BoardVO> textList = boardRepository.myTextlist("member1", new Criteria());
		log.info(textList);
	}
	
}
