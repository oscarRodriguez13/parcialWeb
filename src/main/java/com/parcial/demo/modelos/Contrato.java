package com.parcial.demo.modelos;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificador;

    private Double valor;

    @Column(nullable = false)
    private String nombreContratante;

    @Column(nullable = false)
    private String documentoContratante;

    private String nombreContratantista;

    private String documentoContratantista;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    
}
