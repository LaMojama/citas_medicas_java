package me.citasmedicas.service;

import java.util.List;

import me.citasmedicas.dominio.Diagnostico;

public interface IServicioDiagnostico {
	
	  public List<Diagnostico> findAll();

	   public Diagnostico findById(int id);

	   public Diagnostico save(Diagnostico diagnostico);

	   public void deleteById(int id);

}
