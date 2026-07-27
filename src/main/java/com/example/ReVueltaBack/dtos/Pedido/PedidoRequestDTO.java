package com.example.ReVueltaBack.dtos.Pedido;

import java.time.LocalDate;
import java.util.UUID;

public record PedidoRequestDTO(
    LocalDate fecha,
    String estado,
    Double total,
    String metodoPago,
    String direccionEntrega,
    String notas,
    UUID idComprador
    
) {
    

}
