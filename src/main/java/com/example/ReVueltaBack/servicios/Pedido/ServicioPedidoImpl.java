package com.example.ReVueltaBack.servicios.pedido;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.pedido.PedidoRequestDTO;
import com.example.ReVueltaBack.dtos.pedido.PedidoResponseDTO;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.repositorios.IPedidoRepositorio;
import com.example.ReVueltaBack.validaciones.pedido.IValidacionPedido;

@Service
public class ServicioPedidoImpl implements IServicioPedido {

    private final IPedidoRepositorio pedidoRepositorio;
    private final IValidacionPedido validacionPedido;

    public ServicioPedidoImpl(IPedidoRepositorio pedidoRepositorio, IValidacionPedido validacionPedido) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.validacionPedido = validacionPedido;
    }

    @Override
    public PedidoResponseDTO registrar(PedidoRequestDTO datos) {
        // 1. Convertir los datos del RequestDTO a la entidad
        Pedido datosPedido = datos.toEntity();

        // 2. Aplicar las validaciones de negocio
        validacionPedido.validarPedido(datosPedido);

        // 3. Guardar en BD y mapear la entidad resultante a ResponseDTO
        return PedidoResponseDTO.fromEntity(pedidoRepositorio.save(datosPedido));
    }

    @Override
    public List<PedidoResponseDTO> listar() {
        return pedidoRepositorio.findAll().stream()
                .map(PedidoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public PedidoResponseDTO buscarPorId(UUID id) {
        Pedido pedido = pedidoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "El pedido con ID " + id + " no fue encontrado."
                ));

        return PedidoResponseDTO.fromEntity(pedido);
    }

    @Override
    public PedidoResponseDTO actualizar(UUID id, PedidoRequestDTO datos) {
        // 1. Verificar si el pedido existe
        Pedido pedidoExistente = pedidoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "No se puede actualizar. El pedido con ID " + id + " no existe."
                ));

        // 2. Mapear los nuevos datos a entidad y conservar el ID actual
        Pedido pedidoActualizar = datos.toEntity();
        pedidoActualizar.setId(pedidoExistente.getId());

        // 3. Validar los datos del pedido actualizado
        validacionPedido.validarPedido(pedidoActualizar);

        // 4. Guardar los cambios y retornar el DTO
        return PedidoResponseDTO.fromEntity(pedidoRepositorio.save(pedidoActualizar));
    }

    @Override
    public void eliminar(UUID id) {
        // 1. Validar que exista antes de intentar eliminar
        if (!pedidoRepositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "No se puede eliminar. El pedido con ID " + id + " no existe."
            );
        }

        // 2. Eliminar por ID
        pedidoRepositorio.deleteById(id);
    }
}