package com.ac.service;

import java.util.List;

import com.ac.entity.User;

public interface UserService {

	public List<User> findAll();

	public void save(User role);

	public User getUserByUsername(String username);

}
