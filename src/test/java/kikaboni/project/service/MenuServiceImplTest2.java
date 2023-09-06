package kikaboni.project.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.MenuAttachVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class MenuServiceImplTest2 extends AppTest{

	@Autowired
	MenuService menuService;
	
	@Test
	public void test() {
		List<MenuAttachVO> list = menuService.menuAttachList(1L);
		log.info(list);
	}

}
