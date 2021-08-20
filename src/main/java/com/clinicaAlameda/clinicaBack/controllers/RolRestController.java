package com.clinicaAlameda.clinicaBack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicaAlameda.clinicaBack.entidades.Role;
import com.clinicaAlameda.clinicaBack.servicios.IRolServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class RolRestController {
	
	@Autowired
	private IRolServicio rolServicio;
	
	@GetMapping("/roles")
	public List<Role> index(){
		return rolServicio.findAll();
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Role rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			rol = rolServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(rol == null) {
			response.put("mensaje", "El rol ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(rol, HttpStatus.OK);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<?> create(@RequestBody Role rol) {
		
		Role rolNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			rolNuevo = rolServicio.save(rol);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El rol ha sido creado con éxito");
		// response.put("roles", rolNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<?> update(@RequestBody Role rol, @PathVariable Long id) {
		
		Role rolActual = rolServicio.findById(id);
		Role rolUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(rolActual == null) {
			response.put("mensaje", "Error: no se puede editar, el rol ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			rolActual.setId(rol.getId());	
			rolActual.setNombre(rol.getNombre());	
			rolUpdated = rolServicio.save(rolActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el rol en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El rol ha sido actualizado con éxito");
		// response.put("roles", rolUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			rolServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el rol en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El rol ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
