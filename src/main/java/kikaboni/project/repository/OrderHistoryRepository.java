package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.OrderHistoryVO;

public interface OrderHistoryRepository {

	// 메뉴 전체 조회
	List<OrderHistoryVO> orderList();
	
	// 메뉴 하나의 조회
	OrderHistoryVO getList(String memberId);
}
