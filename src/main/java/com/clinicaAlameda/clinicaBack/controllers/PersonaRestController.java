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

import com.clinicaAlameda.clinicaBack.entidades.Persona;
import com.clinicaAlameda.clinicaBack.servicios.IPersonaServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class PersonaRestController {
	
	@Autowired
	private IPersonaServicio personaService;
	
	@GetMapping("/persona")
	public List<Persona> index(){
		return personaService.findAll();
	}
	
	@GetMapping("/persona/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			persona = personaService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(persona == null) {
			response.put("mensaje", "El cliente ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	//no corrio postman
	@PostMapping("/persona")
	public ResponseEntity<?> create(@RequestBody Persona persona) {
		
		Persona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			personaNew = personaService.save(persona);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La persona ha sido creado con éxito");
		// response.put("persona", personaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/persona/{id}")
	public ResponseEntity<?> update(@RequestBody Persona persona, @PathVariable String id) {
		
		Persona personaActual = personaService.findById(id);
		Persona personaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(personaActual == null) {
			response.put("mensaje", "Error: no se puede editar, el cliente ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			personaActual.setCedula(persona.getCedula());	
			personaActual.setNombre(persona.getNombre());	
			personaActual.setApellido(persona.getApellido());
			personaActual.setSeudonimo(persona.getSeudonimo());
			personaActual.setTipo_identificacion(persona.getTipo_identificacion());
			personaActual.setCorreo(persona.getCorreo());
			personaActual.setClave(persona.getClave());
			personaActual.setFecha_nacimiento(persona.getFecha_nacimiento());
			personaActual.setCelular(persona.getCelular());
			personaActual.setCiudad(persona.getCiudad());
			personaActual.setDepartamento(persona.getDepartamento());
			personaActual.setPais(persona.getPais());
			personaUpdated = personaService.save(personaActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		// response.put("persona", personaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/persona/{id}")
	public ResponseEntity<?> delete (@PathVariable String id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			personaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
