package com.example.ReVueltaBack.servicios.envio;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.envio.EnvioRequestDTO;
import com.example.ReVueltaBack.dtos.envio.EnvioResponseDTO;

public interface IServicioEnvio {

    EnvioResponseDTO crear(EnvioRequestDTO dto);
    List<EnvioResponseDTO> listar();

    EnvioResponseDTO buscarPorId(UUID id);
    
    EnvioResponseDTO actualizar(UUID id, EnvioRequestDTO dto);
    
    void eliminar(UUID id);

}
