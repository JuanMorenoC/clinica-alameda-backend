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

import com.clinicaAlameda.clinicaBack.entidades.Imagen;
import com.clinicaAlameda.clinicaBack.servicios.IImagenServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class ImagenRestController {
	@Autowired
	private IImagenServicio imagenServicio;
	
	@GetMapping("/imagen")
	public List<Imagen> index(){
		return imagenServicio.findAll();
	}
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Imagen imagen = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			imagen = imagenServicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(imagen == null) {
			response.put("mensaje", "La imagen ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Imagen>(imagen, HttpStatus.OK);
	}
	
	@PostMapping("/imagen")
	public ResponseEntity<?> create(@RequestBody Imagen imagen) {
		
		Imagen imagenNueva = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			imagenNueva = imagenServicio.save(imagen);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La imagen ha sido creado con éxito");
		// response.put("imagen", imagenNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/imagen/{id}")
	public ResponseEntity<?> update(@RequestBody Imagen imagen, @PathVariable Long id) {
		
		Imagen imagenActual = imagenServicio.findById(id);
		Imagen imagenUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(imagenActual == null) {
			response.put("mensaje", "Error: no se puede editar, la imagen ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			imagenActual.setIdImagen(imagen.getIdImagen());	
			imagenActual.setUrl(imagen.getUrl());
			imagenActual.setAdministrador(imagen.getAdministrador());
			imagenUpdated = imagenServicio.save(imagenActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la imagen en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La cita ha sido actualizado con éxito");
		// response.put("imagen", imagenUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/imagen/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			imagenServicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la imagen en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La imagen ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
