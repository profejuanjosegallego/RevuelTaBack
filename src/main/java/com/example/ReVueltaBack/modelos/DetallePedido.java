package com.example.ReVueltaBack.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer cantidad;

    @Column
    private Double precio_unitario;

    @Column
    private Double subtotal;

    @Column
    private Double descuento;

    @Column
    private String estado_item;

    @Column
    private LocalDate fecha;

    //  Relación con Pedido (el atributo se llama "pedido" para que calce con
    //  el mappedBy = "pedido" del @OneToMany de Pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonBackReference("pedido_detallePedido")
    private Pedido pedido;

    //  Relación con Prenda
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    @JsonBackReference("prenda_detallePedido")
    private Prenda prenda;

    // - GETTERS Y SETTERS -

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public String getEstado_item() {
        return estado_item;
    }

    public void setEstado_item(String estado_item) {
        this.estado_item = estado_item;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }
}