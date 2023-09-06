package kikaboni.project.controller.introduce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {

	
	@GetMapping("/breadHouse")
	public void breadIntroduce() {
		
	}
}
