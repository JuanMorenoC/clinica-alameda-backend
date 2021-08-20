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

import com.clinicaAlameda.clinicaBack.entidades.TipoProcedimiento;
import com.clinicaAlameda.clinicaBack.servicios.ITipoProcedimientoServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class TipoProcedimientoRestController {
	
	@Autowired
	private ITipoProcedimientoServicio procedimientoServicio;
	
	@GetMapping("/tipoprocedimiento")
	public List<TipoProcedimiento> index(){
		return procedimientoServicio.findAll();
	}
	
	@GetMapping("/tipoprocedimiento/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		TipoProcedimiento procedimiento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			procedimiento = procedimientoServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(procedimiento == null) {
			response.put("mensaje", "El procedimiento ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoProcedimiento>(procedimiento, HttpStatus.OK);
	}
	
	@PostMapping("/tipoprocedimiento")
	public ResponseEntity<?> create(@RequestBody TipoProcedimiento procedimiento) {
		
		TipoProcedimiento ProcedimientoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ProcedimientoNuevo = procedimientoServicio.save(procedimiento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El procedimiento ha sido creado con éxito");
		// response.put("tipoprocedimiento", ProcedimientoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoprocedimiento/{id}")
	public ResponseEntity<?> update(@RequestBody TipoProcedimiento procedimiento, @PathVariable Long id) {
		
		TipoProcedimiento procedimientoActual = procedimientoServicio.findById(id);
		TipoProcedimiento procedimientoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(procedimientoActual == null) {
			response.put("mensaje", "Error: no se puede editar, el pais ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			procedimientoActual.setIdProcedimiento(procedimiento.getIdProcedimiento());	
			procedimientoActual.setTipo(procedimiento.getTipo());	
			procedimientoUpdated = procedimientoServicio.save(procedimientoActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el procedimiento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El procedimiento ha sido actualizado con éxito");
		// response.put("tipoprocedimiento", procedimientoUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/tipoprocedimiento/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			procedimientoServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el procedimiento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El procedimiento ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
