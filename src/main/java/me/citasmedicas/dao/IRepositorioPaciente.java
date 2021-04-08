package me.citasmedicas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.citasmedicas.dominio.Paciente;

public interface IRepositorioPaciente extends JpaRepository<Paciente, Integer> {
	
	// CAMBIAR POR QUERY DE JPQL

	@Query(value = "SELECT u FROM Paciente u WHERE u.usuario = ?1")
	List<Paciente> findByUser(@Param("user") String user);
	
	@Query(value = "SELECT u FROM Paciente u WHERE u.nombre = ?1")
	List<Paciente> findByName(@Param("name") String name);
}
