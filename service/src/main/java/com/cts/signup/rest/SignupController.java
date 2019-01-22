package com.cts.signup.rest;

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

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/adduser")
	public SignUpStatus saveUser(@RequestBody User user) {
		SignUpStatus status = new SignUpStatus();
		status.setSignupStatus(false);
		status.setEmailExist(true);

		User existingUser = userService.findUserByEmail(user.getEmail());

		if (existingUser == null) {
			status.setSignupStatus(userService.save(user));
			status.setEmailExist(false);

		} 

		return status;
	}

	/*
	 * @GetMapping("/user/{email}") public User UserbyEmail(@PathVariable String
	 * email) { return userService.userbyemail(email);
	 * 
	 * }
	 */

}
