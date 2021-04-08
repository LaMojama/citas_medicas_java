package me.citasmedicas.dominio;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pacientes")
public class Paciente extends Usuario{
	@Column(name="numTarjeta")
	private String numTarjeta;
	@Column(name="telefono")
	private String telefono;
	@Column(name="direccion")
	private String direccion;
	@Column(name="NSS")
	private String NSS;
	@JoinTable(name="medicos_por_paciente", joinColumns = @JoinColumn(name="id_paciente",nullable=false),inverseJoinColumns=@JoinColumn(name="id_medico",nullable=false))
	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties("pacientes")
	private List<Medico> medicos;
	@OneToMany(cascade = {CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Cita> citas;
	
	public Paciente(String nombre, String apellidos, String usuario, String clave, String NSS, String numTarjeta, String telefono, String direccion) {
		super(nombre,apellidos,usuario,clave);
		this.NSS = NSS;
		this.numTarjeta = numTarjeta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.citas = new ArrayList<Cita>();
		this.medicos = new ArrayList<Medico>();
	}
	
	public Paciente() {
		super();
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
