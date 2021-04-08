package me.citasmedicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.citasmedicas.dao.IRepositorioDiagnostico;
import me.citasmedicas.dominio.Diagnostico;

@Service
public class ServicioDiagnostico implements IServicioDiagnostico {
	
	@Autowired
    private IRepositorioDiagnostico diagnosticoDAO;

    @Override
    public List<Diagnostico> findAll() {
        List<Diagnostico> listCitas= diagnosticoDAO.findAll();
        return listCitas;
    }

    @Override
    public Diagnostico findById(int id) {
        return diagnosticoDAO.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
    	diagnosticoDAO.save(diagnostico);
    	return diagnostico;

    }

    @Override
    public void deleteById(int id) {
    	diagnosticoDAO.deleteById(id);
    }

}
