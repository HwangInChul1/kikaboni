package kikaboni.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyPageDTO;
import kikaboni.project.domain.ReplyVO;

public interface ReplyService {

	// 댓글 작성
	int insert(ReplyVO vo);
	// 댓글 작성
	int menuinsert(ReplyVO vo);
	// 댓글 작성
	int talkinsert(ReplyVO vo);
	// 댓글 작성
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
	
	
	// 게시판 별 댓글목록 조회
	ReplyPageDTO getList(Long bno, Criteria criteria);
	// 게시판 별 댓글목록 조회
	ReplyPageDTO menuList(Long bno, Criteria criteria);
	// 게시판 별 댓글목록 조회
	ReplyPageDTO talkList(Long bno, Criteria criteria);
	// 게시판 별 댓글목록 조회
	ReplyPageDTO eventList(Long bno, Criteria criteria);
	

}
