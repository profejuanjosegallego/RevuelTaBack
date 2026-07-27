package com.example.ReVueltaBack.servicios.Pedido;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Pedido.PedidoRequestDTO;
import com.example.ReVueltaBack.dtos.Pedido.PedidoResponseDTO;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.repositorios.IPedidoRepositorio;
import com.example.ReVueltaBack.validaciones.pedido.IValidacionPedido;

public class ServicioPedidoImpl implements IServicioPedido {


    //Para inyectar creo tantas variables como elementos tenga que inyectar

    private final IPedidoRepositorio pedidoRepositorio;
    private final IValidacionPedido validacionPedido;

    //Constructor para inyectar las dependencias
        
       
    public ServicioPedidoImpl(IPedidoRepositorio pedidoRepositorio, IValidacionPedido validacionPedido) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.validacionPedido = validacionPedido;
    }

    //Implementación de los métodos de la interfaz IServicioPedido

    @Override
    public PedidoResponseDTO registrar(PedidoRequestDTO datos) {

        //1. convertir los datos del usuariorequestDTO en un modelo
        Pedido datosPedido=datos.toEntity();

        //2. aplicar las validaciones
        validacionPedido.validarPedido(datosPedido);

        //3. aplica el metodo del repositorio para guardar el modelo (guardar / save)
        return PedidoResponseDTO.fromEntity(pedidoRepositorio.save(datosPedido));

    }

    @Override
    public List<PedidoResponseDTO> listar() {

        //1. aplicar el metodo del repositorio (buscar todo / findAll)
        return pedidoRepositorio.findAll().stream()
            .map(PedidoResponseDTO::fromEntity)
            .toList();
     
    }

    @Override
    public PedidoResponseDTO buscarPorId(UUID id) {
       
    }

    @Override
    public PedidoResponseDTO actualizar(UUID id, PedidoRequestDTO datos) {
    }

    @Override
    public void eliminar(UUID id) {
        
    }

}

