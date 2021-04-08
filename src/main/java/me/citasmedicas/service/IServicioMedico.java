package me.citasmedicas.service;

import java.util.List;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Diagnostico;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.Paciente;

public interface IServicioMedico {
	
	   public List<Medico> findAll();

	   public Medico findById(int id);
	   
	   public List<Medico> findByUser(String user);
	   
	   public List<Medico> findByName(String name);

	   public Medico save(Medico medico);

	   public void deleteById(int id);
	   
	   public void addPacienteToMedico(Paciente paciente, int medico_id);
	   
	   public void addCitaToMedico(Cita cita, int medico_id);

	   public Medico addCita(Medico m, Paciente p, Cita c);

	   public Medico addDiagnosticoToCita(Medico m, Diagnostico d, Cita c);
	   
	   public Medico addPaciente(Medico m, Paciente p);

}
