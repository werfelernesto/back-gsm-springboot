package com.ernesto.springboot.gsm.models.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ernesto.springboot.gsm.models.entity.Email;

/**
 * The interfaces and classes for Java mail support in the Spring framework are organized as follows:
 *
 *  MailSender interface:				the top-level interface that provides basic functionality for sending simple emails
 *  JavaMailSender interface:			the subinterface of the above MailSender. It supports MIME messages and is mostly used in conjunction
 *   									with the MimeMessageHelper class for the creation of a MimeMessage. 
 *   									It's recommended to use the MimeMessagePreparator mechanism with this interface.
 *  JavaMailSenderImpl class:			provides an implementation of the JavaMailSender interface. It supports the MimeMessage and SimpleMailMessage.
 *  SimpleMailMessage class:			used to create a simple mail message including the from, to, cc, subject and text fields
 *  MimeMessagePreparator interface:	provides a callback interface for the preparation of MIME messages.
 *  MimeMessageHelper class:			helper class for the creation of MIME messages. It offers support for images, typical mail attachments and text content in an HTML layout.
 */
@Service
public class EmailServiceImpl implements IEmailService {

	/*
	 * Interface provista por spring boot starter mail
	 */
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String envioDe;

	private Map<String,String> respuesta = new HashMap<String, String>();
	
	@Override
	public Map<String,String> sendSimpleMail(Email email) {
	
		try {
			
			// Creamos un SimpleMailMessage
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
			simpleMailMessage.setFrom(envioDe);
			simpleMailMessage.setTo(email.getA());
			simpleMailMessage.setSubject(email.getMotivo());
			simpleMailMessage.setText(email.getTexto());
		
			javaMailSender.send(simpleMailMessage);
			 
			respuesta.put("ok","Mail enviado con exito");
			return respuesta; 
		} catch (Exception e) {
			respuesta.put("error","Hubo un error al enviar el mail. Error: " + e.getMessage());
			return respuesta; 
		}
	}

	@Override
	public Map<String,String> sendSimpleMailTemplate(Email email) {

		
		return null;
	}
	
	@Override
	public Map<String,String> sendMailWithAttacments(Email email) {

		// Creamos un mensaje mime
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			// Se setea Multipart true (segundo argumento) para attacments que seran enviados
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setFrom(envioDe);
			mimeMessageHelper.setTo(email.getA());
			mimeMessageHelper.setSubject(email.getMotivo());
			mimeMessageHelper.setText(email.getTexto());
			
		} catch (Exception e) {

		}
		
		return null;
	}



}
