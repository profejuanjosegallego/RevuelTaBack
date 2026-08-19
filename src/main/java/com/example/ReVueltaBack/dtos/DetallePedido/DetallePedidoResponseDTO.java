package com.example.ReVueltaBack.dto;

import com.example.ReVueltaBack.modelos.DetallePedido;

import java.time.LocalDate;
import java.util.UUID;

public class DetallePedidoResponseDTO {

    private UUID id;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double descuento;
    private String estadoItem;
    private LocalDate fecha;
    private UUID idPedido;
    private UUID idPrenda;

    public DetallePedidoResponseDTO() {
    }

    public static DetallePedidoResponseDTO fromEntity(DetallePedido entidad) {
        DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();
        dto.id = entidad.getId();
        dto.cantidad = entidad.getCantidad();
        dto.precioUnitario = entidad.getPrecio_unitario();
        dto.subtotal = entidad.getSubtotal();
        dto.descuento = entidad.getDescuento();
        dto.estadoItem = entidad.getEstado_item();
        dto.fecha = entidad.getFecha();

        if (entidad.getPedido() != null) {
            dto.idPedido = entidad.getPedido().getId();
        }

        if (entidad.getPrenda() != null) {
            dto.idPrenda = entidad.getPrenda().getId();
        }

        return dto;
    }

    // --- Getters y setters ---

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