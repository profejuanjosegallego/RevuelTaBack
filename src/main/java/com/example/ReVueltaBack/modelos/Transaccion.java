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
@Table (name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column (name = "monto", nullable = false)
    private Double monto;

    @Column (name = "estado", nullable = false, length = 50)
    private String estado;

    // referencia_pago SÍ es unica (identifica el pago): unico caso legitimo de unique aqui.
    @Column (name = "referencia_pago", nullable = false, unique = true, length = 30)
    private String referencia_pago;

    @Column (name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column ()
    private String comprobante;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "fk_id_pedido")
    @JsonBackReference("pedido_transaccion")
    private Pedido pedido;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getReferencia_pago() {
        return referencia_pago;
    }
    public void setReferencia_pago(String referencia_pago) {
        this.referencia_pago = referencia_pago;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getComprobante() {
        return comprobante;
    }
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    


}
