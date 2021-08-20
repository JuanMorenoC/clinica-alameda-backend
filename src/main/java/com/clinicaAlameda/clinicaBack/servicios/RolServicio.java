package com.clinicaAlameda.clinicaBack.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinicaAlameda.clinicaBack.entidades.Role;
import com.clinicaAlameda.clinicaBack.repositorios.RoleRepo;

@Service
public class RolServicio implements IRolServicio{

	@Autowired
    private RoleRepo rolRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
	   return (List<Role>) rolRepository.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Role save(Role rol) {
		return rolRepository.save(rol);

	}


    @Override
    @Transactional
	public void delete(Long id) {
    	rolRepository.deleteById(id);
		
	}

}
