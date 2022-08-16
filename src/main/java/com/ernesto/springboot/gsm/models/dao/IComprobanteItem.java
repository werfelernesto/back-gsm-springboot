package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.ComprobanteItem;

public interface IComprobanteItem extends JpaRepository<ComprobanteItem, Long>{

}
