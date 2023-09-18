package kikaboni.project.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.BoardKindVO;
import kikaboni.project.domain.BoardVO;
import kikaboni.project.domain.Criteria;
import kikaboni.project.repository.BoardAttachRepository;
import kikaboni.project.repository.BoardKindRepository;
import kikaboni.project.repository.BoardRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service // Service로 등록하는 이유 ServletConfig의 ComponentScan이 빈으로 등록하기 위해
public class BoardServiceImpl implements BoardService {

	// boardRepository를 오토와일드로 빈으로 받아옴
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardAttachRepository boardAttachRepository;
	
	@Autowired
	BoardKindRepository boardKindRepository;
	
	// 전체 게시물 출력(repository의 메소드를 바로 반환)
	@Override
	public List<BoardVO> commendList(Criteria criteria) {  // 빵 추천
		return boardRepository.commendList(criteria);
	}
	
	@Override // 메뉴 건의
	public List<BoardVO> menuList(Criteria criteria) {
		return boardRepository.menuList(criteria);
	}
	
	@Override // 사장님과 대화
	public List<BoardVO> talkList(Criteria criteria) {
		return boardRepository.talkList(criteria);
	}
	
	@Override // 사장님과 대화
	public List<BoardVO> eventList(Criteria criteria) {
		return boardRepository.eventList(criteria);
	}
	

	// 단일 게시물 출력(repository의 메소드를 바로 반환)
	@Override
	public BoardVO commendGet(Long bno) {
		return boardRepository.commendGet(bno);
	}
	
	@Override
	public BoardVO menuGet(Long bno) {
		return boardRepository.menuGet(bno);
	}
	
	@Override
	public BoardVO talkGet(Long bno) {
		return boardRepository.talkGet(bno);
	}
	
	@Override
	public BoardVO eventGet(Long bno) {
		return boardRepository.eventGet(bno);
	}
	
	

	// 게시물 작성(게시물 작성 메소드 작성)
	@Transactional
	@Override
	public void commendRegister(BoardVO vo) {
		boardRepository.insertSelectKey(vo);
		
		if(vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
			vo.getAttachList().forEach(attachFile ->{
				attachFile.setBno(vo.getBno());
				boardAttachRepository.insert(attachFile);
			});
		} 
		
		if(vo.getBno() == null || vo.getBno() > 1) {
			return;
		} else if(vo.getMyTextList() == null || vo.getMyTextList().isEmpty() && vo.getBoard_id() == 1) {
			BoardKindVO kindvo = new BoardKindVO();
			kindvo.setBoard_id(1L);
			kindvo.setBoard_name("추천합니다.");
			kindvo.setBoard_content("빵을 추천하는 게시판");
			boardKindRepository.insert(kindvo);
			log.info(kindvo);
		}

	}
	
	@Transactional
	@Override
	public void menuRegister(BoardVO vo) {
		boardRepository.insertMenuSelectKey(vo);
		
		if(vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
			log.info(vo.getAttachList());
			vo.getAttachList().forEach(attachFile ->{
				attachFile.setBno(vo.getBno());
				log.info(vo.getBno());
				boardAttachRepository.menuinsert(attachFile);
			});
		} 
		
		if(vo.getBno() == null || vo.getBno() > 1) {
			return;
		} else if(vo.getMyTextList() == null || vo.getMyTextList().isEmpty() && vo.getBoard_id() == 2) {
			BoardKindVO kindvo = new BoardKindVO();
			kindvo.setBoard_id(2L);
			kindvo.setBoard_name("메뉴 건의합니다.");
			kindvo.setBoard_content("빵 메뉴를 건의하는 게시판");
			boardKindRepository.insert(kindvo);
			log.info(kindvo);
		}

	}
	
