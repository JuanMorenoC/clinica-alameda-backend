package com.clinicaAlameda.clinicaBack.servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaAlameda.clinicaBack.entidades.Persona;
import com.clinicaAlameda.clinicaBack.repositorios.PersonaRepo;

@Service
public class PersonaServicio implements IPersonaServicio{

	@Autowired
    private PersonaRepo personaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(String id) {
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		return personaRepository.save(persona);
		
	}

	@Override
	@Transactional
	public void delete(String id) {
		personaRepository.deleteById(id);
		
	}

}
