package kikaboni.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kikaboni.project.domain.MemberVO;
import kikaboni.project.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberRepository.read(username);
		if(vo == null) {
			throw new UsernameNotFoundException(username); // UsernameNotFoundException은 존재하는 메소드이다. 
														   // 그런데 매개변수를 하나 받음 
		}
		
		return new CustomUser(vo);
	}

}
