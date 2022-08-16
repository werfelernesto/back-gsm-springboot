package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	public boolean existsById(Long id);
	public Cliente save(Cliente cliente);
	public void delete(Long id);	
}
