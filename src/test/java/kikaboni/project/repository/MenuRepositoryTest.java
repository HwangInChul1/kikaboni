package kikaboni.project.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class MenuRepositoryTest extends AppTest{

	@Autowired
	MenuRepository menuRepository;
	
	@Test
	@Ignore
	public void test() {
		List<MenuVO> breadList = menuRepository.breadList(new Criteria());
		log.info(breadList);
	}
	
	@Test
	@Ignore
	public void test1() {
		MenuVO vo = new MenuVO();
		vo.setName("dds");
		vo.setPrice(33387L);
		vo.setType("I");
		Integer key = menuRepository.insertSelectKey(vo);
		log.info(key);
	}
	
	@Test
	@Ignore
	public void test2() {
		MenuVO vo = menuRepository.breadGet(1L);
		log.info(vo);
	}
	
	

}
