package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuVO;

public interface MenuRepository {

	// 메뉴 목록 나열
	List<MenuVO> breadList(Criteria criteria);
	List<MenuVO> cakeList();
	List<MenuVO> coffeeList();
	
	// 메뉴 조회
	MenuVO breadGet(Long mno);
	
	// 메뉴 등록
	int menuRegister(MenuVO vo);
	
	Integer insertSelectKey(MenuVO vo);
	
	// 메뉴 수정
	int menuUpdate(MenuVO vo);
	
	// 메뉴 삭제
	int menuDelete(String name);
	
	// 메뉴 전체 개수
	int getTotalCount();
}
