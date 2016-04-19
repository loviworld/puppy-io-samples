package com.lovi.um.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lovi.puppy.annotation.Service;
import com.lovi.puppy.annotation.ServiceFunction;
import com.lovi.um.dao.UserRepository;
import com.lovi.um.model.User;

@Service("UserService")
public class UserService{

	@Autowired
	private UserRepository userRepository;
	
	@ServiceFunction
	public void insert(User user){
		userRepository.insert(user);
	}
	
	@ServiceFunction
	public void save(User user){
		userRepository.save(user);
	}
	
	@ServiceFunction
	public void delete(User user){
		userRepository.delete(user);
	}
	
	@ServiceFunction
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@ServiceFunction
	public User findByUserId(String userId){
		return userRepository.findByUserId(userId);
	}
	
	@ServiceFunction
	public User findByUserIdAndPassword(String userId,String password){
		return userRepository.findByUserIdAndPassword(userId, password);
	}
	
}
