package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.Proveedor;

public interface IProveedorDao extends JpaRepository<Proveedor, Long> {

}
