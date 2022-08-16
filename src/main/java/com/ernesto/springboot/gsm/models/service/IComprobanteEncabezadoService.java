package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import com.ernesto.springboot.gsm.models.entity.ComprobanteEncabezado;

public interface IComprobanteEncabezadoService {
	
	public List<ComprobanteEncabezado> findAll();
	public List<ComprobanteEncabezado> fetchAllWithClienteWithTipoComprobanteWithItemWithUSuarioWithArticulo();

}
