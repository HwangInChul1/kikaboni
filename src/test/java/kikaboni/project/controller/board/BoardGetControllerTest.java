package kikaboni.project.controller.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.BoardAttachVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardGetControllerTest extends AppTest{

	@Autowired
	WebApplicationContext ctx;
	
	MockMvc mvc;
	
	ObjectMapper mapper;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		this.mapper = Jackson2ObjectMapperBuilder.json().build();
	}
	
	@Test
	public void test() throws Exception {
		 Long bno = 1L; // 적절한 게시물 번호를 설정해주세요
		    MvcResult result = mvc.perform(get("/board/getAttachList")
		                            .param("bno", bno.toString()))
		                            .andReturn();
		    log.info(result.getResponse().getContentAsString());
	}

}
