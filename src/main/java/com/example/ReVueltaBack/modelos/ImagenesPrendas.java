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
@Table(name = "imagenes_prendas")
public class ImagenesPrendas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "url")
    private String url;
    
    @Column(name = "es_principal")
    private Boolean es_Principal;
    
    @Column(name = "orden")
    private Integer orden;
    
    @Column(name = "formato")
    private String formato;
    
    @Column(name = "tamaño_kb")
    private Integer tamaño_KB;
    
    @Column(name = "fecha_subida")
    private LocalDate fecha_subida;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "id_prenda")
    private Prendas prendas;
    

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
    public Boolean getEs_Principal() {
        return es_Principal;
    }
    public void setEs_Principal(Boolean es_Principal) {
        this.es_Principal = es_Principal;
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
    public Integer getTamaño_KB() {
        return tamaño_KB;
    }
    public void setTamaño_KB(Integer tamaño_KB) {
        this.tamaño_KB = tamaño_KB;
    }
    public LocalDate getFecha_subida() {
        return fecha_subida;
    }
    public void setFecha_subida(LocalDate fecha_subida) {
        this.fecha_subida = fecha_subida;
    }
    


}
