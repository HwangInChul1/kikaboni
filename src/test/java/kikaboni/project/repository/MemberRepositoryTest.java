package kikaboni.project.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.MemberVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class MemberRepositoryTest extends AppTest{

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void test() {
		MemberVO id = memberRepository.read("admin");
		log.info(id);
	}

}
