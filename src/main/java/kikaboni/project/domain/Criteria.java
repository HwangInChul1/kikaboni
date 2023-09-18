package kikaboni.project.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Criteria {
	
	private int pageNum; // 현재 페이지
	private int amount; // 페이지당 게시물 수
	
	public Criteria() {
		this(1,5); // All 어노테이션 안주면 에러 남
	}
	
	// 몇번부터 몇번까지 게시물 정의할것인지, 마지막 게시물 번호와, 시작 게시물 번호 필요
	public int getMaxRow() {
		return pageNum * amount; // 만약에 1페이지에 10개의 게시물이라면 1*10 이라서 10번호 게시물이 마지막 게시물
	}
	
	public int getMinRow() {
		return (pageNum-1) * amount; // 1페이지면 0이 첫번째 게시물이 되는데, mapper에서 < 초과를 쓸거라 무조건 1이 시작됨
	}
	
	
	public String getListLink() {
		UriComponentsBuilder builder =  UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount);
	
		return builder.toUriString();
	}

	
}
