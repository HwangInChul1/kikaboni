package kikaboni.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;

public interface BoardRepository {

	// 전체 게시물 조회(빵 추천)
	List<BoardVO> commendList(Criteria criteria);
	// 전체 게시물 조회(메뉴 건의)
	List<BoardVO> menuList();
	// 전체 게시물 조회(사장님과 대화)
	List<BoardVO> talkList();
	
	
	// 단일 게시물 조회(빵 추천)
	BoardVO commendGet(Long bno);
	// 단일 게시물 조회(메뉴 건의)
	BoardVO menuGet(Long bno);
	// 단일 게시물 조회(사장님과 대화)
	BoardVO talkGet(Long bno);
	
	
	// 게시물 작성(빵 추천)
	void commendRegister(BoardVO vo);
	// 게시물 작성(메뉴 건의)
	void menuRegister(BoardVO vo);
	// 게시물 작성(사장님과 대화)
	void talkRegister(BoardVO vo);
	
	// 키값 반환
	Integer insertSelectKey(BoardVO vo);
	
	
	// 게시물 수정(빵 추천)
	int commendUpdate(BoardVO vo);
	// 게시물 수정(메뉴 건의)
	int menuUpdate(BoardVO vo);
	// 게시물 수정(사장님과 대화)
	int talkUpdate(BoardVO vo);
	
	
	// 게시물 삭제(빵 추천)
	int commendDelete(Long bno);
	// 게시물 삭제(메뉴 건의)
	int menuDelete(Long bno);
	// 게시물 삭제(사장님과 대화)
	int talkDelete(Long bno);
	
	// 전체 게시물 수
	int totalCount();
	
	// 게시물 당 댓글 수
	void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
	// 마이페이지(내가 쓴 글(글 번호, 제목, 내용))
	List<BoardVO> myTextlist(@Param("memberId") String memberId, @Param("criteria") Criteria criteria);
	
	
}
