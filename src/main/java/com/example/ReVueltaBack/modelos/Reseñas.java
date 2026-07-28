package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reseñas")
public class Reseñas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String comentario;

    @Column
    private String titulo;

    @Column
    private LocalDate fecha;

    @Column
    private Boolean recomendado;

    @Column
    private Boolean editada;

    @Column
    private Boolean visible;


    
}
