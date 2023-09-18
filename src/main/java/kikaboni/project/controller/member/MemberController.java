package kikaboni.project.controller.member;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kikaboni.project.domain.MemberVO;
import kikaboni.project.exception.PasswordMisMatchException;
import kikaboni.project.service.MemberService;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, Authentication auth, RedirectAttributes rttr) {
		
		String uri = request.getHeader("Referer"); // 로그인 전 사용자가 보던 페이지
		if(uri != null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		log.info(uri);
		
		if(auth != null) { // 이미 로그인이라는 뜻
			rttr.addFlashAttribute("duplicationLogin", "이미 로그인중입니다.");
			if(uri == null) uri="/";
			return "redirect:"+uri;
		}
		return "member/login";
	}
	
	// 회원정보 수정창으로 이동
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/mypageUpdate")
	public String mypagemodify(Principal principal, Model model) {
		String memberId = principal.getName();
		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
		
		return "member/mypageUpdate";
	}
		
	// 회원 정보 수정
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping("/member/modify")
	public String modify(MemberVO vo, RedirectAttributes rttr) {
		memberService.modify(vo);
		rttr.addFlashAttribute("result", "modify");
		return "redirect:/mypage";
	}
	
	// 마이페이지
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping({"/mypage", "mypage/{path}"})
	public String myPage(Principal principal, Model model, @PathVariable(required = false) String path) {
		String memberId = principal.getName(); 
		if(path == null) {
			MemberVO vo = memberService.read(memberId); 
			model.addAttribute("vo", vo); 
			return "member/mypage"; 
		}
		return "member/" + path;
	}
	
	// 관리자 페이지
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return "admin/adminPage";
	}
	
	// 접근 금지된 페이지
	@GetMapping("/accessDenied")
	public String accessDenided() {
		return "accessError";
	}
	
	// 비밀번호 변경 처리
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping(value= "/mypage/changePwd", produces = "application/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> changePwd(@RequestParam Map<String, String> memberMap){
		log.info(memberMap);
		
		try {
			memberService.changePwd(memberMap);
		} catch (PasswordMisMatchException e) {
			return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("비밀번호가 변경 되었습니다.",HttpStatus.OK);
	}
	
	
	@GetMapping("/member/joinForm")
	public String joinForm(MemberVO memberVO) {
		return "member/join";
	}
	

	@PostMapping("/member/join")
	public String join(MemberVO memberVO, RedirectAttributes rttr) {
		memberService.join(memberVO);
		return "redirect:/";
	}
	
	
	@PostMapping("/member/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> idDuplicateCheck(String memberId){
		
		MemberVO vo = memberService.read(memberId);
		return vo == null ? new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK)
				: new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
	}
	

	
	
	
	
}
