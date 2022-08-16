package com.ernesto.springboot.gsm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.Proveedor;
import com.ernesto.springboot.gsm.models.service.IProveedorService;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

	private static Logger logger = LoggerFactory.getLogger(ProveedorController.class);
	
	@Autowired
	private IProveedorService proveedorService;
	
	@GetMapping
	public List<Proveedor> proveedores() {
		logger.info("En: proveedores()" );
		return proveedorService.findAll();
	}
}
