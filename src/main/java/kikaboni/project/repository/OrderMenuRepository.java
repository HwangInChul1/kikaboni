package kikaboni.project.repository;

import kikaboni.project.domain.OrderMenuVO;

public interface OrderMenuRepository {

	// 주문번호에 따른 전체 메뉴
	OrderMenuVO menuList(Long ono);
}
