package com.clinicaAlameda.clinicaBack.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "imagen")
public class Imagen implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;
    
    @Column(name = "url", nullable = false, length = 50000000) 
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "administrador", nullable = true)
    private Persona administrador;
    
    private static final long serialVersionUID = 1L;
}
