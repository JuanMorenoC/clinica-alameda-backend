package com.clinicaAlameda.clinicaBack.repositorios;
import org.springframework.data.repository.CrudRepository;
import com.clinicaAlameda.clinicaBack.entidades.Cita;
import com.clinicaAlameda.clinicaBack.entidades.PQRS;

public interface PqrsRepo  extends CrudRepository<PQRS,Long>{

}
