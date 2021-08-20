package com.clinicaAlameda.clinicaBack.servicios;
import java.util.List;
import com.clinicaAlameda.clinicaBack.entidades.Cita;

public interface ICitaServicio {

	public List<Cita> findAll();
    public Cita findById(Long id);
    public Cita save(Cita cita);
    public void delete(Long id);
	
}
