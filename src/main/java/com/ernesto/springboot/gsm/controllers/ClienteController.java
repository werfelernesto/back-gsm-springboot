package com.ernesto.springboot.gsm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.Cliente;
import com.ernesto.springboot.gsm.models.service.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private static Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	private String mensajeLog;
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> clientes() {
		logger.info("En: clientes() ");
		return clienteService.findAll();
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente add(@RequestBody Cliente cliente) {
		
		mensajeLog = String.format("En: add() - cliente: %s ", cliente.toString());
		logger.info(mensajeLog);
		return clienteService.save(cliente);
	}
	
	@PutMapping("/cliente/{id}")
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		mensajeLog = String.format("En: update() - id: %s ", id.toString());
		logger.info(mensajeLog);
		
		if (clienteService.existsById(id)) {
			return clienteService.save(cliente);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		mensajeLog = String.format("En: delete() - id: %s ", id.toString());
		logger.info(mensajeLog);
		
		clienteService.delete(id);
	}
	
}