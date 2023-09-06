package kikaboni.project.config;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j;

@Log4j
public class RootConfigTest extends AppTest{

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() {
		try(Connection con = dataSource.getConnection()) {
			assertNotNull(con);
			log.info(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
