package com.example.ReVueltaBack.modelos;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "puntosdeacopio")
public class puntosdeacopio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "horario")
    private String horario;               

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "activo")
    private Boolean activo;

    

}
