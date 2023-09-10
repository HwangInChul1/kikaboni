package kikaboni.project.controller.order;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuVO;
import kikaboni.project.domain.OrderHistoryVO;
import kikaboni.project.domain.Pagination;
import kikaboni.project.service.MenuService;
import kikaboni.project.service.OrderHistoryService;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class OrderMenuController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	OrderHistoryService historyService;
	
	// 나의 주문 내역
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member/myMenuList")
	public String myMenuOrder(Model model, Principal principal, Criteria criteria) {
		
		String name = principal.getName();
		
		List<OrderHistoryVO> myOrder = historyService.getList(name, criteria);
		
		model.addAttribute("myOrder", myOrder);
		model.addAttribute("page", new Pagination(criteria, historyService.orderTotalCount()));
		
		return "member/myOrderMenu";
	}
	
	
	// 들어온 주문 으로 이동(관리자)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/menuList")
	public String menuOrder(Model model, Criteria criteria) {
		
		List<OrderHistoryVO> orderList = historyService.orderList(criteria);
		
		model.addAttribute("list", orderList);
		model.addAttribute("page", new Pagination(criteria, historyService.orderTotalCount()));
		
		return "admin/menuBoard";
	}
	
	// 주문 내역으로 이동(관리자)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/menuHistory")
	public String menuHistory() {
		return "admin/menuHistory";
	}
	
	
	// 주문 처리
	//@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping("order/myOrder")
	@ResponseBody
	public ResponseEntity<String> mymenuList(@RequestBody List<OrderHistoryVO> orderList, Principal principal){
		
		log.info(orderList);
		
		String name = principal.getName();
		
		for(OrderHistoryVO vo : orderList) {
			OrderHistoryVO ordervo = new OrderHistoryVO();
			ordervo.setMno(vo.getMno());
			ordervo.setProCount(vo.getProCount());
			ordervo.setMemberId(name);
			
			historyService.menuInsert(ordervo);
			
			log.info(ordervo);
		}
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
		

	
	
	
	
}
