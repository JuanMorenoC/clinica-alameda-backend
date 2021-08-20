package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaAlameda.clinicaBack.entidades.PQRS;
import com.clinicaAlameda.clinicaBack.entidades.Persona;
import com.clinicaAlameda.clinicaBack.repositorios.PersonaRepo;
import com.clinicaAlameda.clinicaBack.repositorios.PqrsRepo;

@Service
public class PqrsServicio implements IPqrsServicio{

	@Autowired
    private PqrsRepo pqrsRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<PQRS> findAll() {
		return (List<PQRS>) pqrsRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public PQRS findById(Long id) {
		return pqrsRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public PQRS save(PQRS pqrs) {
		return pqrsRepository.save(pqrs);

	}

	@Override
	@Transactional
	public void delete(long id) {
		pqrsRepository.deleteById(id);
		
	}

}
