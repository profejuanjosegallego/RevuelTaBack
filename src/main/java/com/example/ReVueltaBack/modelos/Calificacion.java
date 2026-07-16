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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // puntaje 1..5 (la regla de rango va en la capa de validacion). Sin unique ni length: es un entero.
    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;

    @Column(name = "dimension", nullable = false, length = 20)
    private String dimension;

    @Column(name = "comentario_corto", nullable = false, length = 90)
    private String comentario_corto;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "verificada", nullable = false)
    private Boolean verificada;

    @Column(name = "peso", nullable = false)
    private Integer peso;

    // Relacion con Reseña (id_reseña -> reseñas). La dueña es Calificacion.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reseña")
    private Reseña reseña;

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

    public Reseña getReseña() {
        return reseña;
    }

    public void setReseña(Reseña reseña) {
        this.reseña = reseña;
    }
}
