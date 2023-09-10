package kikaboni.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyVO;

public interface ReplyRepository {

	// 댓글 작성(빵 추천)
	int insert(ReplyVO vo);
	// 댓글 작성(메뉴 건의)
	int menuinsert(ReplyVO vo);
	// 댓글 작성(사장님과 대화)
	int talkinsert(ReplyVO vo);
	// 댓글 작성(이벤트,공지사항)
	int eventinsert(ReplyVO vo);
	
	// 댓글 조회
	ReplyVO read(Long rno);
	// 댓글 조회
	ReplyVO menuread(Long rno);
	// 댓글 조회
	ReplyVO talkread(Long rno);
	// 댓글 조회
	ReplyVO eventread(Long rno);
	
	// 댓글 수정
	int update(ReplyVO vo);
	// 댓글 수정
	int menuupdate(ReplyVO vo);
	// 댓글 수정
	int talkupdate(ReplyVO vo);
	// 댓글 수정
	int eventupdate(ReplyVO vo);
	
	// 댓글 삭제
	int delete(Long rno);
	// 댓글 삭제
	int menudelete(Long rno);
	// 댓글 삭제
	int talkdelete(Long rno);
	// 댓글 삭제
	int eventdelete(Long rno);
	
	
	//게시물 별 댓글 목록
	List<ReplyVO> getList(@Param("bno")Long bno, @Param("criteria")Criteria criteria);
	//게시물 별 댓글 목록
	List<ReplyVO> menuList(@Param("bno")Long bno, @Param("criteria")Criteria criteria);
	//게시물 별 댓글 목록
	List<ReplyVO> talkList(@Param("bno")Long bno, @Param("criteria")Criteria criteria);
	//게시물 별 댓글 목록
	List<ReplyVO> eventList(@Param("bno")Long bno, @Param("criteria")Criteria criteria);
	
	// 댓글 수
	int getReplyCount(Long bno);
	// 댓글 수
	int menuReplyCount(Long bno);
	// 댓글 수
	int talkReplyCount(Long bno);
	// 댓글 수
	int eventReplyCount(Long bno);
	

	
}
