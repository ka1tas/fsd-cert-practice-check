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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.dao.UserRepository;
import com.cts.signup.service.UserService;

public class UserServiceMockitoTester {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceMockitoTester.class);
	
	@Mock
	private UserDao dao;

	@Mock
	private UserRepository repo;

	@InjectMocks
	private UserService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSuccessfulyAddingUserusingDao() {
		LOGGER.info("START of testSuccessfulyAddingUserusingDao() testing in UserServiceMockitoTester");
		User user1 = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(dao.save(user1)).thenReturn(true);
		boolean result = service.save(user1);
		assertEquals(true, result);
		verify(dao, times(1)).save(user1);
		LOGGER.info("END of testSuccessfulyAddingUserusingDao() testing in UserServiceMockitoTester");
	}

	@Test
	public void testSuccessfulyAddingUserusinRepository() {
		LOGGER.info("START of testSuccessfulyAddingUserusinRepository() testing in UserServiceMockitoTester");
		User user1 = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(repo.save(user1)).thenReturn(user1);
		boolean result = service.save(user1);
		assertEquals(true, result);
		verify(repo, times(1)).save(user1);
		LOGGER.info("END of testSuccessfulyAddingUserusinRepository() testing in UserServiceMockitoTester");
	}

}