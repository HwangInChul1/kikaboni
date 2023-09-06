package kikaboni.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;

public interface BoardService {
	
	// 전체 게시물 출력(빵 추천)
	List<BoardVO> commendList(Criteria criteria);
	
	// 전체 게시물 출력(메뉴 건의)
	List<BoardVO> menuList();
	
	// 전체 게시물 출력(사장님과 대화)
	List<BoardVO> talkList();
	
	
	// 단일 게시물 출력(빵 추천)
	BoardVO commendGet(Long bno);
	
	// 단일 게시물 출력(메뉴 건의)
	BoardVO menuGet(Long bno);
	
	// 단일 게시물 출력(사장님과 대화)
	BoardVO talkGet(Long bno);
	
	
	// 게시물 작성(빵 추천)
	void commendRegister(BoardVO vo);
	
	// 게시물 작성(메뉴 건의)
	void menuRegister(BoardVO vo);
	
	// 게시물 작성(사장님과 대화)
	void talkRegister(BoardVO vo);
	
	
	// 게시물 수정(빵 추천)
	boolean commendUpdate(BoardVO vo);
	
	// 게시물 수정(메뉴 건의)
	boolean menuUpdate(BoardVO vo);
	
	// 게시물 수정(사장님과 대화)
	boolean talkUpdate(BoardVO vo);
	
	
	// 게시물 삭제(빵 추천)
	boolean commendDelete(Long bno);
	
	// 게시물 삭제(메뉴 건의)
	boolean menuDelete(Long bno);
	
	// 게시물 삭제(사장님과 대화)
	boolean talkDelete(Long bno);
	
	
	// 전체 게시물 수
	int totalCount();
	
	List<BoardAttachVO> getAttachList(Long bno);
	
	BoardAttachVO getAttach(String uuid);
	
	// 마이페이지(내가 쓴 글(글 번호, 제목, 내용))
	List<BoardVO> myTextlist(String memberId, Criteria criteria);
	
	
	
}
