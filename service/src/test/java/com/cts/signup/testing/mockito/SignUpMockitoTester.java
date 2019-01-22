package com.cts.signup.testing.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.signup.bean.SignUpStatus;
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
	public void testSuccesfullSignup() {

		User testUser = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(service.save(testUser)).thenReturn(true);
		when(service.findUserByEmail(testUser.getEmail())).thenReturn(null);

		SignUpStatus expectedstatus = new SignUpStatus();
		expectedstatus.setEmailExist(false);
		expectedstatus.setSignupStatus(true);

		SignUpStatus status = controller.saveUser(testUser);
		System.out.println(status);
		System.out.println(expectedstatus);
		assertEquals(true, expectedstatus.equals(status));
		verify(service, times(1)).save(testUser);
	}

	@Test
	public void testSignupIfDuplicateEmail() {

		User testUser = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(service.save(testUser)).thenReturn(true);
		when(service.findUserByEmail(testUser.getEmail())).thenReturn(testUser);

		SignUpStatus expectedstatus = new SignUpStatus();
		expectedstatus.setEmailExist(true);
		expectedstatus.setSignupStatus(false);

		SignUpStatus status = controller.saveUser(testUser);
		System.out.println(status);
		assertEquals(true, expectedstatus.equals(status));
		verify(service, times(1)).findUserByEmail(testUser.getEmail());
	}

}