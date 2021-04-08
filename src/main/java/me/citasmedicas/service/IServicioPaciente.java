package me.citasmedicas.service;

import java.util.List;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.Paciente;

public interface IServicioPaciente {
	
	  public List<Paciente> findAll();

	   public Paciente findById(int id);
	   
	   public List<Paciente> findByName(String name);
	   
	   public List<Paciente> findByUser(String user);

	   public Paciente save(Paciente paciente);

	   public void deleteById(int id);
	   
	   public void addMedicoToPaciente(int paciente_id, Medico medico);
	   
	   public void addCitaToPaciente(Cita cita, int paciente_id);

	   public Paciente deletePaciente(Paciente paciente, Medico m);

	   public Paciente addCita(Paciente p, Medico m, Cita c);


}
