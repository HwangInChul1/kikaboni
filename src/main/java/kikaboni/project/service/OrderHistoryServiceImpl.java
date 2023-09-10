package kikaboni.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.OrderHistoryVO;
import kikaboni.project.repository.MenuRepository;
import kikaboni.project.repository.OrderHistoryRepository;


@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	OrderHistoryRepository historyRepository;
	
	@Override
	public List<OrderHistoryVO> orderList(Criteria criteria) {
		return historyRepository.orderList(criteria);
	}

	@Override
	public List<OrderHistoryVO> getList(String memberId, Criteria criteria) {
		return historyRepository.getList(memberId, criteria);
	}


	@Override
	public void menuInsert(OrderHistoryVO vo) {
		
		
		historyRepository.menuSelectKey(vo);
		
	}

	@Override
	public Integer menuSelectKey(OrderHistoryVO vo) {
		return historyRepository.menuSelectKey(vo);
	}

	@Override
	public int orderTotalCount() {
		return historyRepository.orderTotalCount();
	}



}

