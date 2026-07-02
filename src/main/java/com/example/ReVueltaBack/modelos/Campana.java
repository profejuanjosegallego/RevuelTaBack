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
@Table(name = "Campanas")
public class Campana {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name= "nombre campaña", nullable= false, unique = false, length= 50)
    private String nombre_campana;

    @Column(name= "descripcion_campana", nullable= false, unique = false, length= 50)
    private String descripcion_campana;

    @Column(name= "fecha_inicio", nullable= false, unique = false, length= 50)
    private LocalDateTime fecha_inicio;

    @Column(name= "fecha_final", nullable= false, unique = false, length= 50)
    private LocalDateTime fecha_final;

    @Column(name= "descuento_pct", nullable= false, unique = false, length= 50)
    private Double descuento_pct;

    @Column(name= "activa", nullable= false, unique = false, length= 50)
    private Boolean activa;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre_campana() {
        return nombre_campana;
    }

    public void setNombre_campana(String nombre_campana) {
        this.nombre_campana = nombre_campana;
    }

    public String getDescripcion_campana() {
        return descripcion_campana;
    }

    public void setDescripcion_campana(String descripcion_campana) {
        this.descripcion_campana = descripcion_campana;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(LocalDateTime fecha_final) {
        this.fecha_final = fecha_final;
    }

    public Double getDescuento_pct() {
        return descuento_pct;
    }

    public void setDescuento_pct(Double descuento_pct) {
        this.descuento_pct = descuento_pct;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }


}
