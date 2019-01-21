package com.cts.signup.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Boolean saveUser(@RequestBody User user) {
		boolean status = false;
		status = userService.save(user);

		return status;
	}

	/*@PostMapping("/deleteuser")
	public Boolean deleteUsers(@RequestBody String id) {
		userService.deleteUser(id);
		return true;
	}

	@GetMapping("/showusers")
	public List<User> showUsers() {
		return userService.showUser();

	}*/

}
