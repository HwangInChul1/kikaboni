package kikaboni.project.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyVO {

	private Long rno;
	private Long bno;
	private Long board_id;
	private String reply;
	private String replyer;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime replyDate;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime updateDate;
	
}
