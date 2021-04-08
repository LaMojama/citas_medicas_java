package me.citasmedicas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.citasmedicas.dao.IRepositorioCita;
import me.citasmedicas.dao.IRepositorioDiagnostico;
import me.citasmedicas.dao.IRepositorioMedico;
import me.citasmedicas.dominio.Cita;
import me.citasmedicas.dominio.Diagnostico;
import me.citasmedicas.dominio.Medico;

@Service
public class ServicioCita implements IServicioCita {
	
	@Autowired
    private IRepositorioCita citaDAO;
	@Autowired
    private IRepositorioMedico medicoDAO;
	@Autowired
    private IRepositorioDiagnostico diagnosticoDAO;

    @Override
    public List<Cita> findAll() {
        List<Cita> listCitas= citaDAO.findAll();
        return listCitas;
    }

    @Override
    public Cita findById(int id) {
        return citaDAO.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Cita save(Cita cita) {
    	citaDAO.save(cita);
    	return cita;

    }

    @Override
    public void deleteById(int id) {
    	citaDAO.deleteById(id);
    }
    
    @Override
    public void addDiagnosticoToCita(int cita_id, Diagnostico diagnostico){
    	Cita cita = citaDAO.findById(cita_id).orElseThrow(RuntimeException::new);
    	cita.setDiagnostico(diagnostico);
    	citaDAO.save(cita);
    }
    
    @Override
    public void createCita(Date fechaHora, String motivoCita) {
    	Cita cita = new Cita(fechaHora,motivoCita);
    	citaDAO.save(cita);
    }
    
    @Override
    public void createDiagnostico(String valoracion, String enfermedad, int cita_id) {
    	Diagnostico diagnostico = new Diagnostico(valoracion,enfermedad);
    	Cita cita = citaDAO.findById(cita_id).orElseThrow(RuntimeException::new);
    	cita.setDiagnostico(diagnostico);
        citaDAO.save(cita);
    }
    
	@Override
    public void deleteCitaFromMedico(int medico_id, int cita_id) {
    	Medico medico = medicoDAO.findById(medico_id).orElseThrow(RuntimeException::new);
    	Cita cita = citaDAO.findById(cita_id).orElseThrow(RuntimeException::new);
    	medico.getCitas().remove(cita);
    	medicoDAO.save(medico);
    }
	
	@Override
	public void deleteDiagnosticoFromCita(int cita_id) {
		Cita cita = citaDAO.findById(cita_id).orElseThrow(RuntimeException::new);
		Diagnostico diagnostico = cita.getDiagnostico();
		cita.setDiagnostico(null);
		diagnosticoDAO.delete(diagnostico);
		citaDAO.save(cita);
	}
  

}
