package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "puntaje", nullable = false, length = 100, unique = true)
    private Integer puntaje;

    @Column(name = "dimension",nullable = false, length = 20)
    private String dimension;

    @Column(name = "comentario_corto",nullable = false, length = 90)
    private String comentario_corto;

    @Column(name = "fecha",nullable = false, unique = true)
    private LocalDate fecha;

    @Column(name = "verificada",nullable = false)
    private Boolean verificada;

    @Column(name = "peso", nullable = false)
    private Integer peso;


    // Creando relacion con la tabla Reseñas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reseñas_id")
    private Reseña reseñas;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getComentario_corto() {
        return comentario_corto;
    }

    public void setComentario_corto(String comentario_corto) {
        this.comentario_corto = comentario_corto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getVerificada() {
        return verificada;
    }

    public void setVerificada(Boolean verificada) {
        this.verificada = verificada;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

}
