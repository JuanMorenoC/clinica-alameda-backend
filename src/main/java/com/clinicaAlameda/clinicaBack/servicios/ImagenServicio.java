package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinicaAlameda.clinicaBack.entidades.Imagen;
import com.clinicaAlameda.clinicaBack.repositorios.ImagenRepo;

@Service
public class ImagenServicio implements IImagenServicio{
	@Autowired
    private ImagenRepo imagenRepository;
	
	@Override
	@Transactional (readOnly = true)
	public List<Imagen> findAll() {
		return (List<Imagen>) imagenRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Imagen findById(Long id) {
		return imagenRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Imagen save(Imagen imagen) {
		return imagenRepository.save(imagen);		
	}

	@Override
	public void delete(Long id) {
		imagenRepository.deleteById(id);
		
	}
}
