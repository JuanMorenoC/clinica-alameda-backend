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

import com.clinicaAlameda.clinicaBack.entidades.Cita;
import com.clinicaAlameda.clinicaBack.servicios.ICitaServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class CitaRestController {
	
	@Autowired
	private ICitaServicio citaServicio;
	
	@GetMapping("/cita")
	public List<Cita> index(){
		return citaServicio.findAll();
	}
	
	@GetMapping("/cita/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cita cita = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			cita = citaServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(cita == null) {
			response.put("mensaje", "La cita ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cita>(cita, HttpStatus.OK);
	}
	
	@PostMapping("/cita")
	public ResponseEntity<?> create(@RequestBody Cita cita) {
		
		Cita citaNueva = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			citaNueva = citaServicio.save(cita);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita ha sido creado con éxito");
		// response.put("cita", citaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/cita/{id}")
	public ResponseEntity<?> update(@RequestBody Cita cita, @PathVariable Long id) {
		
		Cita citaActual = citaServicio.findById(id);
		Cita citaUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(citaActual == null) {
			response.put("mensaje", "Error: no se puede editar, la cita ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			citaActual.setIdCita(cita.getIdCita());	
			citaActual.setEstado(cita.getEstado());	
			citaActual.setHora(cita.getHora());
			citaActual.setFecha_cita(cita.getFecha_cita());	
			citaActual.setDescripcion(cita.getDescripcion());
			citaActual.setPaciente(cita.getPaciente());
			citaActual.setOdontologo(cita.getOdontologo());
			citaActual.setProcedimiento(cita.getProcedimiento());
			citaUpdated = citaServicio.save(citaActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la cita en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La cita ha sido actualizado con éxito");
		// response.put("cita", citaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/cita/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			citaServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la cita en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
