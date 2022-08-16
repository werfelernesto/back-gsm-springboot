package com.ernesto.springboot.gsm.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "token_reset_claves")
public class TokenResetClave  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_expiracion")
	private Date fechaExpiracion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(int minutos) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutos);
		this.fechaExpiracion = now.getTime();
	}
	
    public boolean isExpired() {
        return new Date().after(this.fechaExpiracion);
    }

	@Override
	public String toString() {
		return "TokenResetClave {id=" + this.id + ", token='" + this.token + "'" + ", usuario=" + this.usuario + ", FechaExpiracion=" + this.fechaExpiracion + "}";  
	}
    
}
