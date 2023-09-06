package kikaboni.project.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuAttachVO;
import kikaboni.project.domain.MenuVO;
import kikaboni.project.domain.Pagination;
import kikaboni.project.service.BoardService;
import kikaboni.project.service.MenuService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/menu")
@Log4j
public class MenuController {

	@Autowired
	MenuService menuService;
	
	//@Autowired
	//BoardService boardService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/menuRegister") // 빵 등록 화면으로 이동
	public void RegisterForm() {
		
	}
	
//	// 빵 메뉴 조회 
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping("/menuUpdate")
//	public void UpdateForm(Long mno, Model model) {
//		
//		MenuVO get = menuService.breadGet(mno);
//		model.addAttribute("get", get);
//		log.info(get);
//	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/menuUpdate") // 빵 수정 처리
//	public String Update(MenuVO vo, RedirectAttributes rttr) {
//		
//		menuService.menuUpdate(vo);
//		
//		return "redirect:/menu/orderMenu_bread";
//	}
//	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/menuDelete") // 빵 삭제 처리
//	public String Delete(String name, RedirectAttributes rttr) {
//		
//		menuService.menuDelete(name);
//		
//		return "redirect:/menu/orderMenu_bread";
//	}
	
	// 빵 메뉴 조회 
	@GetMapping("/goodBreadGet")
	public String breadGet(Long mno, Model model) {
		
		MenuVO vo = menuService.breadGet(mno);
		model.addAttribute("vo", vo);
		
		return "menu/breadMenuGet";
	}
	
	
	@GetMapping("/orderMenu_bread") // 빵 메뉴 화면으로 이동(이동할떄 mno를 가지고 가야한다?)
	public String menuBread(Model model, Criteria criteria) {
	
		List<MenuVO> list = menuService.breadList(criteria); // 게시물 목록을 가져옴
		
		for(MenuVO m : list) {
			Long mno = m.getMno();
			List<MenuAttachVO> menuAttachList = menuService.menuAttachList(mno);
			m.setAttachList(menuAttachList);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("page", new Pagination(criteria, menuService.getTotalCount()));
		
		return "menu/orderMenu_bread";
	}
	
	@GetMapping("/orderMenu_cake") // 케이크 메뉴 화면으로 이동
	public String menuCake(Model model) {
	
		List<MenuVO> list = menuService.cakeList();
		model.addAttribute("list", list);
		
		return "menu/orderMenu_cake";
	}
	
	@GetMapping("/orderMenu_coffee") // 커피 메뉴 화면으로 이동
	public String menuCoffee(Model model) {
	
		List<MenuVO> list = menuService.coffeeList();
		model.addAttribute("list", list);
		
		return "menu/orderMenu_coffee";
	}

	@PostMapping("/breadOrder") // 빵 등록 화면에서 등록하면 주문메뉴로 이동
	public String menuMove(MenuVO vo, RedirectAttributes rttr){
		
		log.info(vo.getAttachList());
		
		menuService.menuRegister(vo);
		log.info(vo);
		return "redirect:/menu/orderMenu_bread";
	}
	
	@PostMapping("/breadSubmit")
	public String menuOrder(MenuVO vo, Model model) {
		
		
		
		return "/menu/breadSubmit";
	}
	
	
	
	@GetMapping("/menuAttachList")
	@ResponseBody
	public ResponseEntity<List<MenuAttachVO>> menuAttachList(Long mno){
		return new ResponseEntity<>(menuService.menuAttachList(mno),HttpStatus.OK);
	}
	
	@GetMapping("/menuAttachFileInfo")
	@ResponseBody
	public ResponseEntity<MenuAttachVO> menuAttach(String uuid){
		return new ResponseEntity<>(menuService.menuAttach(uuid), HttpStatus.OK);
	}
	
}