	@Transactional
	@Override
	public void talkRegister(BoardVO vo) {
		boardRepository.insertTalkSelectKey(vo);
		
		if(vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
			log.info(vo.getAttachList());
			vo.getAttachList().forEach(attachFile ->{
				attachFile.setBno(vo.getBno());
				log.info(vo.getBno());
				boardAttachRepository.talkinsert(attachFile);
			});
		} 
		
		if(vo.getBno() == null || vo.getBno() > 1) {
			return;
		} else if(vo.getMyTextList() == null || vo.getMyTextList().isEmpty() && vo.getBoard_id()==3) {
			BoardKindVO kindvo = new BoardKindVO();
			kindvo.setBoard_id(3L);
			kindvo.setBoard_name("사장님께 한 마디~");
			kindvo.setBoard_content("사장님과 대화하는 게시판");
			boardKindRepository.insert(kindvo);
			log.info(kindvo);
		}

	}
	
	@Transactional
	@Override
	public void eventRegister(BoardVO vo) {
		boardRepository.insertEventSelectKey(vo);
		
		if(vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
			log.info(vo.getAttachList());
			vo.getAttachList().forEach(attachFile ->{
				attachFile.setBno(vo.getBno());
				log.info(vo.getBno());
				boardAttachRepository.eventinsert(attachFile);
			});
		} 
		
		if(vo.getBno() == null || vo.getBno() > 1) {
			return;
		} else if(vo.getMyTextList() == null || vo.getMyTextList().isEmpty() && vo.getBoard_id()==4) {
				BoardKindVO kindvo = new BoardKindVO();
				kindvo.setBoard_id(4L);
				kindvo.setBoard_name("이벤트 및 공지사항");
				kindvo.setBoard_content("이벤트 올리거나, 공지사항 있을 때 올리는 게시판");
				boardKindRepository.insert(kindvo);
				log.info(kindvo);
		}
	}
	
	
	

