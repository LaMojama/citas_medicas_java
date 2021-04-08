package me.citasmedicas.dominio;

import java.io.Serializable;
import java.util.List;

public class PacienteDTO implements Serializable {
	
	private String NSS;
	private int id;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String clave;
	private String numTarjeta;
	private String telefono;
	private String direccion;
	private List<Cita> citas;
	private List<Medico> medicos;


	
	public PacienteDTO() {
	}
	
	public int getId() {
		return id;
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
	
	
	public String getNSS() {
		return NSS;
	}
	
	public String getNumTarjeta() {
		return numTarjeta;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public List<Cita> getCitas() {
		return citas;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public void setNSS(String NSS) {
		this.NSS = NSS;
	}
	
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	

}
