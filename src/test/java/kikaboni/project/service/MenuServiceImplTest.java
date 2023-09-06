package kikaboni.project.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.MenuAttachVO;
import kikaboni.project.domain.MenuVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class MenuServiceImplTest extends AppTest{

	@Autowired
	MenuService menuService;
	
	@Test
	@Ignore
	public void test() {
		MenuVO vo = new MenuVO();
		Long mno = vo.getMno();
		List<MenuAttachVO> list = menuService.menuAttachList(2L);
		log.info(list);
	}
	
	@Test
	public void test2() {
		MenuVO get = menuService.breadGet(2L);
		log.info(get);
	}

}
