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
	
	@Transactional
	@Override
	public int menuinsert(ReplyVO vo) {
		boardRepository.updatemenuReplyCnt(vo.getBno(), 1);
		return replyRepository.menuinsert(vo); // vo를 매개변수로 받아와서 insert 메소드에 대입
	}
	
	@Transactional
	@Override
	public int talkinsert(ReplyVO vo) {
		boardRepository.updatetalkReplyCnt(vo.getBno(), 1);
		return replyRepository.talkinsert(vo); // vo를 매개변수로 받아와서 insert 메소드에 대입
	}
	
	@Transactional
	@Override
	public int eventinsert(ReplyVO vo) {
		boardRepository.updateEventReplyCnt(vo.getBno(), 1);
		return replyRepository.eventinsert(vo); // vo를 매개변수로 받아와서 insert 메소드에 대입
	}
	
	

	@Override
	public ReplyVO read(Long rno) {
		return replyRepository.read(rno); // rno를 받아와서 read메소드에 대입
	}
	@Override
	public ReplyVO menuread(Long rno) {
		return replyRepository.menuread(rno); // rno를 받아와서 read메소드에 대입
	}
	@Override
	public ReplyVO talkread(Long rno) {
		return replyRepository.talkread(rno); // rno를 받아와서 read메소드에 대입
	}
	@Override
	public ReplyVO eventread(Long rno) {
		return replyRepository.eventread(rno); // rno를 받아와서 read메소드에 대입
	}
	
	

	@Override
	public int update(ReplyVO vo) {
		return replyRepository.update(vo);
	}
	@Override
	public int menuupdate(ReplyVO vo) {
		return replyRepository.menuupdate(vo);
	}
	@Override
	public int talkupdate(ReplyVO vo) {
		return replyRepository.talkupdate(vo);
	}
	@Override
	public int eventupdate(ReplyVO vo) {
		return replyRepository.eventupdate(vo);
	}

	@Transactional
	@Override
	public int delete(Long rno) {
		ReplyVO vo = replyRepository.read(rno);
		boardRepository.updateReplyCnt(vo.getBno(), -1);
		return replyRepository.delete(rno);
	}
	@Transactional
	@Override
	public int menudelete(Long rno) {
		ReplyVO vo = replyRepository.menuread(rno);
		boardRepository.updatemenuReplyCnt(vo.getBno(), -1);
		return replyRepository.menudelete(rno);
	}
	@Transactional
	@Override
	public int talkdelete(Long rno) {
		ReplyVO vo = replyRepository.talkread(rno);
		boardRepository.updatetalkReplyCnt(vo.getBno(), -1);
		return replyRepository.talkdelete(rno);
	}
	@Transactional
	@Override
	public int eventdelete(Long rno) {
		ReplyVO vo = replyRepository.eventread(rno);
		boardRepository.updateEventReplyCnt(vo.getBno(), -1);
		return replyRepository.eventdelete(rno);
	}

	
	@Transactional
	@Override
	public ReplyPageDTO getList(Long bno, Criteria criteria) {
		return new ReplyPageDTO(
				replyRepository.getReplyCount(bno), 
				replyRepository.getList(bno, criteria));
	}
	@Transactional
	@Override
	public ReplyPageDTO menuList(Long bno, Criteria criteria) {
		return new ReplyPageDTO(
				replyRepository.menuReplyCount(bno), 
				replyRepository.menuList(bno, criteria));
	}
	@Transactional
	@Override
	public ReplyPageDTO talkList(Long bno, Criteria criteria) {
		return new ReplyPageDTO(
				replyRepository.talkReplyCount(bno), 
				replyRepository.talkList(bno, criteria));
	}
	@Transactional
	@Override
	public ReplyPageDTO eventList(Long bno, Criteria criteria) {
		return new ReplyPageDTO(
				replyRepository.eventReplyCount(bno), 
				replyRepository.eventList(bno, criteria));
	}

}
