package kikaboni.project.controller.order;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kikaboni.project.domain.AuthVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MemberVO;
import kikaboni.project.domain.MenuVO;
import kikaboni.project.service.MenuService;
import kikaboni.project.service.OrderHistoryService;
import kikaboni.project.service.OrderMenuService;
import lombok.extern.log4j.Log4j;



@Controller
@RequestMapping("/order")
@Log4j
public class OrderMenuController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	OrderHistoryService historyService;
	
	@Autowired
	OrderMenuService orderMenuService;
	
	// 나의 주문 내역
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@GetMapping("/myMenuList")
	public String myMenuOrder() {
		
		return "member/myOrderMenu";
	}
	
	
	// 들어온 주문 으로 이동(관리자)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/menuList")
	public String menuOrder() {
		
		return "admin/menuBoard";
	}
	
	// 주문 내역으로 이동(관리자)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/menuHistory")
	public String menuHistory() {
		return "admin/menuHistory";
	}
	
	
	// MenuVO의 값을 넘겨주면 된다.
	@PostMapping("/myOrder")
	@ResponseBody
	public ResponseEntity<String> mymenuList(@RequestBody List<MenuVO> menuList){
		
		MenuVO vo = new MenuVO();
		menuService.menuRegister(vo);
		log.info(vo);
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	// orderMenu_bread에서 주문한 메뉴들을, 이 메소드가 받아서 menuBoard로 전달한다.
	@PostMapping("/orderSubmit")
	public String menuSubmit(MenuVO vo, Model model, Principal principal, Authentication auth) {
	
	
		
		String name = principal.getName();
		log.info(name);
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		log.info(authorities);
	
		historyService.getList(name);

		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("vo", vo);
			model.addAttribute("name", name);
			return "admin/menuBoard";
		} else if(authorities.contains(new SimpleGrantedAuthority("ROLE_MEMBER"))) {
			model.addAttribute("vo", vo);
			model.addAttribute("name", name);
			return "member/myOrderMenu";
		}
		
		return "menu/orderMenu_main";
	}
	
	
	
	
	
	
}
