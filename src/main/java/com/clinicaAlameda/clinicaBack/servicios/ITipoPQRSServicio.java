package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;
import com.clinicaAlameda.clinicaBack.entidades.TipoPQRS;

public interface ITipoPQRSServicio {

	public List<TipoPQRS>findAll();
    public TipoPQRS findById(Long id);
    public TipoPQRS save(TipoPQRS tipoPQRS);
    public void delete(Long id);
}
