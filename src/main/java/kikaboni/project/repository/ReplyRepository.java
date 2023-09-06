package kikaboni.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyVO;

public interface ReplyRepository {

	// 댓글 작성
	int insert(ReplyVO vo);
	
	// 댓글 조회
	ReplyVO read(Long rno);
	
	// 댓글 수정
	int update(ReplyVO vo);
	
	// 댓글 삭제
	int delete(Long rno);
	
	//게시물 별 댓글 목록
	List<ReplyVO> getList(@Param("bno")Long bno, @Param("criteria")Criteria criteria);
	
	int getReplyCount(Long bno);
	
	
	List<ReplyVO> myReplyList();
	
	
}
