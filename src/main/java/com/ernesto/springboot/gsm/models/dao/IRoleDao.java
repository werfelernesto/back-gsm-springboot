package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Integer> {

}
