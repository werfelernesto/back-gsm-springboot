package com.ernesto.springboot.gsm.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ernesto.springboot.gsm.models.entity.Articulo;

public interface IArticuloDao extends JpaRepository<Articulo, Long> {

	public List<Articulo> findByDescripcionLikeIgnoreCase(String descripcion);
	public List<Articulo> findByCodigoFabricaLikeIgnoreCase(String codigoFabrica);
	public List<Articulo> findByCodigoBarraLikeIgnoreCase(String codigoBarra);
	
	@Query("select a, p.descripcion from Articulo a join fetch a.proveedor p join fetch a.rubro r")
	public List<Articulo> fetchAllWithProveedorWithRubroJpa();
	
	@Query(value = 
			"SELECT a.*,"
			+ "p.descripcion,"
			+ " r.descripcion "
			+ "FROM articulos a, proveedores p, rubros r "
			+ "WHERE a.proveedor_id = p.proveedor_id "
			+ "AND a.rubro_id = r.rubro_id;",nativeQuery = true)
	public List<Articulo> fetchAllWithProveedorWithRubroNQ();
	
	/*
	 * Por ahi no hace falta el update de las fotos
	 */
	//@Modifying
	//@Query("update Articulo a set a.imagen = ?2 where a.id = ?1")
	//public int updateImagenById(Long id, String imagenFilename);
		
	@Modifying
	@Query("update Articulo a set a.imagen = :imagenFileName where a.id = :id")
	public int updateImagenById(@Param("id") Long id, @Param("imagenFileName") String imagenFileName);
	
}
