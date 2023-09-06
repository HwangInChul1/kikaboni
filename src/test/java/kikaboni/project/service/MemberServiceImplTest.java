package kikaboni.project.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.AuthVO;
import kikaboni.project.domain.MemberVO;
import kikaboni.project.repository.AuthRepository;
import lombok.extern.log4j.Log4j;

@Log4j
public class MemberServiceImplTest extends AppTest{

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Test
	@Ignore
	public void test() {
		MemberVO vo = new MemberVO();
		vo.setMemberId("admin");
		vo.setMemberPwd("1234");
		vo.setMemberName("관리자");
		vo.setEmail("admin@test.com");
		vo.setPhoneNumber("010-1111-2222");
		vo.setAddress("대구광역시 남구");
		memberService.join(vo);
		
		AuthVO authVO = new AuthVO("admin","ROLE_ADMIN");
		authRepository.insert(authVO);
	}
	
	@Test
	@Ignore
	public void test2() {
		MemberVO vo = new MemberVO();
		vo.setMemberId("member1");
		vo.setMemberPwd("1234");
		vo.setMemberName("회원1");
		vo.setEmail("member1@test.com");
		vo.setPhoneNumber("010-1234-5678");
		vo.setAddress("대구");
		memberService.join(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void test3() {
		MemberVO vo = new MemberVO();
		vo.setMemberId("member2");
		vo.setMemberPwd("1234");
		vo.setMemberName("회원2");
		vo.setEmail("member2@test.com");
		vo.setPhoneNumber("010-7897-4567");
		vo.setAddress("대구");
		memberService.join(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		memberService.remove("admin");
	}
	

}
