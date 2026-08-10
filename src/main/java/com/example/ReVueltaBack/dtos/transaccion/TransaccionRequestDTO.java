package com.example.ReVueltaBack.dtos.transaccion;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Transaccion;

public record TransaccionRequestDTO(

    String tipo,
    Double monto,
    String estado,
    String referenciaPago,
    LocalDate fecha,
    String comprobante,
    UUID idPedido

) {

    public Transaccion toEntity(){

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo(tipo);
        transaccion.setMonto(monto);
        transaccion.setEstado(estado);
        transaccion.setReferencia_pago(referenciaPago);
        transaccion.setFecha(fecha);
        transaccion.setComprobante(comprobante);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        transaccion.setPedido(pedido);

        return transaccion;
    }
}