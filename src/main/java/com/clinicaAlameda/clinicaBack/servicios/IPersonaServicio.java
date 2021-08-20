package com.clinicaAlameda.clinicaBack.servicios;
import java.util.List;
import com.clinicaAlameda.clinicaBack.entidades.Persona;

public interface IPersonaServicio {

	public List<Persona>findAll();
    public Persona findById(String id);
    public Persona save(Persona persona);
    public void delete(String id);
}
