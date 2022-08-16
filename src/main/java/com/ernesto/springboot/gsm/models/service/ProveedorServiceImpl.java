package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.IProveedorDao;
import com.ernesto.springboot.gsm.models.entity.Proveedor;

@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return proveedorDao.findAll();
	}

}
