package kikaboni.project.service;

import kikaboni.project.domain.OrderMenuVO;

public interface OrderMenuService {

	// 주문번호에 따른 전체 메뉴
	OrderMenuVO menuList(Long ono);
}
