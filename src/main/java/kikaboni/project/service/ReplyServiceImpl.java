package kikaboni.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.ReplyPageDTO;
import kikaboni.project.domain.ReplyVO;
import kikaboni.project.repository.BoardRepository;
import kikaboni.project.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	@Override
	public int insert(ReplyVO vo) {
		boardRepository.updateReplyCnt(vo.getBno(), 1);
		return replyRepository.insert(vo); // vo를 매개변수로 받아와서 insert 메소드에 대입
	}

	@Override
	public ReplyVO read(Long rno) {
		return replyRepository.read(rno); // rno를 받아와서 read메소드에 대입
	}

	@Override
	public int update(ReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Transactional
	@Override
	public int delete(Long rno) {
		ReplyVO vo = replyRepository.read(rno);
		boardRepository.updateReplyCnt(vo.getBno(), -1);
		return replyRepository.delete(rno);
	}

	@Override
	public ReplyPageDTO getList(Long bno, Criteria criteria) {
		return new ReplyPageDTO(
				replyRepository.getReplyCount(bno), 
				replyRepository.getList(bno, criteria));
	}

	@Override
	public List<ReplyVO> myReplyList() {
		return replyRepository.myReplyList();
	}

}
