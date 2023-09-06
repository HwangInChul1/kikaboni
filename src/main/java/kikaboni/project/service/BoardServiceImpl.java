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
	public List<BoardVO> menuList() {
		return boardRepository.menuList();
	}
	
	@Override // 사장님과 대화
	public List<BoardVO> talkList() {
		return boardRepository.talkList();
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

	// 게시물 작성(게시물 작성 메소드 작성)
	@Transactional
	@Override
	public void commendRegister(BoardVO vo) {
		boardRepository.insertSelectKey(vo);
		
		if(vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
			log.info(vo.getAttachList());
			vo.getAttachList().forEach(attachFile ->{
				attachFile.setBno(vo.getBno());
				log.info(vo.getBno());
				boardAttachRepository.insert(attachFile);
			});
		} 
		
		if(vo.getBno() == null || vo.getBno() > 1) {
			return;
		} else if(vo.getMyTextList() == null || vo.getMyTextList().isEmpty()) {
			BoardKindVO kindvo = new BoardKindVO();
			kindvo.setBoard_id(1L);
			kindvo.setBoard_name("빵 추천합니다.");
			kindvo.setBoard_content("빵 추천 게시판");
			boardKindRepository.insert(kindvo);
		}

	}
	
	@Override
	public void menuRegister(BoardVO vo) {
		boardRepository.menuRegister(vo);
	}

	@Override
	public void talkRegister(BoardVO vo) {
		boardRepository.talkRegister(vo);
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

	@Override
	public boolean menuUpdate(BoardVO vo) {
		return boardRepository.menuUpdate(vo) == 1;
	}

	@Override
	public boolean talkUpdate(BoardVO vo) {
		return boardRepository.talkUpdate(vo) == 1;
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
	public int totalCount() {
		return boardRepository.totalCount();
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {

		return boardAttachRepository.selectByBno(bno);
	}

	@Override
	public BoardAttachVO getAttach(String uuid) {
		return boardAttachRepository.selectByUuid(uuid);
	}

	@Override
	public List<BoardVO> myTextlist(String memberId, Criteria criteria) {
		return boardRepository.myTextlist(memberId, criteria);
	}









}
