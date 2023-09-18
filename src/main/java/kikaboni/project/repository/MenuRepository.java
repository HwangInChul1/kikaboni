package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuVO;

public interface MenuRepository {

	// 메뉴 목록 나열
	List<MenuVO> breadList(Criteria criteria);
	List<MenuVO> cakeList(Criteria criteria);
	List<MenuVO> coffeeList(Criteria criteria);
	
	// 메뉴 조회(상품id 넣으면 상품에 대해 조회)
	MenuVO breadGet(Long mno);
	
	// 메뉴 등록
	int menuRegister(MenuVO vo);
	
	Integer insertSelectKey(MenuVO vo);
	
	// 메뉴 수정
	int menuUpdate(MenuVO vo);
	
	// 메뉴 삭제(삭제도 조회와 마찬가지 상품id를 매개변수로 넣음)
	int menuDelete(Long mno);
	
	// 메뉴 전체 개수
	int getTotalCount();
	

}
