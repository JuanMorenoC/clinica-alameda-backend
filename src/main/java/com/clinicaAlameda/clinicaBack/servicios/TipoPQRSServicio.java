package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaAlameda.clinicaBack.entidades.TipoPQRS;
import com.clinicaAlameda.clinicaBack.repositorios.TipoPQRSRepo;

@Service
public class TipoPQRSServicio implements ITipoPQRSServicio {

	@Autowired
    private TipoPQRSRepo pqrsRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoPQRS> findAll() {
		return (List<TipoPQRS>) pqrsRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public TipoPQRS findById(Long id) {
		return pqrsRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public TipoPQRS save(TipoPQRS tipoPQRS) {
		return pqrsRepository.save(tipoPQRS);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		pqrsRepository.deleteById(id);
		
	}

}
