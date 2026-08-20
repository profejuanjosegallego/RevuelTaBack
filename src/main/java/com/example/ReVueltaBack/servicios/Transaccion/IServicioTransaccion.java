package com.example.ReVueltaBack.servicios.transaccion;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.transaccion.TransaccionRequestDTO;
import com.example.ReVueltaBack.dtos.transaccion.TransaccionResponseDTO;

public interface IServicioTransaccion {

    TransaccionResponseDTO crear(TransaccionRequestDTO datos);

    List<TransaccionResponseDTO> listar();

    TransaccionResponseDTO buscarPorId(UUID id);

    TransaccionResponseDTO actualizar(UUID id, TransaccionRequestDTO datos);

    void eliminar(UUID id);

}