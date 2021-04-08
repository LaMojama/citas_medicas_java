package me.citasmedicas.dominio;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="citas")
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="cita_id",nullable=false)
	private int cita_id;
	@Column(name="fechaHora")
	@Temporal(TemporalType.DATE)
	private Date fechaHora;
	@Column(name="motivoCita")
	private String motivoCita;
	@OneToOne(cascade = CascadeType.ALL)
	private Diagnostico diag;
	
	public Cita(Date fechaHora, String motivoCita) {
		this.fechaHora = fechaHora;
		this.motivoCita = motivoCita;
	}
	
	public Cita() {
		
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	
	public String getMotivoCita() {
		return motivoCita;
	}
	
	public int getCita_id(){
		return cita_id;
	}
	
	public Diagnostico getDiagnostico() {
		return diag;
	}
	
	public void setDiagnostico(Diagnostico diag) {
		this.diag = diag;
	}
	
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}
	
	public void setCita_id(int cita_id) {
		this.cita_id = cita_id;
	}
}

	
