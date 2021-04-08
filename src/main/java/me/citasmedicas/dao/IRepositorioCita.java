package me.citasmedicas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.citasmedicas.dominio.Cita;


public interface IRepositorioCita extends JpaRepository<Cita, Integer> {
	
	@Query(value = "SELECT u FROM Cita u WHERE u.motivoCita = ?1")
	List<Cita> findByMotivoCita(@Param("motivoCita") String motivoCita);
	
	@Query(value = "SELECT u FROM Cita u WHERE u.fechaHora = ?1")
	List<Cita> findByFechaHora(@Param("fechaHora") String fechaHora);

}
