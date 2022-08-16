package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.Articulo;

public interface IArticuloService {
	
	public List<Articulo> findAll();
	public Articulo findById(Long id);
	public List<Articulo> findByDescripcionLike(String descripcion);
	public List<Articulo> findByCodigoFabricaLike(String codigoFabrica);
	public List<Articulo> findByCodigoBarraLike(String codigoBarra);
	public boolean existsById(Long id);
	public Articulo save(Articulo articulo);
	public void delete(Long id);
	public int updateImagenById(Long id, String imagenFileName);
	
	public List<Articulo> fetchAllWithProveedorWithRubroJpa();
	public List<Articulo> fetchAllWithProveedorWithRubroNQ();
}
