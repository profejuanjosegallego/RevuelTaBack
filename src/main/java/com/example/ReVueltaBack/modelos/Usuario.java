package com.example.ReVueltaBack.modelos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 160)
    private String correo;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasena_hash;

    @Column(nullable = false, length = 20)
    private String rol;               // "docente" | "estudiante"

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "color_avatar", length = 9)
    private String color_avatar;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;

    // Constructores, getters y setters...
}


