package com.cts.signup.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.signup.bean.SignUpStatus;
import com.cts.signup.bean.User;
import com.cts.signup.service.UserService;

@RestController
@RequestMapping("/signup")
public class SignupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/adduser")
	public SignUpStatus saveUser(@RequestBody User user) {
		LOGGER.info("START: Inside saveUser() method of SignupController");
		LOGGER.debug("User object by User: {}", user);
		LOGGER.info("END of saveuser() of SignupController");
		return userService.save(user);
	}

	/*
	 * @GetMapping("/user/{email}") public User findUserByEmail(@PathVariable
	 * String email) {
	 * LOGGER.info("START: Inside findUserByEmail() method of SignupController"
	 * ); LOGGER.debug("Email entered by User: {}" , email); return
	 * userService.findUserByEmail(email);
	 * 
	 * }
	 */

}
