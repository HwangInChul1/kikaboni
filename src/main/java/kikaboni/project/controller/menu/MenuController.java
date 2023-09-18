package kikaboni.project.controller.menu;

import java.security.Principal;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/menuRegister") 
	public void RegisterForm() {
		
	}
		
	@GetMapping("/orderMenu_bread") 
	public String menuBread(Model model, Criteria criteria) {
		
		List<MenuVO> list = menuService.breadList(criteria); 
		log.info(list);
			
		for(MenuVO m : list) {
			Long mno = m.getMno();
			List<MenuAttachVO> menuAttachList = menuService.menuAttachList(mno);
			m.setAttachList(menuAttachList);
		}
			
		model.addAttribute("list", list);
		model.addAttribute("page", new Pagination(criteria, menuService.getTotalCount()));
		
		return "menu/orderMenu_bread";
	}
		
	@GetMapping("/orderMenu_cake") 
	public String menuCake(Model model, Criteria criteria) {
		
		List<MenuVO> list = menuService.cakeList(criteria);
		model.addAttribute("list", list);
			
		return "menu/orderMenu_cake";
	}
		
	@GetMapping("/orderMenu_coffee") 
	public String menuCoffee(Model model, Criteria criteria) {
		
		List<MenuVO> list = menuService.coffeeList(criteria);
		model.addAttribute("list", list);
			
		return "menu/orderMenu_coffee";
	}

	@PostMapping("/breadOrder") 
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












