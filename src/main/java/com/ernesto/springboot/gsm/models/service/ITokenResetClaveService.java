package com.ernesto.springboot.gsm.models.service;

import com.ernesto.springboot.gsm.models.entity.TokenResetClave;

public interface ITokenResetClaveService {
	
	public TokenResetClave findByToken(String token);
	
	public TokenResetClave save(TokenResetClave tokenResetClave);

}
