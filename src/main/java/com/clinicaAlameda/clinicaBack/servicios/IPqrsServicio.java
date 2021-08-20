package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import com.clinicaAlameda.clinicaBack.entidades.PQRS;
import com.clinicaAlameda.clinicaBack.entidades.Persona;

public interface IPqrsServicio {

	public List<PQRS>findAll();
    public PQRS findById(Long id);
    public PQRS save(PQRS pqrs);
    public void delete(long id);
}
