package me.citasmedicas.dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CitaDTO implements Serializable {
	

	/**
	 * 
	 */
	private static final SimpleDateFormat dateFormat
    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = 1L;
	private int cita_id;
	private String fechaHora;
	private String motivoCita;
	private Diagnostico diag;
	
	public CitaDTO() {
		
	}
	
	public String getFechaHora() {
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
	
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}
	
	public void setCita_id(int cita_id) {
		this.cita_id = cita_id;
	}
	
	public java.util.Date getSubmissionDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.fechaHora);
    }

    public void setSubmissionDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.fechaHora = dateFormat.format(date);
    }

}
