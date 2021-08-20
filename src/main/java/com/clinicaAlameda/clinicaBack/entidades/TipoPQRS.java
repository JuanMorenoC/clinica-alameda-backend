package com.clinicaAlameda.clinicaBack.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name= "tipopqrs")
public class TipoPQRS implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPQRS;

    @Column(name = "tipo", nullable = false) 
    private String tipo;
    /*
    @OneToMany(mappedBy = "tipopqrs")
    private List<PQRS> pqrs;
   */
   
	private static final long serialVersionUID = 1L;

}
