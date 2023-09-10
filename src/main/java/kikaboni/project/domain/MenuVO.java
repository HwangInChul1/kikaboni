package kikaboni.project.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

	private Long mno; //메뉴 번호
	private String proId; // 상품id
	private String name;   // 상품 이름
	private String type; // 상품 타입
	private Long price; // 상품 가격

	private List<MenuAttachVO> attachList;
	
}

