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
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String codigo_guia;

    @Column()
    private String estado;

    @Column()
    private Double costo;

    @Column()
    private LocalDate fecha_despacho;

    @Column()
    private Double peso_kg;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo_guia() {
        return codigo_guia;
    }

    public void setCodigo_guia(String codigo_guia) {
        this.codigo_guia = codigo_guia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public LocalDate getFecha_despacho() {
        return fecha_despacho;
    }

    public void setFecha_despacho(LocalDate fecha_despacho) {
        this.fecha_despacho = fecha_despacho;
    }

    public Double getPeso_kg() {
        return peso_kg;
    }

    public void setPeso_kg(Double peso_kg) {
        this.peso_kg = peso_kg;
    }

    


}
