package me.citasmedicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.citasmedicas.dao.IRepositorioMedico;
import me.citasmedicas.dao.IRepositorioPaciente;
import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Medico;
import me.citasmedicas.dominio.Paciente;

@Service
public class ServicioPaciente implements IServicioPaciente {
	
	@Autowired
	private IRepositorioPaciente pacienteDAO;
	@Autowired
	private IRepositorioMedico medicoDAO;

    @Override
    public List<Paciente> findAll() {
        List<Paciente> listPacientes= pacienteDAO.findAll();
        return listPacientes;
    }

    @Override
    public Paciente findById(int id) {
        return pacienteDAO.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Paciente save(Paciente paciente) {
    	pacienteDAO.save(paciente);
    	return paciente;

    }

    @Override
    public void deleteById(int id) {
    	pacienteDAO.deleteById(id);
    }
    
    @Override
    public void addMedicoToPaciente(int paciente_id, Medico medico) {
    	Paciente paciente = pacienteDAO.findById(paciente_id).orElseThrow(RuntimeException::new);
    	paciente.getMedicos().add(medico);
    	pacienteDAO.save(paciente);
    }
    
    @Override
    public void addCitaToPaciente(Cita cita, int paciente_id) {
    	Paciente paciente = pacienteDAO.findById(paciente_id).orElseThrow(RuntimeException::new);
    	paciente.getCitas().add(cita);
    	pacienteDAO.save(paciente);
    }
    
    public  Paciente deletePaciente(Paciente paciente, Medico m) {
    	paciente.getMedicos().remove(m);
    	m.getPacientes().remove(paciente);
    	pacienteDAO.save(paciente);
    	medicoDAO.save(m);
    	return paciente;
    }
    
	public Paciente addCita(Paciente p,Medico m,Cita c) {
		m.getCitas().add(c);
		medicoDAO.save(m);
		p.getCitas().add(c);
		pacienteDAO.save(p);
		return p;
	}

	@Override
	public List<Paciente> findByName(String name) {
		List<Paciente> lista = pacienteDAO.findByName(name);
		return lista;
	}

	@Override
	public List<Paciente> findByUser(String user) {
		List<Paciente> lista = pacienteDAO.findByUser(user);
		return lista;
	}
    
    

}
