package com.clinicaAlameda.clinicaBack.repositorios;
import org.springframework.data.repository.CrudRepository;
import com.clinicaAlameda.clinicaBack.entidades.TipoProcedimiento;

public interface ProcedimientoRepo extends CrudRepository<TipoProcedimiento,Long> {



}
