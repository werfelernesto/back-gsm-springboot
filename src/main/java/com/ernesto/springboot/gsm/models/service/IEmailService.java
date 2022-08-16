package com.ernesto.springboot.gsm.models.service;

import java.util.Map;

import com.ernesto.springboot.gsm.models.entity.Email;

public interface IEmailService {
	
	public Map<String,String> sendSimpleMail(Email email);
	
	public Map<String,String> sendSimpleMailTemplate(Email email);
	
	public Map<String,String> sendMailWithAttacments(Email email);

}
