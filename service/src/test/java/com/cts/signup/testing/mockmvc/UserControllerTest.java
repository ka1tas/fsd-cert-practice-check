package com.cts.signup.testing.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUserController() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"akash@1234gmfdffdfailASDF.com\"" + "," + "\"name\" : \"akash\"" + ","
				+ "\"password\" : \"akash10234\"}";

		mockMvc.perform(post("/signup/adduser").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}
}
