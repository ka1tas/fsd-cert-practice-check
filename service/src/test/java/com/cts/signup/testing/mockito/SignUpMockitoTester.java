package com.cts.signup.testing.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.signup.bean.User;
import com.cts.signup.rest.SignupController;
import com.cts.signup.service.UserService;

public class SignUpMockitoTester {

	@Mock
	private UserService service;

	@InjectMocks
	private SignupController controller;

	@Before
	public void setUp() throws Exception {
		// Create a user object which is to be tested
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUserController() {

		User user1 = new User(1, "Saikat", "saikat@gmail.com", "12345");

		when(service.save(user1)).thenReturn(true);

		boolean result = controller.saveUser(user1);

		assertEquals(true, result);

		verify(service, times(1)).save(user1);
	}

}