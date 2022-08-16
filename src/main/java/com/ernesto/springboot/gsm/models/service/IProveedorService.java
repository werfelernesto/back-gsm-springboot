package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.Proveedor;

public interface IProveedorService {
	
	public List<Proveedor> findAll();

}
