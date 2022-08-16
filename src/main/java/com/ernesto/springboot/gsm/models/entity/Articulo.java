package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *	@author Ernesto
 *	https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
 */

@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo_fabrica")
	private String codigoFabrica;
	
	@Column(name = "codigo_barra")
	private String codigoBarra;
	
	private String descripcion;
	
	@Column(name = "precio_lista")
	private Double precio;
	
	private Double margen;
	
	@Column(name = "stock_actual")
	private Double sotckActual;
	
	@Column(name = "punto_reposicion")
	private Double puntoReposicion;

	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	
	@ManyToOne
	@JoinColumn(name = "rubro_id")
	private Rubro rubro;
	
	private String imagen;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoFabrica() {
		return codigoFabrica;
	}

	public void setCodigoFabrica(String codigoFabrica) {
		this.codigoFabrica = codigoFabrica;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getMargen() {
		return margen;
	}

	public void setMargen(Double margen) {
		this.margen = margen;
	}

	public Double getSotckActual() {
		return sotckActual;
	}

	public void setSotckActual(Double sotckActual) {
		this.sotckActual = sotckActual;
	}

	public Double getPuntoReposicion() {
		return puntoReposicion;
	}

	public void setPuntoReposicion(Double puntoReposicion) {
		this.puntoReposicion = puntoReposicion;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
