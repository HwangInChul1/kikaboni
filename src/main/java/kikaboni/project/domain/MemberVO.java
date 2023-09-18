package kikaboni.project.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO{
	
	@NotEmpty
	private String memberId;
	
	@Size(min=6)
	private String memberPwd;
	
	@NotEmpty
	private String memberPwdConfirm;
	
	@NotEmpty
	private String memberName;
	
	@Email
	private String email;
	
	@NotEmpty
	private String phoneNumber;
	
	@NotEmpty
	private String address;
	private boolean enabled;
	
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private List<AuthVO> authList;
	
	public boolean isPasswordEqualToConfirmPassword() {
		return memberPwd.equals(memberPwdConfirm);
	}
	
	
	
}

