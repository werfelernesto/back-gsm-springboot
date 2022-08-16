package com.ernesto.springboot.gsm.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ernesto.springboot.gsm.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findById(Long id);
	public Usuario findByEmail(String email);

	@Modifying
	@Query("update Usuario u set u.activo = ?2 where u.id = ?1")
	public int saveUsuarioEnabledById(Long id, boolean activo);
	
	@Modifying
	@Query("update Usuario u set u.password = ?2 where u.id = ?1")
	public int saveUsuarioClaveById(Long id, String clave);
}
