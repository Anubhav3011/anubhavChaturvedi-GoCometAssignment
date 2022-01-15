package com.ac.service;

import java.util.Set;

import com.ac.entity.Role;

public interface RoleService {

	public void save(Role role);

	public Set<Role> getByNames(String... name);

}
