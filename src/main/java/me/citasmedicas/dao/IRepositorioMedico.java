package me.citasmedicas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.citasmedicas.dominio.Medico;

public interface IRepositorioMedico extends JpaRepository<Medico, Integer> {
	
	// CAMBIAR POR QUERY DE JPQL
	
	@Query(value = "SELECT u FROM Medico u WHERE u.usuario = ?1")
	List<Medico> findByUser(@Param("user") String user);
	
	@Query(value = "SELECT u FROM Medico u WHERE u.nombre = ?1")
	List<Medico> findByName(@Param("name") String name);

}
