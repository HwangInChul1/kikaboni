package kikaboni.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kikaboni.project.domain.Criteria;
import kikaboni.project.domain.MenuAttachVO;
import kikaboni.project.domain.MenuVO;
import kikaboni.project.repository.MenuAttachRepository;
import kikaboni.project.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuAttachRepository menuattachRepository;
	
	
	@Override
	public List<MenuVO> breadList(Criteria criteria) {
		
		List<MenuVO> list = menuRepository.breadList(criteria);
		
		return list;
	}

	@Override
	public List<MenuVO> cakeList(Criteria criteria) {
		
		List<MenuVO> list = menuRepository.cakeList(criteria);
		
		return list;
	}

	@Override
	public List<MenuVO> coffeeList(Criteria criteria) {
		
		List<MenuVO> list = menuRepository.coffeeList(criteria);
		
		return list;
	}
	
	@Transactional
	@Override
	public void menuRegister(MenuVO vo) {
		menuRepository.insertSelectKey(vo);
		
		// 첨부파일이 있을 때
		if(vo.getAttachList()!=null && !vo.getAttachList().isEmpty()) {
			vo.getAttachList().forEach(attachFile -> {
					attachFile.setMno(vo.getMno());
					menuattachRepository.insert(attachFile);
			});
		}
	}
	
	@Override
	public Integer insertSelectKey(MenuVO vo) {	
		
		return menuRepository.insertSelectKey(vo);
	}
	

	@Override
	public int menuUpdate(MenuVO vo) {
		return menuRepository.menuUpdate(vo);
	}

	@Override
	public int menuDelete(Long mno) {
		return menuRepository.menuDelete(mno);
	}

	@Override
	public int getTotalCount() {
		return menuRepository.getTotalCount();
	}

	@Override
	public List<MenuAttachVO> menuAttachList(Long mno) {
		return menuattachRepository.selectByMno(mno);
	}

	@Override
	public MenuAttachVO menuAttach(String uuid) {
		return menuattachRepository.selectByUuid(uuid);
	}

	@Override
	public MenuVO breadGet(Long mno) {
		return menuRepository.breadGet(mno);
	}




}



