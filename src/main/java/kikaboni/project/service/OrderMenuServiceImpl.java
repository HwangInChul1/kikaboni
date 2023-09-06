package kikaboni.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kikaboni.project.domain.OrderMenuVO;
import kikaboni.project.repository.OrderMenuRepository;

@Service
public class OrderMenuServiceImpl implements OrderMenuService {

	@Autowired
	OrderMenuRepository menuRepository;
	
	@Override
	public OrderMenuVO menuList(Long ono) {
		return menuRepository.menuList(ono);
	}

}
