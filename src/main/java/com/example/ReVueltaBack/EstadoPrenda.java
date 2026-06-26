package com.example.ReVueltaBack;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estados_prenda")
public class EstadoPrenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @Column(length = 255)
    private String descripcion;
    
    @Column(name = "nivel_desgaste")
    private Integer nivelDesgaste;
    
    @Column(name = "color_etiqueta", length = 30)
    private String colorEtiqueta;
    
    @Column(name = "requiere_revision", nullable = false)
    private Boolean requiereRevision;
    
    @Column(nullable = false)
    private Boolean activo;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivelDesgaste() {
        return nivelDesgaste;
    }

    public void setNivelDesgaste(Integer nivelDesgaste) {
        this.nivelDesgaste = nivelDesgaste;
    }

    public String getColorEtiqueta() {
        return colorEtiqueta;
    }

    public void setColorEtiqueta(String colorEtiqueta) {
        this.colorEtiqueta = colorEtiqueta;
    }

    public Boolean getRequiereRevision() {
        return requiereRevision;
    }

    public void setRequiereRevision(Boolean requiereRevision) {
        this.requiereRevision = requiereRevision;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
