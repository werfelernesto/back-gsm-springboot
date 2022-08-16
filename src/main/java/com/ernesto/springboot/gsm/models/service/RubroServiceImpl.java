package com.ernesto.springboot.gsm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ernesto.springboot.gsm.models.dao.IRubroDao;
import com.ernesto.springboot.gsm.models.entity.Rubro;

@Service
public class RubroServiceImpl implements IRubroService {

	@Autowired
	private IRubroDao rubroDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Rubro> findAll() {
		return rubroDao.findAll();
	}

}
