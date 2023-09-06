package kikaboni.project.repository;

import java.util.List;

import kikaboni.project.domain.BoardAttachVO;

public interface BoardAttachRepository {
	
	void insert(BoardAttachVO vo);
	
	void delete(String uuid);
	
	List<BoardAttachVO> selectByBno(Long bno);
	
	BoardAttachVO selectByUuid(String uuid);
}
