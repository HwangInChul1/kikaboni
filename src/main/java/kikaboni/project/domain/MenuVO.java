package kikaboni.project.domain;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MenuVO {

	private Long mno; //메뉴 번호
	private String name;   // 메뉴 이름
	private String type; // 메뉴 타입
	private Long price; // 메뉴 가격
	private Long mcount; // 메뉴 개수 
	
	private List<MenuAttachVO> attachList;
	
}
