package com.lovi.um.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lovi.um.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByUserId(String userId);
	public User findByName(String name);
	
}
