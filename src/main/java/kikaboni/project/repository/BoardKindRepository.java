package kikaboni.project.repository;

import kikaboni.project.domain.BoardKindVO;

public interface BoardKindRepository {

	// insert를 통해 값만 집어넣자
	void insert(BoardKindVO vo);
	
	// board_id에 따라 BoardKindVO를 가져오는 메소드
	BoardKindVO getBoardKindById(Long board_id);
}
