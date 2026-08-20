package com.example.ReVueltaBack.controladores;

import com.example.ReVueltaBack.dtos.Pedido.PedidoRequestDTO;
import com.example.ReVueltaBack.dtos.Pedido.PedidoResponseDTO;
import com.example.ReVueltaBack.servicios.Pedido.IServicioPedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosControlador {

    private final IServicioPedido servicioPedido;

    public PedidosControlador(IServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO nuevoPedido = servicioPedido.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        List<PedidoResponseDTO> pedidos = servicioPedido.listar();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable UUID id) {
        PedidoResponseDTO pedido = servicioPedido.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO pedidoActualizado = servicioPedido.actualizar(id, dto);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioPedido.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}