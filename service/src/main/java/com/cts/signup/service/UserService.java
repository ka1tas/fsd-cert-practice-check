package com.cts.signup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;
import com.cts.signup.dao.UserRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDao userDao;

	@Transactional
	public boolean save(User user) {
		LOGGER.info("START : Inside save() method of UserService");
		LOGGER.debug("User Object :  {}", user);
		boolean status = false;
		userRepository.save(user);
		status = true;
		LOGGER.info("END of save() method of UserService");
		return status;
	}

	@Transactional
	public User findUserByEmail(String email) {
		LOGGER.info("START : Inside findUserByEmail() method of UserService");
		LOGGER.debug("Entered email :  {}", email);
		User user = userRepository.findByEmail(email);
		LOGGER.info("END of findUserByEmail() method of UserService");
		return user;
	}

}
