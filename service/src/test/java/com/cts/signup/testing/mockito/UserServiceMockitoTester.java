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

import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.service.UserService;

public class UserServiceMockitoTester {

	@Mock
	private UserDao dao;

	@InjectMocks
	private UserService service;

	@Before
	public void setUp() throws Exception {
		// Create a user object which is to be tested
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUserController() {

		User user1 = new User(1, "Saikat", "saikat@gmail.com", "12345");
		User user2 = null;

		when(dao.save(user1)).thenReturn(true);

		boolean result = service.save(user1);

		assertEquals(true, result);

		verify(dao, times(1)).save(user1);
	}

}