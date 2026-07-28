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
@Table(name = "cupones")
public class cupones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "tipo", nullable = false, unique = false)
    private String tipo;

    @Column(name = "valor", nullable = false, unique = false)
    private String valor;

    @Column(name = "usos_maximos", nullable = false, unique = false)
    private Integer usos_maximos;

    @Column(name = "usos_actuales", nullable = false, unique = false)
    private Integer usos_actuales;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fecha_expiracion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getUsos_maximos() {
        return usos_maximos;
    }

    public void setUsos_maximos(Integer usos_maximos) {
        this.usos_maximos = usos_maximos;
    }

    public Integer getUsos_actuales() {
        return usos_actuales;
    }

    public void setUsos_actuales(Integer usos_actuales) {
        this.usos_actuales = usos_actuales;
    }

    public LocalDateTime getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(LocalDateTime fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    
}