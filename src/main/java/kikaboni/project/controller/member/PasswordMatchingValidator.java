package kikaboni.project.controller.member;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kikaboni.project.domain.MemberVO;


public class PasswordMatchingValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberVO vo = (MemberVO) target;
		if(vo.getMemberPwd().isEmpty() && !vo.isPasswordEqualToConfirmPassword()) {
			
		}
	}

}
