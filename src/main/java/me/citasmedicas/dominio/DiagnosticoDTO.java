package me.citasmedicas.dominio;

import java.io.Serializable;

public class DiagnosticoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int diagnostico_id;
	private String valoracionEspecialista;
	private String enfermedad;
	
	public DiagnosticoDTO(String valoracionEspecialista, String enfermedad) {
		this.valoracionEspecialista = valoracionEspecialista;
		this.enfermedad = enfermedad;
	}
	
	public DiagnosticoDTO() {
		
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
