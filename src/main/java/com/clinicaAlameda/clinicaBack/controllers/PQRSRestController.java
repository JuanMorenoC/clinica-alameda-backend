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

import com.clinicaAlameda.clinicaBack.entidades.PQRS;
import com.clinicaAlameda.clinicaBack.servicios.IPqrsServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class PQRSRestController {
	
	@Autowired
	private IPqrsServicio pqrsServicio;
	
	@GetMapping("/pqrs")
	public List<PQRS> index(){
		return pqrsServicio.findAll();
	}
	
	@GetMapping("/pqrs/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		PQRS pqrs = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			pqrs = pqrsServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(pqrs == null) {
			response.put("mensaje", "El pqrs ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PQRS>(pqrs, HttpStatus.OK);
	}
	
	@PostMapping("/pqrs")
	public ResponseEntity<?> create(@RequestBody PQRS pqrs) {
		
		PQRS pqrsNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			pqrsNuevo = pqrsServicio.save(pqrs);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El pqrs ha sido creado con éxito");
		// response.put("pqrs", pqrsNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pqrs/{id}")
	public ResponseEntity<?> update(@RequestBody PQRS pqrs, @PathVariable Long id) {
		
		PQRS pqrsActual = pqrsServicio.findById(id);
		PQRS pqrsUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(pqrsActual == null) {
			response.put("mensaje", "Error: no se puede editar, el pais ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			pqrsActual.setIdPQRS(pqrs.getIdPQRS());	
			pqrsActual.setDescripcion(pqrs.getDescripcion());	
			pqrsActual.setRespuesta(pqrs.getRespuesta());
			pqrsUpdated = pqrsServicio.save(pqrsActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el pqrs en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El pqrs ha sido actualizado con éxito");
		// response.put("pqrs", pqrsUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/pqrs/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			pqrsServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el pqrs en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El pqrs ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
