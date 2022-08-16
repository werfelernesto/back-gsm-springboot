package com.ernesto.springboot.gsm.models.entity;

import java.util.Map;

public class Email {
	
	private String de;
	private String a;
	private String motivo;
	private String texto;
	private Map<String, Object> template;
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Map<String, Object> getTemplate() {
		return template;
	}
	public void setTemplate(Map<String, Object> template) {
		this.template = template;
	}
	
}
