package kikaboni.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kikaboni.project.domain.AuthVO;
import kikaboni.project.domain.MemberVO;
import kikaboni.project.exception.PasswordMisMatchException;
import kikaboni.project.repository.AuthRepository;
import kikaboni.project.repository.MemberRepository;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional // 2개 동시에 sql문을 실행하기에 Transactional 어노테이션을 붙이기
	@Override
	public void join(MemberVO vo) {
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd())); 
		AuthVO authvo = new AuthVO();
		
		if(vo.getMemberId().equals("admin")) {
			authvo = new AuthVO(vo.getMemberId(), "ROLE_ADMIN");
		} else {
			authvo = new AuthVO(vo.getMemberId(), "ROLE_MEMBER");
		}
		
		memberRepository.insert(vo);
		authRepository.insert(authvo);	
	}

	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Override
	public void modify(MemberVO vo) {
			memberRepository.update(vo);
	}

	@Override
	public void remove(String memberId) {
			memberRepository.delete(memberId);
	}

	@Transactional
	@Override
	public void changePwd(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		MemberVO vo = memberRepository.selectById(memberId);
		log.info(vo.getMemberPwd());
		
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) { // 현재 패스워드와, 가져온 패스워드가 다르다면
			throw new PasswordMisMatchException();
		}
		
		memberRepository.updatePassword(memberId, passwordEncoder.encode(newPwd));
	}
}
