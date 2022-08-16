package com.ernesto.springboot.gsm.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernesto.springboot.gsm.models.entity.TokenResetClave;

public interface ITokenResetClaveDao extends JpaRepository<TokenResetClave, Integer> {

	public TokenResetClave findByToken(String token);
}
