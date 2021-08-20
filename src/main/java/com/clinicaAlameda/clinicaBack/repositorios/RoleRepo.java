package com.clinicaAlameda.clinicaBack.repositorios;
import org.springframework.data.repository.CrudRepository;
import com.clinicaAlameda.clinicaBack.entidades.Cita;
import com.clinicaAlameda.clinicaBack.entidades.Role;

public interface RoleRepo  extends CrudRepository<Role,Long>{

}
