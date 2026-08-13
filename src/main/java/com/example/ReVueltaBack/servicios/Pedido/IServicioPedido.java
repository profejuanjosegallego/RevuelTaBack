package com.example.ReVueltaBack.servicios.Pedido;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Pedido.PedidoRequestDTO;
import com.example.ReVueltaBack.dtos.Pedido.PedidoResponseDTO;

public interface IServicioPedido {

    PedidoResponseDTO registrar(PedidoRequestDTO datos);
    List<PedidoResponseDTO> listar();
    PedidoResponseDTO buscarPorId(UUID id);
    PedidoResponseDTO actualizar(UUID id, PedidoRequestDTO datos);
    void eliminar(UUID id);

}
