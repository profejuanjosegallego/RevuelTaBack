package com.example.ReVueltaBack.dtos.transaccion;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Transaccion;

public record TransaccionResponseDTO(

    UUID id,
    String tipo,
    Double monto,
    String estado,
    String referenciaPago,
    LocalDate fecha,
    String comprobante,
    UUID idPedido

) {

    public static TransaccionResponseDTO fromEntity(Transaccion transaccion){

        return new TransaccionResponseDTO(
            transaccion.getId(),
            transaccion.getTipo(),
            transaccion.getMonto(),
            transaccion.getEstado(),
            transaccion.getReferencia_pago(),
            transaccion.getFecha(),
            transaccion.getComprobante(),
            transaccion.getPedido().getId()
        );

    }
}