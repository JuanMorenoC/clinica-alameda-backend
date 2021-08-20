package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinicaAlameda.clinicaBack.entidades.TipoProcedimiento;
import com.clinicaAlameda.clinicaBack.repositorios.TipoProcedimientoRepo;

@Service
public class TipoProcedimientoServicio implements ITipoProcedimientoServicio{

	@Autowired
    private TipoProcedimientoRepo tipoProcedimientoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoProcedimiento> findAll() {
		return (List<TipoProcedimiento>) tipoProcedimientoRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public TipoProcedimiento findById(Long id) {
		return tipoProcedimientoRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public TipoProcedimiento save(TipoProcedimiento tipoProcedimiento) {
		return tipoProcedimientoRepository.save(tipoProcedimiento);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		tipoProcedimientoRepository.deleteById(id);
		
	}
}
