package com.example.ReVueltaBack.servicios.seguimientoenvio;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioRequestDTO;
import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioResponseDTO;

public interface IServicioSeguimientoEnvio {

    SeguimientoEnvioResponseDTO crear(SeguimientoEnvioRequestDTO dto);
    List<SeguimientoEnvioResponseDTO> listar();
    SeguimientoEnvioResponseDTO buscarPorId(UUID id);
    SeguimientoEnvioResponseDTO actualizar(UUID id, SeguimientoEnvioRequestDTO dto);
    void eliminar(UUID id);

}
