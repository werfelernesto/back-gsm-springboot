package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ofertas")
public class Oferta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double cantidad;
	
	private Double procentaje;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_desde")
	private Date fechaDesde;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_hasta")
	private Date fechaHasta;
}
