package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

}
