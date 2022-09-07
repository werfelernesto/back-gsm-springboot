package com.ernesto.springboot.gsm.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.Email;
import com.ernesto.springboot.gsm.models.entity.TokenResetClave;
import com.ernesto.springboot.gsm.models.entity.Usuario;
import com.ernesto.springboot.gsm.models.service.IEmailService;
import com.ernesto.springboot.gsm.models.service.ITokenResetClaveService;
import com.ernesto.springboot.gsm.models.service.IUsuarioService;

@RestController
@RequestMapping("/api/claves")
public class RecuperaClaveController {

	private static Logger logger = LoggerFactory.getLogger(RecuperaClaveController.class);

	private Map<String, String> response = new HashMap<>();
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ITokenResetClaveService tokenResetClaveService;
	
	@Autowired
	private IEmailService emailService;
	
	@GetMapping("/olvido-clave")
	public ResponseEntity<Map<String, String>> olvidoClave(@RequestParam("email") String email) {
	
		String mensajeLog;
		
		mensajeLog = String.format("En: olvidoClave()- email: %s ", email);
		logger.info(mensajeLog);
		 
		Usuario usuario = usuarioService.findByEmail(email);
		
		if (usuario == null) {
			response.put("error", "No existe el usuario con el email: " + email);
			return ResponseEntity.ok().body(response);	
		}
		
		TokenResetClave tokenResetClave = new TokenResetClave();
		
		tokenResetClave.setUsuario(usuario);
		tokenResetClave.setToken(UUID.randomUUID().toString());
		tokenResetClave.setFechaExpiracion(1440); // un dia en minutos
		
		tokenResetClaveService.save(tokenResetClave);
		
		mensajeLog = String.format("En: olvidoClave() - tokenResetClave: %s ", tokenResetClave.toString());
		logger.info(mensajeLog);
	
		Email mail = new Email();
		
		mail.setA(email);
		mail.setMotivo("Solicitud de balnqueo de clave");
		mail.setTexto("Siga el siguiente link para el blanqueo de la clave:"
				+ " http://localhost:4200/api/claves/olvido-clave?token=" + tokenResetClave.getToken());
		
		response = emailService.sendSimpleMail(mail);
		
		return ResponseEntity.ok().body(response);
		
	}
	
}