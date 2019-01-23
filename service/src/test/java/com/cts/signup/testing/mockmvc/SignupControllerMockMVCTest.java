package com.cts.signup.testing.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.cts.signup.testing.mockito.SignUpMockitoTester;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignupControllerMockMVCTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignupControllerMockMVCTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testSuccessfulyaddingUser() throws Exception {
		LOGGER.info("START");
		String USER_DATA = "{\"email\" : \"saikatsin@sinha.mah\"" + "," + "\"name\" : \"Saikat\"" + ","
				+ "\"password\" : \"saikat12\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.signupStatus").value("true"));

		/*
		 * mockMvc.perform(post("/signup/adduser").content(USER_DATA).
		 * contentType("application/json;charset=UTF-8"))
		 * .andExpect(status().is2xxSuccessful());
		 */
		LOGGER.info("END");
	}

	@Test
	public void testEmailAlreadyExists() throws Exception {
		LOGGER.info("START");
		String USER_DATA = "{\"email\" : \"ram@gmail.com\"" + "," + "\"name\" : \"saikat\"" + ","
				+ "\"password\" : \"saikats\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.emailExist").value("true"))
				.andExpect(jsonPath("$.signupStatus").value("false"));

		LOGGER.info("END");
	}

	@Test
	public void testUrlisWIncorrect() throws Exception {
		LOGGER.info("START");
		String USER_DATA = "{\"email\" : \"saikat1@gmail.com\"" + "," + "\"name\" : \"saikat\"" + ","
				+ "\"password\" : \"1234561\"}";
		mockMvc.perform(post("/signups/addusesr").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError());
		LOGGER.info("END");
	}

	@Test
	public void testNameisNull() throws Exception {
		LOGGER.info("START");
		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Name cannot be empty");
		String USER_DATA = "{\"email\" : \"saikat@gmail.com\"" + "," 
				+ "\"password\" : \"1234561\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
		LOGGER.info("END");
	}

	@Test
	public void testEmailisNull() throws Exception {
		LOGGER.info("START");
		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Email cannot be empty");
		String USER_DATA = "{\"name\" : \"saikat\"" + "," + "\"password\" : \"1234561\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testPasswordisNull() throws Exception {
		LOGGER.info("START");
		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Password cannot be empty");
		String USER_DATA = "{\"email\" : \"saikat@gmail.com\"" + "," + "\"name\" : \"saikat\""
				+ "}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
		LOGGER.info("START");
	}

	@Test
	public void testIncorrectNameSize() throws Exception {
		LOGGER.info("START");
		 exceptionRule.expect(NestedServletException.class);
		 exceptionRule.expectMessage("Name must be of 1 to 20 characters");
		String USER_DATA = "{\"email\" : \"saikatsin@sinha.mah\"" + ","
				+ "\"name\" : \"atsinghamahapatrahellohowareyou\"" + "," + "\"password\" : \"saikat12\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
		LOGGER.info("END");
	}

	@Test
	public void testEmailSizeInvalid() throws Exception {
		LOGGER.info("START");
		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Email must be of 3 to 25 characters");
		String USER_DATA = "{\"email\" : \"saiksinha@singhamahapatra.heyhowareyoudoing\"" + ","
				+ "\"name\" : \"Saikat\"" + "," + "\"password\" : \"saikat12\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
		LOGGER.info("END");
	}

	@Test
	public void testIncorrectPasswordSize() throws Exception {
		LOGGER.info("START");

		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Passsword must be of 3 to 25 characters");
		String USER_DATA = "{\"email\" : \"saikat@gmail.com\"" + "," + "\"name\" : \"saikat\"" + ","
				+ "\"password\" : \"1\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());

		LOGGER.info("END");
	}

	@Test
	public void testIncorrectEmailPattern() throws Exception {
		LOGGER.info("START");
		exceptionRule.expect(NestedServletException.class);
		exceptionRule.expectMessage("Email address is invalid");
		String USER_DATA = "{\"email\" : \"saiksinha.mah\"" + "," + "\"name\" : \"Saikat\"" + ","
				+ "\"password\" : \"saikat12\"}";
		mockMvc.perform(post("/signup/adduser").content(USER_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is5xxServerError());
		LOGGER.info("END");
	}

}
