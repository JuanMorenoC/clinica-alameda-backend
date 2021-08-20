package com.clinicaAlameda.clinicaBack.servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinicaAlameda.clinicaBack.entidades.Cita;
import com.clinicaAlameda.clinicaBack.repositorios.CitaRepo;

@Service
public class CitaServicio implements ICitaServicio {

	@Autowired
    private CitaRepo citaRepository;
	
	@Override
	@Transactional (readOnly = true)
	public List<Cita> findAll() {
		return (List<Cita>) citaRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Cita findById(Long id) {
		return citaRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Cita save(Cita cita) {
		return citaRepository.save(cita);		
	}

	@Override
	public void delete(Long id) {
		citaRepository.deleteById(id);
		
	}

}
