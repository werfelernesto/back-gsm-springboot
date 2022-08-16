package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.IUsuarioDao;
import com.ernesto.springboot.gsm.models.entity.Usuario;


/**
 * 
 * @author Ernesto
 * implementar userDetailService 
 */

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return usuarioDao.existsById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

}
