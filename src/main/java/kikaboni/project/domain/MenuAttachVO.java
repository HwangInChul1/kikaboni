package kikaboni.project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuAttachVO {
	private Long mno;
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
}
