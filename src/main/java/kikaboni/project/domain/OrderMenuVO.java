package kikaboni.project.domain;

import java.util.List;

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
public class OrderMenuVO {

	private Long mno;
	private Long ono;
	private String orderMenu;
	private List<MenuVO> menuList;
}
