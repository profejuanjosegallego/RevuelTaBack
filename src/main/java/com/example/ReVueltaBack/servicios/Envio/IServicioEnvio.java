package com.example.ReVueltaBack.servicios.Envio;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Envio.EnvioRequestDTO;
import com.example.ReVueltaBack.dtos.Envio.EnvioResponseDTO;

public interface IServicioEnvio {

    EnvioResponseDTO crear(EnvioRequestDTO dto);
    List<EnvioResponseDTO> listar();

    EnvioResponseDTO buscarPorId(UUID id);
    
    EnvioResponseDTO actualizar(UUID id, EnvioRequestDTO dto);
    
    void eliminar(UUID id);

}
