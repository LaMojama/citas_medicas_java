package me.citasmedicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.citasmedicas.dao.IRepositorioCita;
import me.citasmedicas.dao.IRepositorioMedico;
import me.citasmedicas.dao.IRepositorioPaciente;
import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Diagnostico;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.Paciente;
import me.citasmedicas.restservice.MedicoNoEncontradoException;

@Service
public class ServicioMedico implements IServicioMedico {
	
	@Autowired
    private IRepositorioMedico medicoDAO;
	@Autowired
    private IRepositorioPaciente pacienteDAO;
	@Autowired
    private IRepositorioCita citaDAO;

    @Override
    public List<Medico> findAll() {
        List<Medico> listMedicos= medicoDAO.findAll();
        return listMedicos;
    }

    @Override
    public Medico findById(int id) {
        return medicoDAO.findById(id)
        	.orElseThrow(MedicoNoEncontradoException::new);
    }

    @Override
    public Medico save(Medico medico) {
    	medicoDAO.save(medico);
    	return medico;

    }

    @Override
    public void deleteById(int id) {
    	medicoDAO.deleteById(id);
    }
    
    public void addPacienteToMedico(Paciente paciente, int medico_id) {
    	Medico medico = medicoDAO.findById(medico_id).orElseThrow(MedicoNoEncontradoException::new);
    	medico.getPacientes().add(paciente);
    	medicoDAO.save(medico);
    }
    
    public void addCitaToMedico(Cita cita, int medico_id) {
    	Medico medico = medicoDAO.findById(medico_id).orElseThrow(MedicoNoEncontradoException::new);
    	medico.getCitas().add(cita);
    	medicoDAO.save(medico);
    }
    
	public Medico addCita(Medico m,Paciente p,Cita c) {
		m.getCitas().add(c);
		medicoDAO.save(m);
		p.getCitas().add(c);
		pacienteDAO.save(p);
		return m;
	}
	
	public Medico addDiagnosticoToCita(Medico m,Diagnostico d,Cita c) {
		c.setDiagnostico(d);
		citaDAO.save(c);
		return m;
	}
	
	public Medico addPaciente(Medico m, Paciente p) {
		m.getPacientes().add(p);
		p.getMedicos().add(m);
		medicoDAO.save(m);
		pacienteDAO.save(p);
		return m;
	}

	@Override
	public List<Medico> findByUser(String user) {
		List<Medico> lista = medicoDAO.findByUser(user);
		return lista;
	}

	@Override
	public List<Medico> findByName(String name) {
		List<Medico> lista = medicoDAO.findByName(name);
		return lista;
	}

}
