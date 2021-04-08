package me.citasmedicas.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.Paciente;
import me.citasmedicas.service.IServicioCita;
import me.citasmedicas.service.IServicioMedico;
import me.citasmedicas.service.IServicioPaciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableJpaRepositories("me.citasmedicas.dao") 
@EntityScan("me.citasmedicas.dominio")
@ComponentScan("me.citasmedicas.restservice")
@ComponentScan("me.citasmedicas.service")
@SpringBootApplication 
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(IServicioMedico medicoServicio, IServicioPaciente pacienteServicio, IServicioCita citaServicio) throws Exception {
        return (String[] args) -> {
        	
        	
        	Medico m = new Medico("Juan", "Castillo Cava", "cavamedico", "1234", "123456789");
            Medico m1 = new Medico("Ana", "Pérez Cánovas", "anamedico", "1234", "435435435");
            Paciente p = new Paciente("Pedro","Castillo Castillo","pedropaciente","1234","23432432498","3243243324","666666666","Calle Covid, N19");
            Paciente p1 = new Paciente("Salvador","Castillo Castillo","salvadorpaciente","1234","23432432498","3243243324","666666666","Calle Covid, N19");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date d = format.parse("17/02/2021 13:00:00");
            Cita c = new Cita(d,"Revision");
            Cita c1 = new Cita(d, "Revision");
            pacienteServicio.save(p);
            pacienteServicio.save(p1);
            citaServicio.save(c);
            citaServicio.save(c1);
            medicoServicio.save(m);
            medicoServicio.save(m1);
            citaServicio.createDiagnostico("Todo bien", "Ninguna", 3);
            citaServicio.createDiagnostico("Todo bien", "Ninguna", 4);
            Cita cita = citaServicio.findById(c.getCita_id());
            Cita cita1 = citaServicio.findById(c1.getCita_id());
            Paciente paciente = pacienteServicio.findById(p.getId());
            Paciente paciente1 = pacienteServicio.findById(p1.getId());
            Medico medico = medicoServicio.findById(m.getId());
            Medico medico1 = medicoServicio.findById(m1.getId());
            medicoServicio.addCitaToMedico(cita, 5);
            medicoServicio.addCitaToMedico(cita1, 6);
            pacienteServicio.addCitaToPaciente(cita, 1);
            pacienteServicio.addCitaToPaciente(cita1, 2);
            medicoServicio.addPacienteToMedico(paciente, 5);
            medicoServicio.addPacienteToMedico(paciente1, 6);
            pacienteServicio.addMedicoToPaciente(1, medico);
            pacienteServicio.addMedicoToPaciente(2, medico1);  
            List<Paciente> lista = pacienteServicio.findByUser("pedropaciente");
            Paciente paciente2 = lista.get(0);
            System.out.println(paciente2.getApellidos());
        };
    }
}


