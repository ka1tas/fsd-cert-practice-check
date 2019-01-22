package com.cts.signup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.signup.bean.User;
import com.cts.signup.dao.UserDao;

@Service
public class UserService {

	/*
	 * @Autowired private UserRepository userRepository;
	 */

	@Autowired
	private UserDao userDao;

	@Transactional
	public boolean save(User user) {
		boolean status = false;

		userDao.save(user);
		status = true;

		return status;
	}

	@Transactional
	public User findUserByEmail(String email) {
		User user = userDao.getByEmail(email);
		return user;
	}

}
