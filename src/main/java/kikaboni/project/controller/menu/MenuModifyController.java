package kikaboni.project.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kikaboni.project.domain.MenuVO;
import kikaboni.project.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuModifyController {

//	@Autowired
//	MenuService menuService;
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping("/menuUpdate")
//	public void menuUpdateForm(Long mno, Model model) {
//		MenuVO vo = menuService.breadGet(mno);
//		model.addAttribute("vo", vo);
//	}
	
	
	
}



