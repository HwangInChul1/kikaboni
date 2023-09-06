package kikaboni.project.repository;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.MenuAttachVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class MenuAttachRepositoryTest extends AppTest{

	@Autowired
	MenuAttachRepository attachRepository;
	
	// 첨부파일 추가
	@Test
	@Ignore
	public void testInsert() {
		MenuAttachVO vo = new MenuAttachVO();
	//	vo.setName("빵3");
		vo.setFileName("text03.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		attachRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testSelect() {
		List<MenuAttachVO> list = attachRepository.selectByMno(1L);
		log.info(list);
	}
	
	
	@Test
	@Ignore
	public void testDelete() {
		String uuid = "04133f12-6640-431e-bad8-6f2ca409d762";
		attachRepository.delete(uuid);	
	}
	
	@Test
	@Ignore
	public void test() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		log.info(sdf.format(now));
		
		File uploadPath = new File("c:/storage",sdf.format(now));
		log.info(uploadPath);
		
	}
	
	@Test
	public void test2() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		log.info(sdf.format(now));
		
//		File uploadPath = new File("c:/storage","test");
		// 이렇게 test 폴더 하나만 생성하려면 컨트롤러에서 mkdirs가 아닌 mkdir 사용
//		log.info(uploadPath);
		
		File uploadPath = new File("c:/storage",sdf.format(now));
		log.info(uploadPath.exists());
		
		if(!uploadPath.exists()) { // 파일이 존재하지 않는다면
			uploadPath.mkdirs(); 
		}
	}
	
	
}




