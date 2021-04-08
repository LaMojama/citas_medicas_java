package me.citasmedicas.dominio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MedicoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String clave;
	private String numColegiado;
	private List<Paciente> pacientes;
	private List<Cita> citas;

	
	public MedicoDTO() {
		
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
	
	public String getNumColegiado() {
		return numColegiado;
	}
	
	public List<Paciente> getPacientes(){
		return pacientes;
	}
	
	public List<Cita> getCitas(){
		return citas;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}
	
	public void setPacientes(LinkedList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public void setCitas(LinkedList<Cita> citas) {
		this.citas = citas;
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
	

}