	// 게시물 수정(update라는 repository의 메소드가 int타입이라 성공적이면 1을 반환하기 때문에 1이면 실행한 결과를 반환)
	@Override
	public boolean commendUpdate(BoardVO vo) {
		
		List<BoardAttachVO> attachList = vo.getAttachList();
		
		
		if(attachList != null) {
			// 기존 파일 삭제
			List<BoardAttachVO> delList = attachList.stream().filter(attach -> attach.getBno() != null)
					.collect(Collectors.toList());
			deleteFiles(delList); // 파일삭제
			delList.forEach(b -> {
				boardAttachRepository.delete(b.getUuid());
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getBno() == null).forEach(b -> {
				b.setBno(vo.getBno());
				boardAttachRepository.insert(b);
			});
		}

		return boardRepository.commendUpdate(vo) == 1;
	}
	
	
	@Override
	public boolean menuUpdate(BoardVO vo) {
		
		List<BoardAttachVO> attachList = vo.getAttachList();
		
		
		if(attachList != null) {
			// 기존 파일 삭제
			List<BoardAttachVO> delList = attachList.stream().filter(attach -> attach.getBno() != null)
					.collect(Collectors.toList());
			deleteFiles(delList); // 파일삭제
			delList.forEach(b -> {
				boardAttachRepository.menudelete(b.getUuid());
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getBno() == null).forEach(b -> {
				b.setBno(vo.getBno());
				boardAttachRepository.menuinsert(b);
			});
		}

		return boardRepository.menuUpdate(vo) == 1;
	}
	


	@Override
	public boolean talkUpdate(BoardVO vo) {
		List<BoardAttachVO> attachList = vo.getAttachList();
		
		
		if(attachList != null) {
			// 기존 파일 삭제
			List<BoardAttachVO> delList = attachList.stream().filter(attach -> attach.getBno() != null)
					.collect(Collectors.toList());
			deleteFiles(delList); // 파일삭제
			delList.forEach(b -> {
				boardAttachRepository.talkdelete(b.getUuid());
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getBno() == null).forEach(b -> {
				b.setBno(vo.getBno());
				boardAttachRepository.talkinsert(b);
			});
		}

		return boardRepository.talkUpdate(vo) == 1;
	}

	@Override
	public boolean eventUpdate(BoardVO vo) {
		List<BoardAttachVO> attachList = vo.getAttachList();
		
		
		if(attachList != null) {
			// 기존 파일 삭제
			List<BoardAttachVO> delList = attachList.stream().filter(attach -> attach.getBno() != null)
					.collect(Collectors.toList());
			deleteFiles(delList); // 파일삭제
			delList.forEach(b -> {
				boardAttachRepository.eventdelete(b.getUuid());
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getBno() == null).forEach(b -> {
				b.setBno(vo.getBno());
				boardAttachRepository.eventinsert(b);
			});
		}

		return boardRepository.eventUpdate(vo) == 1;
	}
	
	
	
	private void deleteFiles(List<BoardAttachVO> delList) {
		delList.forEach(vo -> {
			File file = new File("c:/storage/" + vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			file.delete();
			
			// 섬네일 삭제
			if(vo.isFileType()) {
				file = new File("c:/storage/" + vo.getUploadPath(), "/s_" + vo.getUuid() + "_" + vo.getFileName());
				file.delete();
			}
		});
}

	
	
	
	// 게시물 삭제(delete라는 repository의 메소드가 int타입이라 성공적이면 1을 반환하기 때문에 1이면 실행한 결과를 반환)
	@Override
	public boolean commendDelete(Long bno) {
		return boardRepository.commendDelete(bno) == 1 ;
	}

	@Override
	public boolean menuDelete(Long bno) {
		return boardRepository.menuDelete(bno) == 1 ;
	}

	@Override
	public boolean talkDelete(Long bno) {
		return boardRepository.talkDelete(bno) == 1 ;
	}
	
	@Override
	public boolean eventDelete(Long bno) {
		return boardRepository.eventDelete(bno) == 1 ;
	}
	

	@Override
	public int totalCount() {
		return boardRepository.totalCount();
	}

	@Override
	public int menutotalCount() {
		return boardRepository.menutotalCount();
	}
	
	@Override
	public int talktotalCount() {
		return boardRepository.talktotalCount();
	}
	
	@Override
	public int eventtotalCount() {
		return boardRepository.eventtotalCount();
	}
	
	
	
	
	

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {

		return boardAttachRepository.selectByBno(bno);
	}
	@Override
	public List<BoardAttachVO> menuAttachList(Long bno) {

		return boardAttachRepository.menuselectByBno(bno);
	}
	@Override
	public List<BoardAttachVO> talkAttachList(Long bno) {

		return boardAttachRepository.talkselectByBno(bno);
	}
	@Override
	public List<BoardAttachVO> eventAttachList(Long bno) {

		return boardAttachRepository.eventselectByBno(bno);
	}

	@Override
	public BoardAttachVO getAttach(String uuid) {
		return boardAttachRepository.selectByUuid(uuid);
	}
	@Override
	public BoardAttachVO menuAttach(String uuid) {
		return boardAttachRepository.menuselectByUuid(uuid);
	}
	@Override
	public BoardAttachVO talkAttach(String uuid) {
		return boardAttachRepository.talkselectByUuid(uuid);
	}
	@Override
	public BoardAttachVO eventAttach(String uuid) {
		return boardAttachRepository.eventselectByUuid(uuid);
	}

	

	@Override
	public List<BoardVO> myTextlist(String memberId, Criteria criteria) {
		return boardRepository.myTextlist(memberId, criteria);
	}
	
	@Override
	public List<BoardVO> myMenuTextlist(String memberId, Criteria criteria) {
		return boardRepository.myMenuTextlist(memberId, criteria);
	}
	
	@Override
	public List<BoardVO> mytalkTextlist(String memberId, Criteria criteria) {
		return boardRepository.mytalkTextlist(memberId, criteria);
	}
	
	@Override
	public List<BoardVO> myeventTextlist(String memberId, Criteria criteria) {
		return boardRepository.myeventTextlist(memberId, criteria);
	}

	@Override
	public int MytotalCount(String memberId) {
		return boardRepository.MytotalCount(memberId);
	}

	@Override
	public int MymenutotalCount(String memberId) {
		return boardRepository.MymenutotalCount(memberId);
	}

	@Override
	public int MytalktotalCount(String memberId) {
		return boardRepository.MytalktotalCount(memberId);
	}

	@Override
	public int MyeventtotalCount(String memberId) {
		return boardRepository.MyeventtotalCount(memberId);
	}



}
