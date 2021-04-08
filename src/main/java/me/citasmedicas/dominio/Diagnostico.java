package me.citasmedicas.dominio;

import javax.persistence.*;

@Entity
@Table(name="diagnosticos")
public class Diagnostico{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="diagnostico_id",nullable=false)
	private int diagnostico_id;
	@Column(name="valoracionEspecialista")
	private String valoracionEspecialista;
	@Column(name="enfermedad")
	private String enfermedad;
	
	public Diagnostico(String valoracionEspecialista, String enfermedad) {
		this.valoracionEspecialista = valoracionEspecialista;
		this.enfermedad = enfermedad;
	}
	
	public Diagnostico() {
		
	}
	
	public String getValoracionEspecialista() {
		return valoracionEspecialista;
	}
	
	public String getEnfermedad() {
		return enfermedad;
	}
	
	public int getDiagnostico_id() {
		return diagnostico_id;
	}
	
	public void setValoracionEspecialista(String valoracionEspecialista) {
		this.valoracionEspecialista = valoracionEspecialista;
	}
	
	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	public void setDiagnostico_id(int diagnostico_id) {
		this.diagnostico_id = diagnostico_id;
	}
}
