package kikaboni.project.repository;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.MemberVO;

public interface MemberRepository {

	// 사용자 아이디를 읽어오기만 하면 된다.
	MemberVO read(String memberId);
	
	// 회원 추가
	void insert(MemberVO vo);
	
	// 회원 수정
	void update(MemberVO vo);
	
	// 아이디 찾기
	MemberVO selectById(String memberId);
	
	// 회원 삭제
	void delete(String memberId);
	
	// 비밀번호 변경
	void updatePassword(@Param("memberId") String memberId,
						@Param("memberPwd") String memberPwd);
	
}
