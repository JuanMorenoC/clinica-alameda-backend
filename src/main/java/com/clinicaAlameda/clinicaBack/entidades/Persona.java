package com.clinicaAlameda.clinicaBack.entidades;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {


	
	@Id
    @Column(name = "cedula", nullable = false) 
    private String cedula;
    
    @Column(name = "nombre", nullable = false) 
    private String nombre;
    
    @Column(name = "apellido", nullable = false) 
    private String apellido;
    
    @Column(name = "seudonimo", nullable = false) 
    private String seudonimo;
    
    @Column(name = "tipo_identificacion", nullable = false) 
    private String tipo_identificacion;
    
    @Column(name = "correo", unique = true ,nullable = false) 
    private String correo;
    
    @Column(name = "clave", nullable = false) 
    private String clave;
    
    @Column(name = "fecha_nacimiento", nullable = false) 
    private Date fecha_nacimiento;

    @Column(name = "celular") 
    private String celular;
    
    @Column(name = "ciudad") 
    private String ciudad;
    
    @Column(name = "departamento") 
    private String departamento;
    
    @Column(name = "pais") 
    private String pais;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "persona_roles", joinColumns = @JoinColumn(name = "persona_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"persona_id", "role_id"})})
	private List<Role> roles;

    /*
    @OneToMany(mappedBy = "moderador")
    private List<PQRS> pqrsModerador;
    
    @OneToMany(mappedBy = "paciente")
    private List<PQRS> pqrsPaciente;
    
    @OneToMany(mappedBy = "paciente")
    private List<Cita> citaPaciente;
    
    @OneToMany(mappedBy = "odontologo")
    private List<Cita> citaOdontologo;
    */
   



	private static final long serialVersionUID = 1L;
}
