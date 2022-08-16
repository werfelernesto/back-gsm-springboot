package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.IComprobanteEncabezadoDao;
import com.ernesto.springboot.gsm.models.entity.ComprobanteEncabezado;

@Service
public class ComprobanteEncabezadoServiceImpl implements IComprobanteEncabezadoService {

	@Autowired
	private IComprobanteEncabezadoDao comprobanteEncabezadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ComprobanteEncabezado> findAll() {
		return comprobanteEncabezadoDao.findAll();
	}

	@Override
	public List<ComprobanteEncabezado> fetchAllWithClienteWithTipoComprobanteWithItemWithUSuarioWithArticulo() {
		return comprobanteEncabezadoDao.fetchAllWithClienteWithTipoComprobanteWithItemWithUSuarioWithArticulo();
	}

	
}
