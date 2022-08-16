package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.Domicilio;

public interface IDomicilioDao extends JpaRepository<Domicilio, Long>{

}
