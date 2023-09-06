package kikaboni.project.domain;

import java.util.Date;
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
public class OrderHistoryVO {

	private Long ono;
	private String memberId;
	private Date orderDate;
	private List<OrderMenuVO> menuList;
}
