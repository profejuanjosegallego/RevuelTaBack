package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String codigoGuia;

    @Column()
    private String estado;

    @Column()
    private Double costo;

    @Column()
    private LocalDate fechaDespacho;

    @Column()
    private Double pesoKg;

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
    @JoinColumn(name = "fk_id_puntoAcopio")
    @JsonBackReference("punto_acopio_envio")
    private PuntoAcopio puntoAcopio;

    //Creando una relación con la tabla Seguimiento envío lll
    @OneToMany(mappedBy = "envio")
    @JsonManagedReference("envio_seguimiento")
    private List<SeguimientoEnvio> seguimientoEnvio = new ArrayList<>();

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo_guia() {
        return codigoGuia;
    }

    public void setCodigo_guia(String codigoGuia) {
        this.codigoGuia = codigoGuia;
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
        return fechaDespacho;
    }

    public void setFecha_despacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Double getPesKg() {
        return pesoKg;
    }

    public void setPeso_kg(Double pesoKg) {
        this.pesoKg = pesoKg;
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

    public PuntoAcopio getPuntos_de_acopio() {
        return puntoAcopio;
    }

    public void setPuntos_de_acopio(PuntoAcopio puntoAcopio) {
        this.puntoAcopio = puntoAcopio;
    }

    


}