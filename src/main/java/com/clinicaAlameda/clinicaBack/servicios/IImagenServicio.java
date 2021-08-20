package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import com.clinicaAlameda.clinicaBack.entidades.Imagen;

public interface IImagenServicio {
	public List<Imagen> findAll();
    public Imagen findById(Long id);
    public Imagen save(Imagen imagen);
    public void delete(Long id);
}
