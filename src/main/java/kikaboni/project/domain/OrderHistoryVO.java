package kikaboni.project.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	private Long ono; // 주문에 따른 테이블 번호
	private String memberId; // 회원id
	private Date orderDate; // 주문 날짜
	private Long proCount; // 주문 개수
	private Long mno; // 상품 번호
	
	private MenuVO menu;
	private List<MenuVO> menuList;
	
}
