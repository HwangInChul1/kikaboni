package kikaboni.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kikaboni.project.domain.OrderHistoryVO;
import kikaboni.project.repository.OrderHistoryRepository;


@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	OrderHistoryRepository historyRepository;
	
	@Override
	public List<OrderHistoryVO> orderList() {
		return historyRepository.orderList();
	}

	@Override
	public OrderHistoryVO getList(String memberId) {
		return historyRepository.getList(memberId);
	}

}
