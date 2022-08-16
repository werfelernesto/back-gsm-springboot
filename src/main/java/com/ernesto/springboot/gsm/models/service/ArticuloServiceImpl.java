package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.IArticuloDao;
import com.ernesto.springboot.gsm.models.entity.Articulo;

@Service
public class ArticuloServiceImpl implements IArticuloService {

	@Autowired
	private IArticuloDao articuloDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		return articuloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByDescripcionLike(String descripcion) {
		return articuloDao.findByDescripcionLikeIgnoreCase("%"+descripcion+"%") ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByCodigoFabricaLike(String codigoFabrica) {
		return articuloDao.findByCodigoFabricaLikeIgnoreCase("%"+codigoFabrica+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findByCodigoBarraLike(String codigoBarra) {
		return articuloDao.findByCodigoBarraLikeIgnoreCase("%"+codigoBarra+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return articuloDao.existsById(id);
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		return articuloDao.save(articulo);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		articuloDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public int updateImagenById(Long id, String imagenFileName) {
		return articuloDao.updateImagenById(id, imagenFileName);
	}

	@Override
	public List<Articulo> fetchAllWithProveedorWithRubroJpa() {
		return articuloDao.fetchAllWithProveedorWithRubroJpa();
	}

	@Override
	public List<Articulo> fetchAllWithProveedorWithRubroNQ() {
		return articuloDao.fetchAllWithProveedorWithRubroNQ();
	}

}