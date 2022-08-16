package com.ernesto.springboot.gsm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.ComprobanteEncabezado;
import com.ernesto.springboot.gsm.models.service.IComprobanteEncabezadoService;

@RestController
@RequestMapping("/api/comprobante")
public class ComprobanteEncabezadoController {

	private static Logger logger = LoggerFactory.getLogger(ComprobanteEncabezado.class);
	
	@Autowired
	private IComprobanteEncabezadoService comprobanteEncabezadoService;
	
	@GetMapping("/comprobantes")
	public List<ComprobanteEncabezado> comprobantes() {
		logger.info("En: comprobantes() ");
		return comprobanteEncabezadoService.findAll(); 
	}

	@GetMapping("/comprobantesjpa")
	public List<ComprobanteEncabezado> comprobantesjpa() {
		logger.info("En: comprobantesjpa() ");
		return comprobanteEncabezadoService.fetchAllWithClienteWithTipoComprobanteWithItemWithUSuarioWithArticulo(); 
	}
}
