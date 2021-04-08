package me.citasmedicas.restservice;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.CitaDTO;
import me.citasmedicas.service.IServicioCita;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaREST {
    @Autowired
    private IServicioCita citaServicio;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/lista")
    public List<CitaDTO> findAll() {
        List<Cita> listaCitas = citaServicio.findAll();
        return listaCitas.stream()
        		.map(this::convertToDto)
        		.collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CitaDTO findOne(@PathVariable Integer id) {
        Cita cita = citaServicio.findById(id);
        return convertToDto(cita);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitaDTO create(@RequestBody CitaDTO citaDTO) throws ParseException {
        Cita cita = convertToEntity(citaDTO);
        Cita citaCreada = citaServicio.save(cita);
        return convertToDto(citaCreada);
        
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws ParseException {
    	citaServicio.deleteById(id);
    }

    @PutMapping
    public void updateCita(@RequestBody CitaDTO citaDTO) throws ParseException {
        Cita cita = citaServicio.findById(citaDTO.getCita_id());
        modelMapper.map(citaDTO, cita);
        cita.setFechaHora(citaDTO.getSubmissionDateConverted(
        		"GMT+01:00"));
        citaServicio.save(cita);
    }
    
    @PostMapping("/deleteCitaFromMedico/{medico_id}")
    public CitaDTO DeleteCitaFromMedico(@PathVariable Integer medico_id, @RequestBody CitaDTO citaDTO) throws ParseException {
        citaServicio.deleteCitaFromMedico(medico_id, citaDTO.getCita_id());
        return citaDTO;
        
    }
    
    @PostMapping("/deleteDiagnosticoFromCita/{cita_id}")
    public CitaDTO DeleteDiagnosticoFromCita(@PathVariable Integer cita_id, @RequestBody CitaDTO citaDTO) throws ParseException {
        citaServicio.deleteDiagnosticoFromCita(cita_id);
        return citaDTO;
        
    }
    

    
    private CitaDTO convertToDto(Cita cita) {
        CitaDTO citaDto = modelMapper.map(cita, CitaDTO.class);
        citaDto.setSubmissionDate(cita.getFechaHora(), 
            "GMT+01:00");
        return citaDto;
    }
    
    private Cita convertToEntity(CitaDTO citaDto) throws ParseException {
        Cita cita = modelMapper.map(citaDto, Cita.class);
        cita.setFechaHora(citaDto.getSubmissionDateConverted(
        		"GMT+01:00"));
        return cita;
    }
}
