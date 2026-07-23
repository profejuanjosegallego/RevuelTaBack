package com.example.ReVueltaBack.dtos.Pedido;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoRequestDTO(
    UUID id,
    LocalDateTime fecha,
    String estado,
    Double total,
    String metodoPago,
    String direccionEntrega,
    String notas,
    UUID idComprador
    
) {

}
