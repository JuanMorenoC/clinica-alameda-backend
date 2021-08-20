package com.clinicaAlameda.clinicaBack.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicaAlameda.clinicaBack.entidades.Persona;
import com.clinicaAlameda.clinicaBack.servicios.RecuperarContrasenaServicio;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200", "*"})
@CrossOrigin
public class RecuperarContrasenaRestController {
	@Autowired
    private RecuperarContrasenaServicio mailService;
	
	@PostMapping("/enviocontrasena")
    public ResponseEntity<?> sendMail(@RequestBody Persona persona){
		Map<String, Object> response = new HashMap<>();
		try {
	        mailService.sendMail(persona);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
    }
}
