package com.clinicaAlameda.clinicaBack.entidades;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name= "pqrs")
public class PQRS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPQRS;

    @Column(name = "descripcion", nullable = false, length = 1000) 
    private String descripcion;
    
    @Column(name = "respuesta", nullable = false, length = 1000) 
    private String respuesta;
   
    @ManyToOne
    @JoinColumn(name = "moderador", nullable = false)
    private Persona moderador;
    
    @ManyToOne
    @JoinColumn(name = "paciente", nullable = false)
    private Persona paciente;

    @ManyToOne
    @JoinColumn(name = "tipopqrs", nullable = false)
    private TipoPQRS tipopqrs;
   

	private static final long serialVersionUID = 1L;

}
