package kikaboni.project.service;

import java.util.List;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.OrderHistoryVO;

public interface OrderHistoryService {

	// 메뉴 전체 조회
	List<OrderHistoryVO> orderList(Criteria criteria);
	
	// 메뉴 하나의 조회
	List<OrderHistoryVO> getList(String memberId, Criteria criteria);
	
	// 메뉴 추가
	void menuInsert(OrderHistoryVO vo);
	
	// 메뉴 추가하면 ono를 키로 얻음
	Integer menuSelectKey(OrderHistoryVO vo);
	
	// 주문 전체 개수
	int orderTotalCount();
	
}
