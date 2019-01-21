package com.cts.signup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.signup.Dao.UserRepository;
import com.cts.signup.bean.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public boolean saveUser(User user) {
		boolean status = false;
		userRepository.save(user);
		status = true;
		return status;
	}

	@Transactional
	public List<User> showUser() {

		return (List<User>) userRepository.findAll();
	}

	@Transactional
	public void deleteUser(String email) {
		User user = userRepository.findById(email);
		userRepository.delete(user);
	}

	/*
	 * <dependency> <groupId>org.springframework.boot</groupId>
	 * <artifactId>spring-boot-starter</artifactId> </dependency>
	 * 
	 * <dependency> <groupId>org.springframework.boot</groupId>
	 * <artifactId>spring-boot-starter-data-mongodb</artifactId> </dependency>
	 */

}
