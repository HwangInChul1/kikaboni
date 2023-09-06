package kikaboni.project.service;

import java.util.Map;

import kikaboni.project.domain.MemberVO;

public interface MemberService {
	
	void join(MemberVO vo);

	MemberVO read(String memberId);
	
	void modify(MemberVO vo);
	
	void remove(String memberId);
	
	void changePwd(Map<String, String> memberMap);
	
	
}
