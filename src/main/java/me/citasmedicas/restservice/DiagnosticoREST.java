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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.citasmedicas.dominio.Diagnostico;
import me.citasmedicas.dominio.DiagnosticoDTO;
import me.citasmedicas.service.IServicioDiagnostico;

@RestController
@CrossOrigin(origins = "*")
public class DiagnosticoREST {
    @Autowired
    private IServicioDiagnostico diagnosticoServicio;
    
    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "*")
    @GetMapping("/api/diagnosticos/lista")
    public List<DiagnosticoDTO> findAll() {
    	List<Diagnostico >listaDiagnosticos = diagnosticoServicio.findAll();
        return listaDiagnosticos.stream()
        		.map(this::convertToDto)
        		.collect(Collectors.toList());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/api/diagnosticos/{id}")
    public DiagnosticoDTO findOne(@PathVariable Integer id) {
        Diagnostico diagnostico = diagnosticoServicio.findById(id);
        return convertToDto(diagnostico);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/diagnosticos")
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosticoDTO create(@RequestBody DiagnosticoDTO diagnosticoDTO) throws ParseException {
        Diagnostico diagnostico = convertToEntity(diagnosticoDTO);
        Diagnostico diagnosticoCreado = diagnosticoServicio.save(diagnostico);
        return convertToDto(diagnosticoCreado);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/api/diagnosticos/{id}")
    public void delete(@PathVariable Integer id) throws ParseException {
    	diagnosticoServicio.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/api/diagnosticos")
    public void updateDiagnostico(@RequestBody DiagnosticoDTO diagnosticoDTO) throws ParseException {
    	Diagnostico diagnostico = diagnosticoServicio.findById(diagnosticoDTO.getDiagnostico_id());
        modelMapper.map(diagnosticoDTO, diagnostico);
        diagnosticoServicio.save(diagnostico);
    }
    
    private DiagnosticoDTO convertToDto(Diagnostico diagnostico) {
        DiagnosticoDTO diagnosticoDTO = modelMapper.map(diagnostico, DiagnosticoDTO.class);
        return diagnosticoDTO;
    }
    
    private Diagnostico convertToEntity(DiagnosticoDTO diagnosticoDto) throws ParseException {
    	Diagnostico diagnostico = modelMapper.map(diagnosticoDto, Diagnostico.class);
    	return diagnostico;
    }
        
}
