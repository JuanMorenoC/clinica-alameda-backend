package com.clinicaAlameda.clinicaBack.repositorios;
import org.springframework.data.repository.CrudRepository;
import com.clinicaAlameda.clinicaBack.entidades.Cita;
import com.clinicaAlameda.clinicaBack.entidades.Persona;

public interface PersonaRepo extends CrudRepository<Persona,String>{

}
