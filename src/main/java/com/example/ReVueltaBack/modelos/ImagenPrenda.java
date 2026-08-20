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
    private Boolean es_Principal;
    
    @Column(name = "orden")
    private Integer orden;
    
    @Column(name = "formato")
    private String formato;
    
    @Column(name = "tamaño_kb")
    private Integer tamano_KB;
    
    @Column(name = "fecha_subida")
    private LocalDate fecha_subida;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;
    
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
    public Integer getTamano_KB() {
        return tamano_KB;
    }
    public void setTamano_KB(Integer tamano_KB) {
        this.tamano_KB = tamano_KB;
    }
    public LocalDate getFecha_subida() {
        return fecha_subida;
    }
    public void setFecha_subida(LocalDate fecha_subida) {
        this.fecha_subida = fecha_subida;
    }
    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }


}
