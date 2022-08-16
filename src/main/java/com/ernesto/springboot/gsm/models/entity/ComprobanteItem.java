package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comprobantes_items")
public class ComprobanteItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articulo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer"}) //ver si hace falta "handler"
	private Articulo articulo;
	
	private Double cantidad;
	
	private Double precio;
	
	@Column(name = "recago_descuento")
	private Double recargoDescuento;
	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getRecargoDescuento() {
		return recargoDescuento;
	}
	public void setRecargoDescuento(Double recargoDescuento) {
		this.recargoDescuento = recargoDescuento;
	}
	public Double getTotalItem() {
		return (precio * (1 + (recargoDescuento / 100)) * cantidad);
	}
}
