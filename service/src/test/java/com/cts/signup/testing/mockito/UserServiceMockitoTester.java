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

import com.cts.signup.Dao.UserRepository;
import com.cts.signup.bean.User;
import com.cts.signup.service.UserService;

public class UserServiceMockitoTester {

	@Mock
	private UserRepository repository;

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

		when(repository.save(user1)).thenReturn(user1);

		boolean result = service.save(user1);

		assertEquals(true, result);

		verify(repository, times(1)).save(user1);
	}

}