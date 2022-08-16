package com.ernesto.springboot.gsm.controllers;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ernesto.springboot.gsm.models.entity.Role;
import com.ernesto.springboot.gsm.models.entity.Usuario;
import com.ernesto.springboot.gsm.models.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> usuarios() {
		logger.info("En: usuarios() ");
		return usuarioService.findAll(); 
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario add(@RequestBody Usuario usuario) {
	
		logger.info("En: add() - usuario: " + usuario.toString());
		
		Usuario usuarioDB = new Usuario();
		
		usuarioDB.setEmail(usuario.getEmail());
		usuarioDB.setApellido(usuario.getApellido());
		usuarioDB.setNombre(usuario.getNombre());
		usuarioDB.setPassword(usuario.getPassword());
		usuarioDB.setActivo(usuario.isActivo());
		usuarioDB.setImagen(usuario.getImagen());
		
		for (Role role : usuario.getRoles()) {
			usuarioDB.addRole(role);
		}
	
		return usuarioService.save(usuarioDB);
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario udpdate(@PathVariable Long id, @RequestBody Usuario usuario) {
		
		logger.info("En: update() id: " + id.toString());
		
		Usuario usuarioDB = usuarioService.findById(usuario.getId());
				
		if (usuarioDB != null) {
		
			usuarioDB.setEmail(usuario.getEmail());
			usuarioDB.setApellido(usuario.getApellido());
			usuarioDB.setNombre(usuario.getNombre());
		
			for (Role role: usuario.getRoles()) {
				if (!usuarioDB.getRoles().contains(role)) {
					usuarioDB.addRole(role);
				}
			}
			
			Role role = null;
			for (Iterator<Role> iteratorRole = usuarioDB.getRoles().iterator(); iteratorRole.hasNext();) {
				role = iteratorRole.next();
				
				if (!usuario.getRoles().contains(role)) {
					iteratorRole.remove();
				}
			}
		
		}

		return usuarioService.save(usuarioDB);
	}
	
	
	
	
	
}
