package kikaboni.project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pagination {

	// 필드 생성
	// Criteria(현재 페이지, 게시물 수) 이전페이지, 다음페이지,
	// 시작페이지, 끝페이지, 진짜 끝페이지, 한 페이지당 보이는 페이지버튼 수, 전체 게시물 수 
	private Criteria criteria;
	private int startPage; // 시작 페이지
	private int endPage; // 마지막 페이지
	private int tempEndPage; // 진짜 마지막 페이지
	private int displayPageNum = 10; // 보이는 페이지 버튼 수
	private int totalCount; // 전체 게시물 수
	private boolean next; // 다음페이지
	private boolean prev; // 이전페이지
	

	public Pagination(Criteria criteria, int totalCount) {
		this.criteria = criteria;
		this.totalCount = totalCount;
		
		endPage = (int) Math.ceil(criteria.getPageNum() / (double)displayPageNum) * displayPageNum;
		
		startPage = (endPage - displayPageNum) + 1;
		
		tempEndPage = (int) Math.ceil(totalCount / (double)criteria.getAmount());

		prev = startPage != 1; 
		
		next = endPage < tempEndPage; 
		
		if(tempEndPage < endPage) endPage = tempEndPage;
	}
	
}
