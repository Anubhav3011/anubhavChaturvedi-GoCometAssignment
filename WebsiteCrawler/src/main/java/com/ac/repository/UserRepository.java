package com.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ac.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM Users u WHERE u.user_username = :username", nativeQuery = true)
	public User getUserByUsername(String username);

}