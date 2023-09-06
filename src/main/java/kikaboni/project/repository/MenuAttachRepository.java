package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.MenuAttachVO;

public interface MenuAttachRepository {

	// 첨부파일 추가
	void insert(MenuAttachVO vo);
	
	// 첨부파일 삭제
	void delete(String uuid);
	
	// 메뉴마다 첨부파일 목록
	List<MenuAttachVO> selectByMno(Long mno);
	
	MenuAttachVO selectByUuid(String uuid);
}
