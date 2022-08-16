package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comprobantes_encabezados")
public class ComprobanteEncabezado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "tipo_comprobante_id")
	private TipoComprobante tipoComprobante;
	
	/*
	 * Relacion Cliente-ComprobanteEncabezado uni-direccional
	 */
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "tipo_venta")
	private Integer tipoVenta;
	
	@Column(name = "recargo_descuento")
	private double recargoDescuento;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private String observaciones;
	
	/*
	 * La relacion encabezado-items es unidireccional.
	 * Entramos por encabezado (entidad padre) para ver
	 * los items (entidad hija), pero no alrevez.
	 * 
	 * Al ser una relacion en un sentido debemos indicar 
	 * con @JoinColumn el nombre foreing key en la tabla 
	 * ComporanteItem en este caso coprobante_encabezado_id
	 * 
	 * En ComprobanteItem no hay referencia al ComprobanteEncabezado
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "comprobante_encabezado_id")
	private Set<ComprobanteItem> items;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(Integer tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public double getRecargoDescuento() {
		return recargoDescuento;
	}

	public void setRecargoDescuento(double recargoDescuento) {
		this.recargoDescuento = recargoDescuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set<ComprobanteItem> getItems() {
		return items;
	}

	public void setItems(Set<ComprobanteItem> items) {
		this.items = items;
	}

	@PrePersist
	public void prePersit() {
		fecha = new Date();
	}
	
	public Double getTotalFactura() {
		
		Double total = 0.0;
	
		for (Iterator<ComprobanteItem> it = items.iterator(); it.hasNext();) {
			total += it.next().getTotalItem() * (1 + (recargoDescuento / 100));
		}
		
		return total;
	}
}
