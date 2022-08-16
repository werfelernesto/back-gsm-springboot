package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	public Usuario findByEmail(String email);
	public Usuario findById(Long id);
	public boolean existsById(Long id);
	public Usuario save(Usuario usuario);
	public void delete(Long id);

}
