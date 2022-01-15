package com.ac.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.entity.Role;
import com.ac.repository.RoleRepository;
import com.ac.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Set<Role> getByNames(String... name) {
		return roleRepository.getByNames(name);
	}

}
