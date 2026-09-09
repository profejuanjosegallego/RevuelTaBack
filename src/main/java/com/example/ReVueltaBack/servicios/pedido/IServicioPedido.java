package com.example.ReVueltaBack.servicios.pedido;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.pedido.PedidoRequestDTO;
import com.example.ReVueltaBack.dtos.pedido.PedidoResponseDTO;

public interface IServicioPedido {

    PedidoResponseDTO registrar(PedidoRequestDTO datos);
    List<PedidoResponseDTO> listar();
    List<PedidoResponseDTO> listarPorUsuario(UUID idUsuario);
    List<PedidoResponseDTO> listarPorVendedor(UUID idVendedor);
    PedidoResponseDTO buscarPorId(UUID id);
    PedidoResponseDTO actualizar(UUID id, PedidoRequestDTO datos);
    void eliminar(UUID id);

}
