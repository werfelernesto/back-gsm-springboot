package com.ernesto.springboot.gsm.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.ITokenResetClaveDao;
import com.ernesto.springboot.gsm.models.entity.TokenResetClave;

@Service
public class TokenResetClaveServiceImpl implements ITokenResetClaveService {

	@Autowired
	private ITokenResetClaveDao tokenResetClaveDao;
	
	@Override
	@Transactional(readOnly = true)
	public TokenResetClave findByToken(String token) {
		return tokenResetClaveDao.findByToken(token);
	}

	@Override
	@Transactional(readOnly = true)
	public TokenResetClave save(TokenResetClave tokenResetClave) {
		return tokenResetClaveDao.save(tokenResetClave);
	}
	
}
