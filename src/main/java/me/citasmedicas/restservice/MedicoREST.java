package me.citasmedicas.restservice;

import java.security.Principal;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.HttpStatus;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.CitaDTO;
import me.citasmedicas.dominio.Diagnostico;
import me.citasmedicas.dominio.DiagnosticoDTO;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.MedicoDTO;
import me.citasmedicas.dominio.Paciente;
import me.citasmedicas.dominio.PacienteDTO;
import me.citasmedicas.service.IServicioCita;
import me.citasmedicas.service.IServicioDiagnostico;
import me.citasmedicas.service.IServicioMedico;
import me.citasmedicas.service.IServicioPaciente;

@RestController
@CrossOrigin(origins = "*")
public class MedicoREST {
	    @Autowired
	    private IServicioMedico medicoServicio;
	    @Autowired
	    private IServicioPaciente pacienteServicio;
	    @Autowired
	    private IServicioCita citaServicio;
	    @Autowired
	    private IServicioDiagnostico diagnosticoServicio;
	    
	    @Autowired
	    private ModelMapper modelMapper;
	    
	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/medicos/lista")
	    public List<MedicoDTO> findAll() {
	    	List<Medico>listaMedicos = medicoServicio.findAll();
	        return listaMedicos.stream()
	        		.map(this::convertToDto)
	        		.collect(Collectors.toList());
	    }
	    
	    @CrossOrigin(origins = "*")
	    @RequestMapping("/loginMedico")
	    public boolean login(@RequestBody MedicoDTO medico) {
	    	return medico.getUsuario().equals("user1") && medico.getClave().equals("password1");
	    }
	    
	    @CrossOrigin(origins = "*")
	    @RequestMapping("/medico")
	    public Principal user(HttpServletRequest request) {
	        String authToken = request.getHeader("Authorization")
	          .substring("Basic".length()).trim();
	        return () ->  new String(Base64.getDecoder()
	          .decode(authToken)).split(":")[0];
	    }
	    
	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/medicos/{id}")
	    public MedicoDTO findOne(@PathVariable Integer id) {
	        Medico medico = medicoServicio.findById(id);
	        return convertToDto(medico);
	    }

	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/medicos")
	    @ResponseStatus(HttpStatus.CREATED)
	    public MedicoDTO create(@RequestBody MedicoDTO medicoDTO) throws ParseException {
	        Medico medico = convertToEntity(medicoDTO);
	        Medico medicoCreado = medicoServicio.save(medico);
	        return convertToDto(medicoCreado);
	    }

	    @CrossOrigin(origins = "*")
	    @DeleteMapping("/api/medicos/{id}")
	    public void delete(@PathVariable Integer id) throws ParseException {
	    	medicoServicio.deleteById(id);
	    }

	    @CrossOrigin(origins = "*")
	    @PutMapping("/api/medicos")
	    public void updateMedico(@RequestBody MedicoDTO medicoDTO) throws ParseException {
	    	Medico medico = medicoServicio.findById(medicoDTO.getId());
	    	modelMapper.map(medicoDTO, medico);
	        medicoServicio.save(medico);
	    }
	    
	    @CrossOrigin(origins = "*")
	    @GetMapping("/api/medicos/usuario/{usuario}")
	    public List<MedicoDTO> getMedicoByUsuario(@PathVariable String usuario){
	    	List<Medico> lista = medicoServicio.findByUser(usuario);
	    		return lista.stream()
	    				.map(this::convertToDto)
	    				.collect(Collectors.toList());
	    }
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/medicos/{idMedico}/addCita/{idPaciente}")
		MedicoDTO addCita(@PathVariable int idMedico,@PathVariable int idPaciente, @RequestBody CitaDTO citaDTO) throws ParseException {
			Medico m = medicoServicio.findById(idMedico);
			Paciente p = pacienteServicio.findById(idPaciente);
			Cita c = modelMapper.map(citaDTO, Cita.class);
	        c.setFechaHora(citaDTO.getSubmissionDateConverted(
	        		"GMT+01:00"));
			citaServicio.save(c);
			m = medicoServicio.addCita(m, p, c);

			return convertToDto(m);
		}
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/medicos/{idMedico}/addDiagnostico/{idCita}")
		MedicoDTO addDiagnosticoToCita(@PathVariable int idMedico,@PathVariable int idCita, @RequestBody DiagnosticoDTO diagnosticoDTO) {
			Medico m = medicoServicio.findById(idMedico);
			Cita c = citaServicio.findById(idCita);
			Diagnostico d = modelMapper.map(diagnosticoDTO, Diagnostico.class);
			diagnosticoServicio.save(d);
			m = medicoServicio.addDiagnosticoToCita(m, d, c);

			return convertToDto(m);
		}
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/medicos/{idMedico}/addPaciente/{idPaciente}")
		MedicoDTO addPaciente(@PathVariable int idMedico,@PathVariable int idPaciente, @RequestBody MedicoDTO medicoDTO) {
			Medico m = medicoServicio.findById(idMedico);
			Paciente p = pacienteServicio.findById(idPaciente);
			m = medicoServicio.addPaciente(m, p);

			return convertToDto(m);
		}
	    
	    @CrossOrigin(origins = "*")
	    @PostMapping("/api/medicos/{idMedico}/deleteCita/{idCita}/paciente/{idPaciente}")
		MedicoDTO deleteCita(@PathVariable int idPaciente,@PathVariable int idCita,@PathVariable int idMedico, @RequestBody CitaDTO citaDTO) throws ParseException {
			Cita c = citaServicio.findById(idCita);
			Paciente p = pacienteServicio.findById(idPaciente);
			Medico m = medicoServicio.findById(idMedico);
			m.getCitas().remove(c);
			p.getCitas().remove(c);
			medicoServicio.save(m);
			pacienteServicio.save(p);
			citaServicio.deleteById(idCita);;
			return convertToDto(m);
		}
	    
	    private MedicoDTO convertToDto(Medico medico) {
	        MedicoDTO medicoDTO = modelMapper.map(medico, MedicoDTO.class);
	        return medicoDTO;
	    }
	    
	    private Medico convertToEntity(MedicoDTO medicoDTO) throws ParseException {
	    	Medico medico = modelMapper.map(medicoDTO, Medico.class);
	    	return medico;
	    }
	}
