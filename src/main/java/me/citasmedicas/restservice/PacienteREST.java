package me.citasmedicas.restservice;

import java.security.Principal;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.CitaDTO;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.MedicoDTO;
import me.citasmedicas.dominio.Paciente;
import me.citasmedicas.dominio.PacienteDTO;
import me.citasmedicas.service.IServicioCita;
import me.citasmedicas.service.IServicioMedico;
import me.citasmedicas.service.IServicioPaciente;

@RestController
@CrossOrigin(origins = "*")
public class PacienteREST {
	    @Autowired
	    private IServicioPaciente pacienteServicio;
	    @Autowired
	    private IServicioMedico medicoServicio;
	    @Autowired
	    private IServicioCita citaServicio;
	    
	    @Autowired
	    private ModelMapper modelMapper;

	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/pacientes/lista")
	    public List<PacienteDTO> findAll() {
	    	List<Paciente>listaPacientes = pacienteServicio.findAll();
	        return listaPacientes.stream()
	        		.map(this::convertToDto)
	        		.collect(Collectors.toList());
	    }

	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/pacientes")
	    @ResponseStatus(HttpStatus.CREATED)
	    public PacienteDTO create(@RequestBody PacienteDTO pacienteDTO) throws ParseException {
	        Paciente paciente = convertToEntity(pacienteDTO);
	        Paciente pacienteCreado = pacienteServicio.save(paciente);
	        return convertToDto(pacienteCreado);
	    }

	    @CrossOrigin(origins = "*")
	    @DeleteMapping("/api/pacientes/{id}")
	    public void delete(@PathVariable Integer id) throws ParseException {
	    	pacienteServicio.deleteById(id);
	    }

	    @CrossOrigin(origins = "*")
	    @PutMapping("/api/pacientes")
	    public void updatePaciente(@RequestBody PacienteDTO pacienteDTO) throws ParseException {
	    	Paciente paciente = pacienteServicio.findById(pacienteDTO.getId());
	    	modelMapper.map(pacienteDTO, paciente);
	        pacienteServicio.save(paciente);
	    }
	    
	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/pacientes/{id}")
	    public PacienteDTO findOne(@PathVariable Integer id) {
	        Paciente paciente = pacienteServicio.findById(id);
	        return convertToDto(paciente);
	    }
	    
	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/pacientes/usuario/{usuario}")
	    public List<PacienteDTO> getPacienteByUsuario(@PathVariable String usuario){
	    	List<Paciente> lista = pacienteServicio.findByUser(usuario);
	    		return lista.stream()
	    				.map(this::convertToDto)
	    				.collect(Collectors.toList());
	    }
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/pacientes/deletePaciente/{id}")
	    @ResponseStatus(HttpStatus.CREATED)
	    public PacienteDTO deletePaciente(@RequestBody PacienteDTO pacienteDTO, @PathVariable int id) throws ParseException {
	    	Paciente paciente = pacienteServicio.findById(pacienteDTO.getId());
	        Medico m = medicoServicio.findById(id);
	        paciente = pacienteServicio.deletePaciente(paciente,m);
	        return convertToDto(paciente);
	    }
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/pacientes/{idPaciente}/addCita/{idMedico}")
		PacienteDTO addCita(@PathVariable int idMedico,@PathVariable int idPaciente, @RequestBody CitaDTO citaDTO) throws ParseException {
			Medico m = medicoServicio.findById(idMedico);
			Paciente p = pacienteServicio.findById(idPaciente);
			Cita c = modelMapper.map(citaDTO, Cita.class);
	        c.setFechaHora(citaDTO.getSubmissionDateConverted(
	        		"GMT+01:00"));
			citaServicio.save(c);
			p = pacienteServicio.addCita(p, m, c);

			return convertToDto(p);
		}
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/pacientes/{idPaciente}/updateCita/{idCita}")
		PacienteDTO updateCita(@PathVariable int idPaciente,@PathVariable int idCita, @RequestBody CitaDTO citaDTO) throws ParseException {
			Cita c = citaServicio.findById(idCita);
			Paciente p = pacienteServicio.findById(idPaciente);
			modelMapper.map(citaDTO, c);
	        c.setFechaHora(citaDTO.getSubmissionDateConverted(
	        		"GMT+01:00"));
			citaServicio.save(c);
			return convertToDto(p);
		}
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/pacientes/{idPaciente}/deleteCita/{idCita}/medico/{idMedico}")
		PacienteDTO deleteCita(@PathVariable int idPaciente,@PathVariable int idCita,@PathVariable int idMedico, @RequestBody CitaDTO citaDTO) throws ParseException {
			Cita c = citaServicio.findById(idCita);
			Paciente p = pacienteServicio.findById(idPaciente);
			Medico m = medicoServicio.findById(idMedico);
			m.getCitas().remove(c);
			p.getCitas().remove(c);
			medicoServicio.save(m);
			pacienteServicio.save(p);
			citaServicio.deleteById(idCita);;
			return convertToDto(p);
		}
	    
	    private PacienteDTO convertToDto(Paciente paciente) {
	    	PacienteDTO pacienteDto = modelMapper.map(paciente, PacienteDTO.class);
	        return pacienteDto;
	    }
	    
	    private Paciente convertToEntity(PacienteDTO pacienteDTO) throws ParseException {
	    	Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
	    	return paciente;
	    }
}
