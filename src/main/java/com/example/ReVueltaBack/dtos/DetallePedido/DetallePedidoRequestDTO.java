package com.example.ReVueltaBack.dto;

import com.example.ReVueltaBack.modelos.DetallePedido;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Prenda;

import java.time.LocalDate;
import java.util.UUID;

public class DetallePedidoRequestDTO {

    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double descuento;
    private String estadoItem;
    private LocalDate fecha;
    private UUID idPedido;
    private UUID idPrenda;

    public DetallePedidoRequestDTO() {
    }

    public DetallePedido toEntity() {
        DetallePedido entidad = new DetallePedido();
        entidad.setCantidad(this.cantidad);
        entidad.setPrecio_unitario(this.precioUnitario);
        entidad.setSubtotal(this.subtotal);
        entidad.setDescuento(this.descuento);
        entidad.setEstado_item(this.estadoItem);
        entidad.setFecha(this.fecha);

        if (this.idPedido != null) {
            Pedido pedido = new Pedido();
            pedido.setId(this.idPedido);
            entidad.setPedido(pedido);
        }

        if (this.idPrenda != null) {
            Prenda prenda = new Prenda();
            prenda.setId(this.idPrenda);
            entidad.setPrenda(prenda);
        }

        return entidad;
    }

    // --- Getters y setters ---

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public String getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(String estadoItem) {
        this.estadoItem = estadoItem;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public UUID getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(UUID idPrenda) {
        this.idPrenda = idPrenda;
    }
}