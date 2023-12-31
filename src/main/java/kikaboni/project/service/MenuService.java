package kikaboni.project.service;

import java.util.List;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuAttachVO;
import kikaboni.project.domain.MenuVO;

public interface MenuService {
	
	// 메뉴 목록 나열
	List<MenuVO> breadList(Criteria criteria);
	List<MenuVO> cakeList(Criteria criteria);
	List<MenuVO> coffeeList(Criteria criteria);
	
	// 메뉴 조회
	MenuVO breadGet(Long mno);
	
	// 메뉴 등록
	void menuRegister(MenuVO vo);
	
	Integer insertSelectKey(MenuVO vo);
	
	// 메뉴 수정
	int menuUpdate(MenuVO vo);
	
	// 메뉴 삭제
	int menuDelete(Long mno);
	
	// 메뉴 전체 개수
	int getTotalCount();
	
	// 첨부파일 수
	List<MenuAttachVO> menuAttachList(Long mno);
	
	MenuAttachVO menuAttach(String uuid);
	
}
