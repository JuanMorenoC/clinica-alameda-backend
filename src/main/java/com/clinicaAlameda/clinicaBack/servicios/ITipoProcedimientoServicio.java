package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import com.clinicaAlameda.clinicaBack.entidades.TipoProcedimiento;

public interface ITipoProcedimientoServicio {

	public List<TipoProcedimiento>findAll();
    public TipoProcedimiento findById(Long id);
    public TipoProcedimiento save(TipoProcedimiento tipoProcedimiento);
    public void delete(Long id);
}
