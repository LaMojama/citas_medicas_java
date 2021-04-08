package me.citasmedicas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.citasmedicas.dominio.Diagnostico;


public interface IRepositorioDiagnostico extends JpaRepository<Diagnostico, Integer> {
	
	@Query(value = "SELECT u FROM Diagnostico u WHERE u.enfermedad = ?1")
	List<Diagnostico> findByEnfermedad(@Param("enfermedad") String enfermedad);

}
