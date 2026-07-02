package com.example.ReVueltaBack.modelos;

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

    @ManyToOne
    private Pedido id_pedido;

    @ManyToOne
    private Prendas id_prenda;

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(Double precio_unitario) { this.precio_unitario = precio_unitario; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Double getDescuento() { return descuento; }
    public void setDescuento(Double descuento) { this.descuento = descuento; }

    public String getEstado_item() { return estado_item; }
    public void setEstado_item(String estado_item) { this.estado_item = estado_item; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Pedido getId_pedido() { return id_pedido; }
    public void setId_pedido(Pedido id_pedido) { this.id_pedido = id_pedido; }

    public Prendas getId_prenda() { return id_prenda; }
    public void setId_prenda(Prendas id_prenda) { this.id_prenda = id_prenda; }
}