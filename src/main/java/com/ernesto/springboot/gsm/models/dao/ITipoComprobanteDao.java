package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ernesto.springboot.gsm.models.entity.TipoComprobante;

public interface ITipoComprobanteDao extends JpaRepository<TipoComprobante, Integer>{

}
