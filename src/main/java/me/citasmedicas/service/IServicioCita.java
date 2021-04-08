package me.citasmedicas.service;

import java.util.Date;
import java.util.List;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Diagnostico;

public interface IServicioCita {
	
	  public List<Cita> findAll();

	   public Cita findById(int id);

	   public Cita save(Cita cita);

	   public void deleteById(int id);
	   
	   public void addDiagnosticoToCita(int cita_id, Diagnostico diagnostico);
	   
	   public void createCita(Date fechaHora, String motivoCita);
	   
	   public void createDiagnostico(String valoracion, String enfermedad, int cita_id);

	   public void deleteCitaFromMedico(int medico_id, int cita_id);

	   public void deleteDiagnosticoFromCita(int cita_id);

}
