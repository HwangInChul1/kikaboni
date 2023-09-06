package kikaboni.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kikaboni.project.config.AppTest;
import kikaboni.project.domain.ReplyVO;

public class ReplyListControllerTest extends AppTest{

	@Autowired
	WebApplicationContext act;
	
	MockMvc mvc;
	
	ObjectMapper mapper;
	
	@Before  // 생성자에다 초기화 하는 거 아니고, 메소드 생성해서 초기화
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(act).build();
		this.mapper = Jackson2ObjectMapperBuilder.json().build();
	}
	

	@Test
	@Ignore
	public void test() throws Exception {
		ReplyVO vo = ReplyVO.builder()
				.bno(1L)
				.reply("내용")
				.replyer("작성자!")
				.build();
		String content = mapper.writeValueAsString(vo);
		mvc.perform(post("/replies/new")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
		).andReturn();
	}
	
	@Test
	@Ignore
	public void test2() throws Exception {
		mvc.perform(get("/replies/1"));
	}
	
	@Test
//	@Ignore
	public void test3() throws Exception {
		mvc.perform(delete("/replies/9"));
	}
	
	@Test
	@Ignore
	public void testList() throws Exception {
		mvc.perform(get("/replies/pages/1"));
	}
	
	

}
