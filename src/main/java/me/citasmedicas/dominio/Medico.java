package me.citasmedicas.dominio;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="medicos")
public class Medico extends Usuario{
	@Column(name="numColegiado")
	private String numColegiado;
	@ManyToMany(mappedBy = "medicos")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties("medicos")
	private List<Paciente> pacientes;
	@OneToMany(cascade = {CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Cita> citas;
	
	public Medico(String nombre, String apellidos, String usuario, String clave, String numColegiado) {
		super(nombre,apellidos,usuario,clave);
		this.numColegiado = numColegiado;
		this.pacientes = new ArrayList<Paciente>();
		this.citas = new ArrayList<Cita>();
	}
	
	public Medico() {
		super();
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
	
	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}
	
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@Override
	public String toString() {
		return "Medico [numColegiado=" + numColegiado + ", pacientes=" + pacientes + ", citas=" + citas
				+ ", Nombre=" + getNombre() + ", Apellidos=" + getApellidos() + ", Usuario="
				+ getUsuario() + ", Clave=" + getClave() + "]";
	}

}
