package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.Role;

public interface IRoleService {
	
	public List<Role> findAll();
	public Role save(Role role);
	public void delete(Long id);

}
