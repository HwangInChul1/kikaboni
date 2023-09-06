package kikaboni.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyPageDTO;
import kikaboni.project.domain.ReplyVO;

public interface ReplyService {

	// 댓글 작성
	int insert(ReplyVO vo);
	
	// 댓글 조회
	ReplyVO read(Long rno);
	
	// 댓글 수정
	int update(ReplyVO vo);
	
	// 댓글 삭제
	int delete(Long rno);
	
	// 게시판 별 댓글목록 조회
	ReplyPageDTO getList(Long bno, Criteria criteria);
	
	List<ReplyVO> myReplyList();
	
}
