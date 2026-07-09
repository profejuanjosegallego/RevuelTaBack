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
@Table(name = "imagenes_prenda")
public class ImagenPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "url")
    private String url;
    
    @Column(name = "es_principal")
    private Boolean esPrincipal; 
    
    @Column(name = "orden")
    private Integer orden;
    
    @Column(name = "formato")
    private String formato;
    
    @Column(name = "tamaño_kb")
    private Integer tamanoKb; 
    
    @Column(name = "fecha_subida")
    private LocalDate fechaSubida; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    private Prenda prenda; 
    
    // --- GETTERS Y SETTERS CORREGIDOS ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getTamanoKb() {
        return tamanoKb;
    }

    public void setTamanoKb(Integer tamanoKb) {
        this.tamanoKb = tamanoKb;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }
}