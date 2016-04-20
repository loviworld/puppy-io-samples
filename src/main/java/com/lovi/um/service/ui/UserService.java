package com.lovi.um.service.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lovi.puppy.annotation.UIService;
import com.lovi.puppy.annotation.UIServiceFunction;
import com.lovi.um.dao.UserRepository;
import com.lovi.um.model.User;

@UIService
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@UIServiceFunction(value="users", delay=1)
	public List<User> pushUsers(){
		return userRepository.findAll();
	}
}
