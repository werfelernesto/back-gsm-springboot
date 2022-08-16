package com.ernesto.springboot.gsm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.Rubro;
import com.ernesto.springboot.gsm.models.service.IRubroService;

@RestController
@RequestMapping("/api/rubro")
public class RubroController {

	private static Logger logger = LoggerFactory.getLogger(RubroController.class);
	
	@Autowired
	private IRubroService rubroService;
	
	@GetMapping
	public List<Rubro> rubros() {
		logger.info("En: rubros()");
		return rubroService.findAll();
	}
	
}
