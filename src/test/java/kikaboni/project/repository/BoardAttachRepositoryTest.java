package kikaboni.project.repository;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardAttachVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardAttachRepositoryTest extends AppTest{

	@Autowired
	BoardAttachRepository boardAttachRepository;
	
	@Test
	@Ignore
	public void test() {
		BoardAttachVO vo = new BoardAttachVO();
		vo.setBno(1L);
//		vo.setFileName("test01.txt");
		vo.setFileName("test02.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		boardAttachRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testSelectByBno() {
		boardAttachRepository.selectByBno(1L)
			.forEach(file -> log.info(file));
	}
	
	@Test
//	@Ignore
	public void testDelete() {
		// 데이터베이스에 저장된 uuid값을 참고
		String uuid = "5d878eb8-5da4-4826-b4a9-a81e49da6cb1";
		boardAttachRepository.delete(uuid);
	}
	
	

	
}
