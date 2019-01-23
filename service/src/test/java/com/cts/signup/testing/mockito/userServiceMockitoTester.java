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

import com.cts.signup.bean.SignUpStatus;
import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.service.UserService;

public class userServiceMockitoTester {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(userServiceMockitoTester.class);
	
	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSuccesfullSignup() {
		LOGGER.info("START of testSuccesfullSignup() testing in SignUpMockitoTester");
		User testUser = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(userDao.save(testUser)).thenReturn(true);
		when(userDao.findByEmail(testUser.getEmail())).thenReturn(null);

		SignUpStatus expectedstatus = new SignUpStatus();
		expectedstatus.setEmailExist(false);
		expectedstatus.setSignupStatus(true);

		SignUpStatus status = service.save(testUser);
		LOGGER.debug("status: "+ status);
		assertEquals(true, expectedstatus.equals(status));
		verify(userDao, times(1)).save(testUser);
		LOGGER.info("END of testSuccesfullSignup() testing in SignUpMockitoTester");
	}

	@Test
	public void testSignupIfDuplicateEmail() {
		LOGGER.info("START of testSignupIfDuplicateEmail() testing in SignUpMockitoTester");
		User testUser = new User(1, "Saikat", "saikat@gmail.com", "12345");
		when(userDao.save(testUser)).thenReturn(true);
		when(userDao.findByEmail(testUser.getEmail())).thenReturn(testUser);

		SignUpStatus expectedstatus = new SignUpStatus();
		expectedstatus.setEmailExist(true);
		expectedstatus.setSignupStatus(false);

		SignUpStatus status = service.save(testUser);
		LOGGER.debug("status: "+ status);
		assertEquals(true, expectedstatus.equals(status));
		verify(userDao, times(1)).findByEmail(testUser.getEmail());
		LOGGER.info("END of testSignupIfDuplicateEmail() testing in SignUpMockitoTester");
	}

}