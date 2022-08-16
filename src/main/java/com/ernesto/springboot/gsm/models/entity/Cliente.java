package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	
	private String apellido;
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "domicilio_id")
	private Domicilio domicilio;
	
	@OneToOne
	@JoinColumn(name = "tipo_iva_id")
	private TipoIVA tipoIva;
	
	private Long cuit;
	
	private String email;
	
	private String tefono;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public TipoIVA getTipoIva() {
		return tipoIva;
	}
	public void setTipoIva(TipoIVA tipoIva) {
		this.tipoIva = tipoIva;
	}
	public Long getCuit() {
		return cuit;
	}
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTefono() {
		return tefono;
	}
	public void setTefono(String tefono) {
		this.tefono = tefono;
	}
		
}