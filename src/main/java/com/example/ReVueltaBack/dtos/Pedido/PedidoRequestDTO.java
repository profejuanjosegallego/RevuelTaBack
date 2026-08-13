package com.example.ReVueltaBack.dtos.Pedido;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Usuario;

public record PedidoRequestDTO(
    LocalDate fecha,
    String estado,
    Double total,
    String metodoPago,
    String direccionEntrega,
    String notas,
    UUID idComprador
    
) {
    public Pedido toEntity() {
        Usuario usuario = new Usuario();
        Pedido pedido = new Pedido();
        usuario.setId(idComprador);
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(estado);
        pedido.setTotal(total);
        pedido.setMetodo_pago(metodoPago);
        pedido.setDireccion_entrega(direccionEntrega);
        pedido.setNotas(notas);
        pedido.setUsuario(usuario);

        return pedido;
    }
    

}
