package com.lexicon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.model.User;
import com.lexicon.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/{login}")
	public String getUser(@PathVariable String login) {
		User user = new User();
		user.setFbLogin(login);
		userRepository.save(user);
		User foundUser = userRepository.findByFbLogin(login);	
		return "Login: "+foundUser.getFbLogin();
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
}
