package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.BoardAttachVO;

public interface BoardAttachRepository {
	
	// 첨부파일 추가(빵 추천)
	void insert(BoardAttachVO vo);
	void menuinsert(BoardAttachVO vo);
	void talkinsert(BoardAttachVO vo);
	void eventinsert(BoardAttachVO vo);
	
	void delete(String uuid);
	void menudelete(String uuid);
	void talkdelete(String uuid);
	void eventdelete(String uuid);
	
	List<BoardAttachVO> selectByBno(Long bno);
	List<BoardAttachVO> menuselectByBno(Long bno);
	List<BoardAttachVO> talkselectByBno(Long bno);
	List<BoardAttachVO> eventselectByBno(Long bno);
	
	BoardAttachVO selectByUuid(String uuid);
	BoardAttachVO menuselectByUuid(String uuid);
	BoardAttachVO talkselectByUuid(String uuid);
	BoardAttachVO eventselectByUuid(String uuid);
}

