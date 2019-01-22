package com.cts.signup.testing.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

//	@Test
//	public void addNewUser() throws Exception {
//
//		String USER_DATA = "{\"email\" : \"r1d2s12@nggn.bom\"" + "," + "\"name\" : \"kiran\"" + ","
//				+ "\"password\" : \"kira12n\"}";
//			mockMvc.perform(
//					post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
//					.andExpect(status().isOk()).andExpect(jsonPath("$.signedUp").value("true"));
//		
//	}
	
//	@Test
//	public void checkEmailExists() throws Exception {
//
//		String USER_DATA = "{\"email\" : \"Ravariyal@gmail.com\"" + "," + "\"name\" : \"kiran\"" + ","
//				+ "\"password\" : \"kira12n\"}";
//			mockMvc.perform(
//					post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
//					.andExpect(status().isOk()).andExpect(jsonPath("$.emailExists").value("true")).andExpect(jsonPath("$.signedUp").value("false"));
//		
//	}
	
	
	@Test
	public void incorrectPasswordFormat() throws Exception {
	
		exceptionRule.expect(NestedServletException.class);
		
		String USER_DATA = "{\"email\" : \"Rdcdsa3iayal@gmail.com\"" + "," + "\"name\" : \"kiran\"" + ","
				+ "\"password\" : \"1\"}";
			mockMvc.perform(
					post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
					.andExpect(status().is5xxServerError());
		
	}
	
//	@Test
//	public void nullName() throws Exception {
//	
//		exceptionRule.expect(NestedServletException.class);
//		exceptionRule.expectMessage("Name cannot be empty");
//		String USER_DATA = "{\"email\" : \"Ravarlteggrteldi111yal@gmail.com\"" + "," + "\"name\" : \"\" " + ","
//				+ "\"password\" : \"1234561\"}";
//			mockMvc.perform(
//					post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
//					.andExpect(status().isOk());
//		
//	}
	
}

