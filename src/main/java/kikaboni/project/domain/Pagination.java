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
	
	// 생성자에 값을 초기화 진행
	public Pagination(Criteria criteria, int totalCount) {
		this.criteria = criteria;
		this.totalCount = totalCount;
		
		// 마지막 페이지 : 현재페이지를 가져와서, 페이지 버튼 수를 나누는데(정수 / 정수 : 정수) 라서 하나를 실수형으로 바꾸고
		// 전체를 정수로 다시 형변환해주면 된다. 그리고 페이지 버튼수를 곱하기
		endPage = (int) Math.ceil(criteria.getPageNum() / (double)displayPageNum) * displayPageNum;
		
		// 시작 페이지
		startPage = (endPage - displayPageNum) + 1;
		
		// 진짜 마지막 페이지
		tempEndPage = (int) Math.ceil(totalCount / (double)criteria.getAmount());
		
		// 이전으로
		prev = startPage != 1; // 시작 페이지가 1이 아니라면 활성화
		
		next = endPage < tempEndPage; // 다음 페이지는 마지막 페이지가 진짜 마지막 페이지보다 작으면 활성화
		
		if(tempEndPage < endPage) endPage = tempEndPage;
	}
	
}
