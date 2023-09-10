package kikaboni.project.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.OrderHistoryVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class OrderHistoryRepositoryTest extends AppTest{

	@Autowired
	OrderHistoryRepository historyRepository;
	
	@Test
	@Ignore
	public void test() {
		OrderHistoryVO vo = new OrderHistoryVO();
		vo.setMno(1L);
//		vo.setName("zzz");
//		vo.setPrice(6000L);
		vo.setMemberId("admin");
	//	vo.setMcount(1L);
		historyRepository.menuInsert(vo);
	}
	
	@Test
	public void test1() {
		List<OrderHistoryVO> orderList = historyRepository.orderList(new Criteria());
		log.info(orderList);
	}
	
	@Test
	public void test2() {
		List<OrderHistoryVO> list = historyRepository.getList("admin", new Criteria());
		log.info(list);
	}

}
