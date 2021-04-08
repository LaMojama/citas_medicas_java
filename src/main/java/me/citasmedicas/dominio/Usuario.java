package me.citasmedicas.dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="usuario_id",nullable=false)
	private int id;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String clave;
	
	
	public Usuario(String nombre, String apellidos, String usuario, String clave) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public Usuario() {
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
