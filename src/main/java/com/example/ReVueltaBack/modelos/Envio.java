package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    //Creando una relación con la tabla pedido
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_pedido")
    @JsonBackReference("pedido_envio")
    private Pedido pedido;

    //Creando una relación con la tabla transportista
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_transportista")
    @JsonBackReference("transportista_envio")
    private Transportista transportista;

    //Creando una relación con la tabla puntos de acopio
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_puntos_de_acopio")
    @JsonBackReference("puntos_de_acopio_envio")
    private PuntosDeAcopio puntos_de_acopio;

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public PuntosDeAcopio getPuntos_de_acopio() {
        return puntos_de_acopio;
    }

    public void setPuntos_de_acopio(PuntosDeAcopio puntos_de_acopio) {
        this.puntos_de_acopio = puntos_de_acopio;
    }

    


}
