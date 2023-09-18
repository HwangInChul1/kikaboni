package kikaboni.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardKindVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;

public interface BoardService {
	
	// 전체 게시물 출력(빵 추천)
	List<BoardVO> commendList(Criteria criteria);
	
	// 전체 게시물 출력(메뉴 건의)
	List<BoardVO> menuList(Criteria criteria);
	
	// 전체 게시물 출력(사장님과 대화)
	List<BoardVO> talkList(Criteria criteria);
	
	// 전체 게시물 출력(사장님과 대화)
	List<BoardVO> eventList(Criteria criteria);
	
	
	// 단일 게시물 출력(빵 추천)
	BoardVO commendGet(Long bno);
	
	// 단일 게시물 출력(메뉴 건의)
	BoardVO menuGet(Long bno);
	
	// 단일 게시물 출력(사장님과 대화)
	BoardVO talkGet(Long bno);
	
	// 단일 게시물 출력(사장님과 대화)
	BoardVO eventGet(Long bno);
	
	
	// 게시물 작성(빵 추천)
	void commendRegister(BoardVO vo);
	
	// 게시물 작성(메뉴 건의)
	void menuRegister(BoardVO vo);
	
	// 게시물 작성(사장님과 대화)
	void talkRegister(BoardVO vo);
	
	// 게시물 작성(사장님과 대화)
	void eventRegister(BoardVO vo);
	
	
	// 게시물 수정(빵 추천)
	boolean commendUpdate(BoardVO vo);
	
	// 게시물 수정(메뉴 건의)
	boolean menuUpdate(BoardVO vo);
	
	// 게시물 수정(사장님과 대화)
	boolean talkUpdate(BoardVO vo);
	
	// 게시물 수정(사장님과 대화)
	boolean eventUpdate(BoardVO vo);
	
	
	// 게시물 삭제(빵 추천)
	boolean commendDelete(Long bno);
	
	// 게시물 삭제(메뉴 건의)
	boolean menuDelete(Long bno);
	
	// 게시물 삭제(사장님과 대화)
	boolean talkDelete(Long bno);
	
	// 게시물 삭제(사장님과 대화)
	boolean eventDelete(Long bno);
	
	
	// 전체 게시물 수
	int totalCount();
	
	int menutotalCount();
	
	int talktotalCount();
	
	int eventtotalCount();
	
	
	// 회원아이디 별 게시판의 글 전체 개수
	// 전체 게시물 수(빵 추천)
	int MytotalCount(String memberId);
	// 전체 게시물 수(메뉴 건의)
	int MymenutotalCount(String memberId);
	// 전체 게시물 수(사장님과 대화)
	int MytalktotalCount(String memberId);
	// 전체 게시물 수(이벤트, 공지사항)
	int MyeventtotalCount(String memberId);
	
	
	List<BoardAttachVO> getAttachList(Long bno);
	List<BoardAttachVO> menuAttachList(Long bno);
	List<BoardAttachVO> talkAttachList(Long bno);
	List<BoardAttachVO> eventAttachList(Long bno);
	

	BoardAttachVO getAttach(String uuid);
	BoardAttachVO menuAttach(String uuid);
	BoardAttachVO talkAttach(String uuid);
	BoardAttachVO eventAttach(String uuid);
	
	// 마이페이지(내가 쓴 글(글 번호, 제목, 내용))
	List<BoardVO> myTextlist(String memberId, Criteria criteria);
	
	List<BoardVO> myMenuTextlist(String memberId, Criteria criteria);
	
	List<BoardVO> mytalkTextlist(String memberId, Criteria criteria);
	
	List<BoardVO> myeventTextlist(String memberId, Criteria criteria);
	
	
	
	
}
