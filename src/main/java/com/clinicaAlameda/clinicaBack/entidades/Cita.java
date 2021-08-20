package com.clinicaAlameda.clinicaBack.entidades;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name= "cita")
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;
    
    @Column(name = "estado", nullable = false) 
    private String estado;
    
    @Column(name = "hora", nullable = false) 
    private String hora;
    
    @Column(name = "fecha_cita", nullable = false) 
    private String fecha_cita;
    
    @Column(name = "descripcion", length = 1000) 
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "paciente", nullable = true)
    private Persona paciente;
    
    @ManyToOne
    @JoinColumn(name = "odontologo", nullable = true)
    private Persona odontologo;
    
    @ManyToOne
    @JoinColumn(name = "procedimiento", nullable = true)
    private TipoProcedimiento procedimiento;


	private static final long serialVersionUID = 1L;

}