package kikaboni.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.OrderHistoryVO;

public interface OrderHistoryRepository {

	// 주문 전체 조회
	List<OrderHistoryVO> orderList(Criteria criteria);
	
	// 사용자에 따른 주문 조회
	List<OrderHistoryVO> getList(@Param("memberId") String memberId, @Param("criteria") Criteria criteria);

	// 주문 내용 추가
	Integer menuInsert(OrderHistoryVO vo);
	
	// 메뉴 추가하면 ono를 키로 얻음
	Integer menuSelectKey(OrderHistoryVO vo);
	
	// 주문 전체 개수
	int orderTotalCount();
	
	// 회원별 주문 개수
	int MyOrderTotalCount(String memberId);
	
}

