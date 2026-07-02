package com.example.ReVueltaBack.modelos;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name =  "recompensas")
public class Recompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "puntos_requeridos", nullable = false)
    private  Integer puntos_requeridos;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "activa", nullable = false)
    private Boolean activa;


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getPuntos_requeridos() {
        return puntos_requeridos;
    }
    public void setPuntos_requeridos(Integer puntos_requeridos) {
        this.puntos_requeridos = puntos_requeridos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Boolean getActiva() {
        return activa;
    }
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    


    

}
