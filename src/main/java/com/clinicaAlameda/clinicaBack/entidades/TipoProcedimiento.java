package com.clinicaAlameda.clinicaBack.entidades;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name= "tipoprocedimiento")
public class TipoProcedimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedimiento;
    
    @Column(name = "tipo", nullable = false) 
    private String tipo;
    
    /*
    @OneToMany(mappedBy = "procedimiento")
    private List<Cita> cita;
   */

	private static final long serialVersionUID = 1L;

}
