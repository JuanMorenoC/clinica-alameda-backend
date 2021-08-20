package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import com.clinicaAlameda.clinicaBack.entidades.Role;

public interface IRolServicio {

	public List<Role>findAll();
    public Role findById(Long id);
    public Role save(Role rol);
    public void delete(Long id);
}
