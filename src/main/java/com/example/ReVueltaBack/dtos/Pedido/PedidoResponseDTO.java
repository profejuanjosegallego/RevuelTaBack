package com.example.ReVueltaBack.dtos.Pedido;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Pedido;

public record PedidoResponseDTO(
    UUID id,
    LocalDate fecha,
    String estado,
    Double total,
    String metodoPago,
    String direccionEntrega,
    String notas,
    UUID idComprador) {


        public static PedidoResponseDTO fromEntity(Pedido pedido){

            return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getMetodo_pago(),
                pedido.getDireccion_entrega(),
                pedido.getNotas(),
                pedido.getUsuario().getId()
            );




        }

}
