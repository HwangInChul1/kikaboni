package kikaboni.project.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kikaboni.project.config.AppTest;
import lombok.extern.log4j.Log4j;

@Log4j
public class TestRepositoryTest extends AppTest{

	@Autowired
	TestRepository repository;
	
	@Test
	public void test() {
		String date = repository.getDate();
		log.info(date);
	}

}
