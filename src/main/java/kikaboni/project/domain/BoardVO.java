package kikaboni.project.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
public class BoardVO {

	private Long bno;
	private Long board_id;
	private String memberId;
	private String title;
	private String content;
	private String writer;
	private int replyCnt;
	
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private Date writeDate;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private Date updateDate;
	
	private List<BoardAttachVO> attachList;
	
	private List<BoardKindVO> myTextList;
	
}
