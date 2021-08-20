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
import com.clinicaAlameda.clinicaBack.entidades.TipoPQRS;
import com.clinicaAlameda.clinicaBack.servicios.ITipoPQRSServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class TipoPQRSRestController {
	
	@Autowired
	private ITipoPQRSServicio pqrsServicio;
	
	@GetMapping("/tipopqrs")
	public List<TipoPQRS> index(){
		return pqrsServicio.findAll();
	}
	
	@GetMapping("/tipopqrs/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		TipoPQRS tipoPqrs = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			tipoPqrs = pqrsServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(tipoPqrs == null) {
			response.put("mensaje", "El tipo pqrs ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoPQRS>(tipoPqrs, HttpStatus.OK);
	}
	
	@PostMapping("/tipopqrs")
	public ResponseEntity<?> create(@RequestBody TipoPQRS tipoPqrs) {
		
		TipoPQRS tipoPqrsNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoPqrsNuevo = pqrsServicio.save(tipoPqrs);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El tipo Pqrs ha sido creado con éxito");
		// response.put("tipopqrs", tipoPqrsNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipopqrs/{id}")
	public ResponseEntity<?> update(@RequestBody TipoPQRS tipoPqrs, @PathVariable Long id) {
		
		TipoPQRS tipoPqrsActual = pqrsServicio.findById(id);
		TipoPQRS tipoPqrsUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(tipoPqrsActual == null) {
			response.put("mensaje", "Error: no se puede editar, el tipo de pqrs ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			tipoPqrsActual.setIdPQRS(tipoPqrs.getIdPQRS());	
			tipoPqrsActual.setTipo(tipoPqrs.getTipo());	
			tipoPqrsUpdated = pqrsServicio.save(tipoPqrsActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el tipo de pqrs en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo de pqrs ha sido actualizado con éxito");
		// response.put("tipopqrs", tipoPqrsUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/tipopqrs/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			pqrsServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el tipo de pqrs en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El tipo de pqrs ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
